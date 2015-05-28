package com.example.catheimage1.bitmap2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LogoImageSharePreference {

	private final static String SHAREPREFERENCE_NAME = "LogoHDImageSharePreference";

	private final static String KEY_LOGO_IMAGE_URL = "image_pad_url";

	public LogoImageSharePreference() {

	}

	public static boolean putLogoImageUrl(Context context, String logo_image_url) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHAREPREFERENCE_NAME, 0);
		Editor editor = sharedPreferences.edit();
		editor.putString(KEY_LOGO_IMAGE_URL, logo_image_url);

		return editor.commit();
	}

	public static String getLogoImageUrl(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHAREPREFERENCE_NAME, 0);
		return sharedPreferences.getString(KEY_LOGO_IMAGE_URL, "");
	}

	public static void clearAll(Context context) {
		putLogoImageUrl(context, "");
	}

}
