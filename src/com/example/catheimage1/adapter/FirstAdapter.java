package com.example.catheimage1.adapter;


import java.util.List;


import com.example.catheimage1.R;
import com.example.catheimage1.bean.LiveChannelBean;
import com.example.catheimage1.bitmap.FinalBitmap;
import com.example.catheimage1.bitmap2.LogoImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstAdapter extends MyBaseAdapter {
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private FinalBitmap fb;
	public FirstAdapter(Context context){
		this.mContext=context;
		mLayoutInflater=LayoutInflater.from(mContext);
		fb=FinalBitmap.create(mContext);
	}

	
	private void resetViews(ViewHolder holder) {
		holder.imageView.setImageBitmap(null);
		holder.textView.setText("");
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView==null){
			convertView=mLayoutInflater.inflate(R.layout.cctv_listview_live_item, null);
			holder=new ViewHolder();
			holder.imageView=(ImageView) convertView.findViewById(R.id.tv_picture_image_view);
			holder.textView=(TextView) convertView.findViewById(R.id.tv_living_text_view);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		resetViews(holder);
		if(null!=items){
			//http://d.hiphotos.baidu.com/zhidao/pic/item/b999a9014c086e06061d0a3806087bf40ad1cb0f.jpg
			LiveChannelBean livebean = (LiveChannelBean) items.get(position);
			fb.display(holder.imageView, "http://e.hiphotos.baidu.com/image/pic/item/7aec54e736d12f2ee54cbf6b4dc2d5628535686b.jpg");
			//Bitmap loadBitmap = LogoImageLoader.getInstance().loadBitmap(livebean.getBigImgUrl());
			//holder.imageView.setImageBitmap(loadBitmap);
			holder.textView.setText(livebean.getTitle());
			
		}else {
			//error
		}
		return convertView;
	}
static class ViewHolder{
	ImageView imageView;
	TextView textView;
}
}
