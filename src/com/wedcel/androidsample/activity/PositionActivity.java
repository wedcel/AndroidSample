package com.wedcel.androidsample.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.TextView;

import com.wedcel.androidsample.R;
 

public class PositionActivity extends Activity{
	
	private Button button1,button2,button3,button4,button5;
	private TextView textview1;
	String str1;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_position);
		
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		button4 = (Button)findViewById(R.id.button4);
		button5 = (Button)findViewById(R.id.button5);
		
		
		
		textview1 = (TextView)findViewById(R.id.textview1);
		button1.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				// TODO Auto-generated method stub
				Rect localvisibleRect = new Rect();
				button5.getLocalVisibleRect(localvisibleRect);
				Rect globalvisibleRect = new Rect();
				button5.getGlobalVisibleRect(globalvisibleRect);
				int [] locationInWindow = new int[2];
				button5.getLocationInWindow(locationInWindow);
				int [] locationOnScreen = new int[2];
				button5.getLocationOnScreen(locationOnScreen);
				
				 str1 = "button1.getTranslationX()"+button5.getTranslationX()+"----button1.getTranslationY()"+button5.getTranslationY()
							+"----localvisibleRect.left"+localvisibleRect.left+"-----localvisibleRect.right"+localvisibleRect.right+"----localvisibleRect.top"+
							localvisibleRect.top+"----localvisibleRect.bottom"+localvisibleRect.bottom
							+"----globalvisibleRect.left"+globalvisibleRect.left+"-----globalvisibleRect.right"+globalvisibleRect.right+"----globalvisibleRect.top"+
							globalvisibleRect.top+"----globalvisibleRect.bottom"+globalvisibleRect.bottom
							
							+"--locationInWindow.x"+locationInWindow[0]+"---locationInWindow.y"+locationInWindow[1]
									
							+"---locationOnScreen.x"+locationOnScreen[0]+"---locationOnScreen.y"+locationOnScreen[1];
				 textview1.setText(str1);
				 
				
			}
		});
		
		 button5.setTranslationX(0);
		 button5.setTranslationY(0);
		
		
		
  }
	
	
	
	
	 
}
