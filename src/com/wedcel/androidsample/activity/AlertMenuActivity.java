package com.wedcel.androidsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wedcel.androidsample.R;
import com.wedcel.androidsample.view.MMAlert;
import com.wedcel.androidsample.view.SelectPicPopupWindow;

public class AlertMenuActivity extends Activity {
	//自定义的弹出框类
	SelectPicPopupWindow menuWindow;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button tv = (Button) this.findViewById(R.id.button);
		tv.setText("点击测试AlertWindow");
		// 把文字控件添加监听，点击弹出自定义窗口
		tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 实例化SelectPicPopupWindow
				MMAlert.showAlert(AlertMenuActivity.this, new MMAlert.OnAlertSelectId(){

					@Override
					public void onClick(int whichButton) {						
						 
					}
					
				},
				null);
			}
		});
	}
	
 
}