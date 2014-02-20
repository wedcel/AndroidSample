package com.wedcel.androidsample.view;

import com.wedcel.androidsample.util.AppLogger;
import com.wedcel.androidsample.util.TouchEventUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 *
 */
public class InterceptChildView extends Button {

 
	
     public InterceptChildView(Context context) {
        super(context);
    }

    public InterceptChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
	}

	/**
	 * 这里返回treu 就不会往下传递 会执行所有的dispatchTouchEvent  不执行onTouchEvent
	 * 如果返回false activity的onTouchEvent也会执行 返回默认的不执行
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {//事件分发
		// TODO Auto-generated method stub
		AppLogger.d("InterceptChildView | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		return super.dispatchTouchEvent(ev);
		
		
	} 
  
	@Override
	public boolean onTouchEvent(MotionEvent event) {//事件响应 
		// TODO Auto-generated method stub
		AppLogger.d("InterceptChildView | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()));
		return super.onTouchEvent(event);
		
		//这里返回true跟默认的一样
		//如果这里返回false  fatherview和activity的onTouchEvent也都会执行
	}
	
	
}
