package com.example.catheimage1;

import com.example.catheimage1.bitmap.FinalBitmap;
import com.example.catheimage1.constants.Variables;

import android.app.Application;
import android.widget.Toast;

public class MainApplication extends Application {
@Override
public void onCreate() {
	// TODO Auto-generated method stub
	super.onCreate();
	//if (Variables.isfirst) {
		
		FinalBitmap.create(getApplicationContext(), Variables.CACHE_DIR, 1024 * 1024 * 10);
//	}else {
//		Variables.isfirst=true;
//		FinalBitmap.create(getApplicationContext(), Variables.CACHE_DIR2, 1024 * 1024 * 10);
//	}
	Toast.makeText(getApplicationContext(), "application什么时候执行", Toast.LENGTH_SHORT).show();
}
}
