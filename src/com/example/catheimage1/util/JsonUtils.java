package com.example.catheimage1.util;


import java.lang.reflect.Field;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.catheimage1.excaption.CntvException;


/**
* @ClassName: JsonUtils
* @Description: TODO JsonObject转JavaObject
* @author Cheng Yong
* @date  2013-4-11
*
*/
public class JsonUtils {
	
	private static final String TAG="JsonUtils";
	
	public static boolean isJsonDataEffective(JSONObject json, String name) {
		try {
			if (json.has(name) && json.get(name) != null && !"".equals(json.getString(name))
				&& !"null".equals(json.getString(name))) {
				return true;
			} else {
				return false;
			}
		} catch (JSONException e) {
			return false;
		}
	}
	
	public static boolean isJsonArrayDataEffective(JSONObject json, String name) {
		try {
			if (json.has(name) && json.getJSONArray(name) != null && json.getJSONArray(name).length() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (JSONException e) {
			return false;
		}
	}
	
	public static Object fromJsonToJava(JSONObject json, Class<?> pojo) throws CntvException {
		try {
			// 首先得到pojo所定义的字段
			Field[] fields = pojo.getDeclaredFields();
			// 根据传入的Class动态生成pojo对象
			Object obj = pojo.newInstance();
			for (Field field : fields) {
				// 设置字段可访问
				field.setAccessible(true);
				// 得到字段的属性名
				String name = field.getName();
				// 如果字段在JSONObject中不存在会抛出异常，如果出异常，则跳过。
//				try {
//					json.get(name);
//				} catch (Exception ex) {
//					continue;
//				}
				if (json.has(name)&&json.get(name) != null && !"".equals(json.getString(name))
						&& !"null".equals(json.getString(name))) {
					//根据字段的类型将值转化为相应的类型，并设置到生成的对象中。
					if (field.getType().equals(Long.class)
							|| field.getType().equals(long.class)) {
						field.set(obj, Long.parseLong(json.getString(name)));
					} else if (field.getType().equals(String.class)) {
						field.set(obj, json.getString(name));
					} else if (field.getType().equals(Double.class)
							|| field.getType().equals(double.class)) {
						field.set(obj, Double.parseDouble(json.getString(name)));
					} else if (field.getType().equals(Integer.class)
							|| field.getType().equals(int.class)) {
						field.set(obj, Integer.parseInt(json.getString(name)));
					} else if (field.getType().equals(java.util.Date.class)) {
						field.set(obj, new Date(json.getLong(name)));
					}  else if (field.getType().equals(Boolean.class)
							|| field.getType().equals(boolean.class)) {
						field.set(obj, Boolean.parseBoolean(json.getString(name)));
					} else {
						continue;
					}
				}
			}
			return obj;
		} catch (Exception e) {
			throw new CntvException("json数据转换实体失败!",e);
		}
	}

}
