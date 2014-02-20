package com.wedcel.androidsample.view;

import com.wedcel.androidsample.util.AppLogger;
import com.wedcel.androidsample.util.TouchEventUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 *
 */
public class InterceptFatherView extends LinearLayout {

	
     public InterceptFatherView(Context context) {
        super(context);
        AppLogger.d("InterceptFatherView context");
        setOrientation(VERTICAL);
    }

    public InterceptFatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        AppLogger.d("InterceptFatherView context");
        setOrientation(VERTICAL);
    }

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		  AppLogger.d("onFinishInflate");
		super.onFinishInflate();
	}

	
	
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		 AppLogger.d("canvas");
		super.onDraw(canvas);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		 AppLogger.d("onLayout");
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		 AppLogger.d("onMeasure");
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 *  Activity 的 dispatchTouchEvent(MotionEvent ev) 
	 *  方法会以隧道方式（从根元素依次往下传递直到最内层子元素或在中间某一元素中由于某一条件停止传递）
	 *  将事件传递给最外层 View 的 dispatchTouchEvent(MotionEvent ev) 方法，
	 *  并由该 View 的 dispatchTouchEvent(MotionEvent ev) 方法对事件进行分发。
	 *  dispatchTouchEvent 的事件分发逻辑如下：
	 *  
	 *  如果 return true，事件会分发给当前 View 并由 dispatchTouchEvent 方法进行消费，同时事件会停止向下传递；
		如果 return false，事件分发分为两种情况：
		如果当前 View 获取的事件直接来自 Activity，则会将事件返回给 Activity 的 onTouchEvent 进行消费；
		如果当前 View 获取的事件来自外层父控件，则会将事件返回给父 View 的  onTouchEvent 进行消费。
		如果返回系统默认的 super.dispatchTouchEvent(ev)，事件会自动的分发给当前 View 的 onInterceptTouchEvent 方法。
	 *  
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {//事件分发
		// TODO Auto-generated method stub
		AppLogger.d("InterceptFatherView | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		return super.dispatchTouchEvent(ev);
		
		//如果这里返回true 则得到dispatchTouchEvent（activity）--this 之后事件不往下传递 activity的onTouchEvent最终不会执行 事件不分发
		//如果返回false 则将事件返回给activity activity的onTouchEvent最终会执行
	} 
	/**
	 * 在外层 View 的 dispatchTouchEvent(MotionEvent ev) 方法返回系统默认的 super.dispatchTouchEvent(ev) 情况下，
	 * 事件会自动的分发给当前 View 的 onInterceptTouchEvent 方法。onInterceptTouchEvent 的事件拦截逻辑如下：
		•如果 onInterceptTouchEvent 返回 true，则表示将事件进行拦截，并将拦截到的事件交由当前 View 的 onTouchEvent 进行处理；
		•如果 onInterceptTouchEvent 返回 false，则表示将事件放行，当前 View 上的事件会被传递到子 View 上，
		再由子 View 的 dispatchTouchEvent 来开始这个事件的分发；
		•如果 onInterceptTouchEvent 返回 super.onInterceptTouchEvent(ev)，事件默认会被拦截，
		并将拦截到的事件交由当前 View 的 onTouchEvent 进行处理。
	 * 
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {//事件拦截 
		// TODO Auto-generated method stub
		AppLogger.d("InterceptFatherView | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		return super.onInterceptTouchEvent(ev);
		//如果这里返回true则截下来这个view里的onTouchEvent会执行 并且activity里的onTouchEvent也会执行
		//如果返回false 则跟默认结果一样
	} 
  
	/**
	 * 在 dispatchTouchEvent 返回 super.dispatchTouchEvent(ev) 
	 * 并且 onInterceptTouchEvent 返回 true 或返回 super.onInterceptTouchEvent(ev) 的情况下 onTouchEvent 会被调用。o
	 * nTouchEvent 的事件响应逻辑如下：
	•如果事件传递到当前 View 的 onTouchEvent 方法，而该方法返回了 false，那么这个事件会从当前 View 向上传递，
	并且都是由上层 View 的 onTouchEvent 来接收，如果传递到上面的 onTouchEvent 也返回 false，这个事件就会“消失”，而且接收不到下一次事件。
	•如果返回了 true 则会接收并消费该事件。
	•如果返回 super.onTouchEvent(ev) 默认处理事件的逻辑和返回 false 时相同。
	到这里，与 Touch 事件相关的三个方法就分析完毕了。下面的内容会通过各种不同的的测试案例来验证上文中三个方法对事件的处理逻辑。
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {//事件响应 
		// TODO Auto-generated method stub
		AppLogger.d("InterceptFatherView | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()));
		return super.onTouchEvent(event);
		//	return true;
		
		//正常情况下 这里不会执行 他执行不执行 得看下层view的返回结果
	}
}
