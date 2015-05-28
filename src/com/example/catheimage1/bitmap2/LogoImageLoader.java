package com.example.catheimage1.bitmap2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.ImageView;

public class LogoImageLoader {

	private final static String TAG = "LogoImageLoader";

	private BitmapCache imageCacheMap = null;

	// 缓存数量
	private final static int CACHE_IMG_SIZE = 10;

	private static LogoImageLoader asyncImageLoader = null;

	/* 下载保存路径 */
	private String picSavePath;

	private List<File> cacheFiles;

	private final static String SDPICPATH = "cntvhd";

	private CacheManger cacheManger;

	static {
		asyncImageLoader = new LogoImageLoader();
	}

	public LogoImageLoader() {
		imageCacheMap = new BitmapCache(CACHE_IMG_SIZE);
		picSavePath = Environment.getExternalStorageDirectory() + "/"
				+ SDPICPATH + "/";
		cacheManger = new CacheManger();
		// 执行获取图片集合
		cacheFiles = cacheManger.getCacheFiles(picSavePath);
	}

	public static LogoImageLoader getInstance() {
		return asyncImageLoader;
	}

	/**
	 * @Title: loadDrawable
	 * @Description: TODO 加载图片
	 * @param @param imageUrl
	 * @param @param imageView
	 * @param @param imageCallback
	 * @param @return 参数文件
	 * @return Drawable 返回类型
	 * @throws
	 */
	public Bitmap loadBitmap(final String imageUrl) {
		if (imageUrl == null || imageUrl.equals("")) {
			return null;
		}

		//Get bitmap from image cache
		if (imageCacheMap.get(imageUrl) != null) {
			Bitmap bitmap = imageCacheMap.get(imageUrl);

			return bitmap;
		} else if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			String bitmapName = imageUrl.substring(
					(imageUrl.lastIndexOf("/") + 1), imageUrl.lastIndexOf("."));

			File cacheDir = new File(picSavePath + bitmapName);
			if (cacheDir.exists()) {
				return BitmapFactory.decodeFile(picSavePath + bitmapName);
			}
		}
		// 建立新一个新的线程下载图片
		begainLoadPic(imageUrl);
		return null;
	}

	void begainLoadPic(String imageUrl) {
		// 建立新一个新的线程下载图片
		Thread thread = new Thread(new LoadLogoPicThread(imageUrl));
		thread.start();
	}

	// 获取数据线程
	class LoadLogoPicThread implements Runnable {
		String imageUrl;

		LoadLogoPicThread(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public void run() {
			Drawable drawable = null;
			try {
				drawable = ImageUtil.getDrawableFromUrl(imageUrl);

			} catch (Exception e) {
				e.printStackTrace();
			}
			// 图片加载成功进行双缓存操作
			if (drawable != null && ((BitmapDrawable) drawable).getBitmap() != null) {
				Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
				imageCacheMap.put(imageUrl, bitmap);

				// 判断SD卡是否存在，并且是否具有读写权限
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					// 获得存储卡的路径

					File file = new File(picSavePath);
					// 判断文件目录是否存在
					if (!file.exists()) {
						file.mkdir();
					}

					File bitmapFile = new File(picSavePath
							+ imageUrl.substring(
									(imageUrl.lastIndexOf("/") + 1),
									imageUrl.lastIndexOf(".")));

					if (!bitmapFile.exists()) {
						try {
							bitmapFile.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					FileOutputStream fos;
					try {
						fos = new FileOutputStream(bitmapFile);
						bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
						fos.close();
						// 删除超出限定数量图片
						cacheFiles = cacheManger.delOverflowPic(cacheFiles,
								bitmapFile, picSavePath);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 回调接口
	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable, ImageView imageView,
				String imageUrl);
	}

}
