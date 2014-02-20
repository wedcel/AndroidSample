package com.wedcel.androidsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.wedcel.androidsample.R;
import com.wedcel.androidsample.view.SliderRelativeLayout;
 
 
public class SliderRelaActivity extends Activity{
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(1 == msg.what){
				Toast.makeText(SliderRelaActivity.this, "滑动到了", Toast.LENGTH_SHORT).show();
			}
			
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lock_screen);
		SliderRelativeLayout sliderRelativeLayout = (SliderRelativeLayout) findViewById(R.id.sliderLayout);
		sliderRelativeLayout.setMainHandler(handler);
		sliderRelativeLayout.setIsLast(true);
		sliderRelativeLayout.getBackground().setAlpha(180); //设置背景的透明度
  }
	
	
	
	
	 
}
