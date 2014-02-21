package com.wedcel.androidsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wedcel.androidsample.R;
import com.wedcel.androidsample.animation.animation.AnimatorSet;
import com.wedcel.androidsample.animation.animation.ObjectAnimator;
import com.wedcel.androidsample.util.AppLogger;
import com.wedcel.androidsample.view.SliderRelativeLayout;
 
 
public class SliderRelaActivity extends Activity{
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(1 == msg.what){
				
				ObjectAnimator anim1 = ObjectAnimator.ofFloat(sliderRelativeLayout, "y",
						   height, height/2).setDuration(500);
				linearLay2.setVisibility(View.VISIBLE);
				   ObjectAnimator anim2 = ObjectAnimator.ofFloat(linearLay2, "y",
						   height, height/2-sliderRelativeLayout.getHeight()).setDuration(500);
				   AnimatorSet s1 = new AnimatorSet();
				   s1.play(anim1).before(anim2);
				   s1.start();
				
			}
			
		}
	};
	
	private LinearLayout linearLay2;
	private RelativeLayout relaLayout;
	private SliderRelativeLayout sliderRelativeLayout;
	private int height;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lock_screen);
		sliderRelativeLayout = (SliderRelativeLayout) findViewById(R.id.sliderLayout);
		sliderRelativeLayout.setMainHandler(handler);
		sliderRelativeLayout.setIsLast(false);
		sliderRelativeLayout.getBackground().setAlpha(180); //设置背景的透明度
		
		relaLayout = (RelativeLayout) findViewById(R.id.relaLayout);
		linearLay2 = (LinearLayout) findViewById(R.id.linearLay2);
		
		
		relaLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				// TODO Auto-generated method stub
				height = relaLayout.getHeight();
				AppLogger.d("linearLay1.width"+relaLayout.getWidth()+"----linearLay1.height"+relaLayout.getHeight());
			}
			
		});
		
		
		linearLay2.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				// TODO Auto-generated method stub
				AppLogger.d("linearLay2.width"+linearLay2.getWidth()+"----linearLay2.height"+linearLay2.getHeight());
			}
			
		});
		
  }
	
	
	
	
	 
}
