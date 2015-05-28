package com.example.catheimage1.util;

import java.lang.reflect.Field;

import com.example.catheimage1.R;



import android.app.Activity;

/**
 * <hr/>
 * 
 * @author www.TheWk.cn.vc
 */
public final class RefreshUtils {

	public static final void initGui(Activity activity) {
		Field[] fields = activity.getClass().getDeclaredFields();
		Class<R.id> ridclz = R.id.class;
		for (Field field : fields) {
			if (isView(field.getType())) {
				field.setAccessible(true);
				try {
					field.set(activity, activity.findViewById(ridclz.getField(field.getName()).getInt(ridclz)));
				}catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				
			}
		}
	}

	private static final boolean isView(Class<?> clz) {
		if (clz==null) {
			return false;
		}
		if (clz.equals(android.view.View.class)) {
			return true;
		}
		else if (clz.equals(Object.class)) {
			return false;
		}
		return isView(clz.getSuperclass());
	}

}
