package com.wedcel.androidsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.wedcel.androidsample.R;
import com.wedcel.androidsample.util.AppLogger;
import com.wedcel.androidsample.util.TouchEventUtil;
 
 
public class InterceptActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppLogger.d("onCreate");
		setContentView(R.layout.activity_intercept);
		
		
  }
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		AppLogger.d("onPause");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		AppLogger.d("onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		AppLogger.d("onResume");
	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		AppLogger.d("onStart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		AppLogger.d("onStop");
	}


	@Override
	public Window getWindow() {
		// TODO Auto-generated method stub
		AppLogger.d("getWindow");
		return super.getWindow();
	}


	@Override
	public WindowManager getWindowManager() {
		// TODO Auto-generated method stub
		AppLogger.d("getWindowManager");
		return super.getWindowManager();
	}


	@Override
	public void onAttachedToWindow() {
		// TODO Auto-generated method stub
		AppLogger.d("onAttachedToWindow");
		super.onAttachedToWindow();
	}




	/**
	 * 默认情况下  就是都返回super的话  路径是这样的：
	 * ActionDown:  ActionUp  ActionMove:
	 * dispatchTouchEvent（activity）-->dispatchTouchEvent(fatherview)-->onInterceptTouchEvent（fatherview）
	 * -->dispatchTouchEvent(childview)--->touchEvent(childview)
	 * 
	 * 
	 * 如果这里返回true 或者 false将会执行下面的onTouchEvent（activity）
	 * 事件不进行分发 
	 * 
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {//事件分发
		// TODO Auto-generated method stub
		AppLogger.d("InterceptActivity | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		return super.dispatchTouchEvent(ev);
		
	} 
	 
	@Override
	public boolean onTouchEvent(MotionEvent event) {//事件响应 
		// TODO Auto-generated method stub
		AppLogger.d("InterceptActivity | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()));
		return super.onTouchEvent(event);
	}
	
	 
}
