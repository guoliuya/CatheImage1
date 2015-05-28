package com.example.catheimage1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;






import com.example.catheimage1.adapter.FirstAdapter;
import com.example.catheimage1.asynctask.InterfaceListener;
import com.example.catheimage1.asynctask.LiveAsynctask;
import com.example.catheimage1.bean.LiveBean;
import com.example.catheimage1.bean.LiveChannelBean;
import com.example.catheimage1.bitmap.FinalBitmap;
import com.example.catheimage1.command.AbstractCommand;
import com.example.catheimage1.command.ICallBack;
import com.example.catheimage1.command.liveCommand.LiveChannelCammand;
import com.example.catheimage1.constants.Variables;
import com.example.catheimage1.listview.XListView;
import com.example.catheimage1.listview.XListView.IXListViewListener;
import com.example.catheimage1.service.MainService;
import com.example.catheimage1.util.GetFileSizeUtil;
import com.example.catheimage1.util.ImageRecycleListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements IXListViewListener,
		InterfaceListener {
	private XListView listView;
	private FirstAdapter firstAdapter;
	private List<LiveChannelBean> items = new ArrayList<LiveChannelBean>();
	private LiveBean liveBean;
	private boolean isRefresh;
	private String mCacheSize;
	private static final int MSG_CAL_CACHE_MEMORY_SIZE = 1;
	private Button bt;
	private TextView tv;
	   //
	private Handler mHandler = new Handler() {
		
		@Override
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			
			case MSG_CAL_CACHE_MEMORY_SIZE:
				tv.setText(mCacheSize);
				break;
			default:
				super.handleMessage(msg);
				break;
			}
		}
	};
	private final ServiceConnection mServiceConnection = new ServiceConnection() {

		public void onServiceDisconnected(ComponentName name) {

		}

		public void onServiceConnected(ComponentName name, IBinder service) {
			MainService mainService = ((MainService.MainServiceBinder) service)
					.getService();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bindMainService();                                                                               
		bindMainService();                                                                               
		setContentView(R.layout.activity_main);
		
		initView();
		initData();
		initAction();
		calCacheSize();
		// new LiveAsynctask(this).execute();
	}

	private void bindMainService() {
		// TODO Auto-generated method stub
		Intent mainServiceIntent = new Intent(this, MainService.class);
		bindService(mainServiceIntent, mServiceConnection,
				Context.BIND_AUTO_CREATE);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//FinalBitmap.create(getApplicationContext()).onDestroy();//红米手机图片不显示的原因
			//System.exit(0);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	private void calCacheSize() {
		Thread thread = new Thread() {
			public void run() {
				try {
					mCacheSize = GetFileSizeUtil.getInstance().FormetFileSize(
							GetFileSizeUtil.getInstance().getFileSize(new File(Variables.CACHE_DIR)));
					mHandler.sendEmptyMessage(MSG_CAL_CACHE_MEMORY_SIZE);
				} catch (Exception e) {
					mCacheSize = "";
				}
			};
		};
		thread.start();
	}
	private void initAction() {
		// TODO Auto-generated method stub
		listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
		listView.setPullLoadEnable(true);
		listView.setNeedFootBlack(false);
		listView.setRecyclerListener(new ImageRecycleListener(
				new int[] { R.id.tv_picture_image_view }));
		listView.setXListViewListener(this);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Thread thread = new Thread() {
					public void run() {
						try {
							FinalBitmap.create(MainActivity.this).clearCache();
						} catch (Exception e) {
							//do nothing
						}
						mHandler.sendEmptyMessage(MSG_CAL_CACHE_MEMORY_SIZE);
					};
				};
				thread.start();
			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub
		getLiveInfoMethod("http://serv.cbox.cntv.cn/json/zhibo/yangshipindao/ysmc/index.json");
		for (int i = 0; i < 10; i++) {
			// items.add("永不言弃");
		}

	}

	private void getLiveInfoMethod(String url) {
		// TODO Auto-generated method stub
		LiveChannelCammand liveChannelCammand;
		if (isRefresh) {

			liveChannelCammand = new LiveChannelCammand(
					"http://serv.cbox.cntv.cn/json/zhibo/difangpindao/dfmc/index.json");
		} else {
			liveChannelCammand = new LiveChannelCammand(url);
		}
		liveChannelCammand.addCallBack("livecommand",
				new ICallBack<List<LiveChannelBean>>() {

					@Override
					public void callBack(
							AbstractCommand<List<LiveChannelBean>> command,
							List<LiveChannelBean> result, Exception ex) {
						// TODO Auto-generated method stub
						// listener.setData(result);
						//Toast.makeText(MainActivity.this, "网络请求回调", Toast.LENGTH_SHORT).show();
						firstAdapter = new FirstAdapter(MainActivity.this);
						

						
						listView.setAdapter(firstAdapter);
						if (isRefresh) {
							firstAdapter.notifyDataSetChanged();
							items.addAll(result);
							
							 firstAdapter.setItems(items);
						}else {
							 items.addAll(result);
							 firstAdapter.setItems(result);
						}
						// liveBean=new LiveBean();
						// liveBean.setList(result);
						listView.stopRefresh();

					}
				});
		MainService.addTaskAtFirst(liveChannelCammand);
	}

	private void initView() {
		// TODO Auto-generated method stub
		// RefreshUtils.initGui(MainActivity.this);
		listView = (XListView) findViewById(R.id.firstlistview);
		bt=(Button) findViewById(R.id.bt);
		tv=(TextView) findViewById(R.id.tv);
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		listView.setRefreshTime(getString(R.string.xlistview_header_last_time,
				format2.format(new Date(System.currentTimeMillis()))));

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		isRefresh = true;
		listView.stopLoadMore();
		getLiveInfoMethod("http://serv.cbox.cntv.cn/json/zhibo/yangshipindao/ysmc/index.json");

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		listView.stopRefresh();
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		listView.setRefreshTime(getString(R.string.xlistview_header_last_time,
				format2.format(new Date(System.currentTimeMillis()))));
	}

	@Override
	public void onRightSlip() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeftSlip() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetData(List<LiveChannelBean> list) {
		// TODO Auto-generated method stub
		// firstAdapter=new FirstAdapter(MainActivity.this);
		// firstAdapter.setItems(list);
		// listView.setAdapter(firstAdapter);
		// items.addAll(list);
	}
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	unbindService(mServiceConnection);
	
	//FinalBitmap.create(getApplicationContext()).exitTasksEarly(true);
//	FinalBitmap.create(getApplicationContext()).clearMemoryCache();
//	FinalBitmap.create(getApplicationContext()).onDestroy();
}
}
