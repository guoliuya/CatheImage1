/**
 * 
 */
package com.example.catheimage1.bitmap2;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.graphics.Bitmap;


/**
* @ClassName: BitmapCache
* @Description: TODO 图片缓存类
* @author Cheng Yong
* @date  2013-4-11
*
*/
public class BitmapCache {
	private Map<String, Bitmap> map = null;
	private Map<String, SoftReference<Bitmap>> softMap = null;
	private int size = 0;
	private List<String> list = null;
	
	public BitmapCache(int size){
		this.size = size;
		map = new HashMap<String, Bitmap>();
		softMap = new HashMap<String, SoftReference<Bitmap>>();
		list = new ArrayList<String>();
	}
	
	public void put(String id,Bitmap bitmap){
		if(!list.contains(id)){
			list.add(id);
			if(list.size() > size){
				String indexValue = list.get(0);
				softMap.put(indexValue, new SoftReference<Bitmap>(map.get(indexValue)));
				map.put(indexValue, null);
				list.remove(0);
			}
		}
		
		map.put(id, bitmap);
	}
	
	public Bitmap get(String id){
		Bitmap bitmap = map.get(id);
		if(bitmap == null){
			return getSoftBitmap(id);
		}
		
		return map.get(id);
	}
	
	private Bitmap getSoftBitmap(String id){
		SoftReference<Bitmap> bitmapReference = softMap.get(id);
		if(bitmapReference != null){
			return bitmapReference.get();
		}
		
		return null;
	}
}

