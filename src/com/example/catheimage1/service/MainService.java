package com.example.catheimage1.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import com.example.catheimage1.command.AbstractCommand;
import com.example.catheimage1.command.ICallBack;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

/**
 * 
 * @Description 
 * @author Cheng Yong
 * @version 1.0 2012-8-15
 * @class MainService
 */
@SuppressWarnings("unchecked")
public class MainService extends Service implements Runnable {

	private static final String TAG = "MainService";

	// 轮循时间 1000毫秒
	private static final long SleepTime = 2000;

	// 服务线程是否运行
	private boolean isRunning = false;

	/**
	 * 任务集合
	 */
	private static LinkedList<AbstractCommand> All_Commands = new LinkedList<AbstractCommand>();

	private IBinder myBinder = new MainServiceBinder();

	private final Handler handler = new Handler(Looper.getMainLooper()) {

		@Override
		public void handleMessage(Message msg) {
			HandlerObj handlerObj = (HandlerObj) msg.obj;
			AbstractCommand command = handlerObj.command;
			LinkedHashMap<String, ICallBack> callBacks = command.getCallBacks();
			for (String callBackName : callBacks.keySet()) {
				callBacks.get(callBackName).callBack(handlerObj.command, handlerObj.result, handlerObj.exception);
			}
		}
	};

	@Override
	public IBinder onBind(Intent arg0) {
		return myBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		isRunning = true;
		MainService.addTaskAtLast(new SleepCommand());//test
		new Thread(this).start();
	}

	@Override
	public void run() {

		while (isRunning) {
			// d(TAG, "Thread In Service Run:" + Thread.currentThread().getName());
//			Logs.e("jsx==run", "isRunning55555");
			final AbstractCommand command = removeFirstTask();
			if (command != null) {
				if (command.newThread()) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							runCommand(command);
						}
					}).start();

				}
				else {
					runCommand(command);
				}
			}
		}
	}

	/**
	 * 执行命令
	 * 
	 * @author www.TheWk.cn.vc
	 */
	private void runCommand(final AbstractCommand command) {
		Message message = null;
		Object result = null;
		Exception exception = null;
//		Logs.e("jsx==runCommand", "runCommand111111111");
		try {
			result = command.execute();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			exception = ex;
		}
    
		// 如果任务的回调类集合不为空,则才需要使用主线程
		if (command.haveCallBacks()) {
			HandlerObj handlerObject = new HandlerObj(command, result, exception);
			message = handler.obtainMessage();
			message.obj = handlerObject;
			handler.sendMessage(message);
		}
	}

	/**
	 * 填加一个最后执行的任务
	 */
	public static void addTaskAtLast(AbstractCommand command) {
		synchronized (All_Commands) {
			All_Commands.addLast(command);
		}
	}

	/**
	 * 填加一个最先执行的任务
	 */
	public static void addTaskAtFirst(AbstractCommand command) {
		synchronized (All_Commands) {
			All_Commands.addFirst(command);
		}
	}

	public static AbstractCommand removeFirstTask() {
		synchronized (All_Commands) {
			if (!(All_Commands.isEmpty())) {
				return All_Commands.removeFirst();
			}
			return null;
		}
	}

	public static AbstractCommand removeLastTask() {
		synchronized (All_Commands) {
			if (!(All_Commands.isEmpty())) {
				return All_Commands.removeLast();
			}
			return null;
		}
	}

	@Override
	public void onDestroy() {
		addTaskAtLast(new StopCommand());
		super.onDestroy();
	}

	private class HandlerObj {

		public HandlerObj(AbstractCommand command, Object result, Exception exception) {
			this.command = command;
			this.result = result;
			this.exception = exception;
		}
		private AbstractCommand command;
		private Object result;
		private Exception exception;

	}

	/**
	 * 终止线程.用于在服务线程中,所有的任务完成以后,终于该服务
	 * <hr/>
	 */
	private class StopCommand extends AbstractCommand {

		@Override
		public Object execute() throws Exception {
			isRunning = false;
			return null;
		}

	}

	private class SleepCommand extends AbstractCommand {

		@Override
		public Object execute() throws Exception {
			try {
				TimeUnit.MILLISECONDS.sleep(SleepTime);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
//			Logs.e("jsx==SleepCommand", "SleepCommand");
			MainService.addTaskAtLast(this);
			return null;
		}
	}

	public class MainServiceBinder extends Binder {

		public MainService getService() {
			return MainService.this;
		}
	}

}
