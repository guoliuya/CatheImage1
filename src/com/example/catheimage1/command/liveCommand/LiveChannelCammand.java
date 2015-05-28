package com.example.catheimage1.command.liveCommand;

import java.util.List;

import com.example.catheimage1.bean.LiveChannelBean;
import com.example.catheimage1.command.AbstractCommand;
import com.example.catheimage1.util.HttpTools;


public class LiveChannelCammand extends AbstractCommand<List<LiveChannelBean>> {
	private String path;

	public LiveChannelCammand(String path) {
		this.path = path;
	}

	@Override
	public List<LiveChannelBean> execute() throws Exception {
		String httpResult = HttpTools.post(path);
		List<LiveChannelBean> result = LiveChannelBean.convertFromJsonObject(httpResult);
		return result;
	}
}

