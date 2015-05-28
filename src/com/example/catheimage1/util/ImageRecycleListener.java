package com.example.catheimage1.util;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView.RecyclerListener;
import android.widget.ImageView;

public class ImageRecycleListener implements RecyclerListener {

	private int[] resIds;

	private int resId;

	public ImageRecycleListener(int[] resIds) {
		super();
		this.resIds = resIds;
	}

	public ImageRecycleListener(int resId) {
		super();
		this.resId = resId;
	}

	@Override
	public void onMovedToScrapHeap(View view) {
		try {
			if (resIds != null) {
				ImageView image = null;
				int count = resIds.length;
				for (int i = 0; i < count; i++) {
					image = (ImageView) view.findViewById(resIds[i]);
					if (image != null) {
						if (image.getDrawable() != null) {
							image.getDrawable().setCallback(null);
						}
						 image.setImageDrawable(null);
					}
				}
			}
			if (resId >= 0) {
				ImageView imageView = (ImageView) view.findViewById(resId);
				if (imageView != null) {
					if (imageView.getDrawable() != null) {
						imageView.getDrawable().setCallback(null);
					}
					imageView.setImageDrawable(null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
