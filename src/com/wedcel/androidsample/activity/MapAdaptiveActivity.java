package com.wedcel.androidsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.wedcel.androidsample.R;
import com.wedcel.androidsample.view.SelectPicPopupWindow;

public class MapAdaptiveActivity extends Activity {
	//自定义的弹出框类
	SelectPicPopupWindow menuWindow;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button tv = (Button) this.findViewById(R.id.button);
		 
	}
	
 
}