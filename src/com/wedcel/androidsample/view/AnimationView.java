package com.wedcel.androidsample.view;

import com.wedcel.androidsample.R;
import com.wedcel.androidsample.animation.animation.ObjectAnimator;
import com.wedcel.androidsample.util.AppLogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 *
 */
public class AnimationView extends LinearLayout {

 
	private Button view1,view2;
	private int x,y,width,height,top,bottom;
	private boolean isLayout = true;
     public AnimationView(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }
 
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		view1 = (Button) findViewById(R.id.view1);
		view2 = (Button) findViewById(R.id.view2);
	/*	AppLogger.d("view1.getWidth():"+view1.getWidth()+"---view1.getHeight()"+view1.getHeight()+
				"view1.getLeft()"+view1.getLeft()+"view1.getTop()"+view1.getTop()+"view1.getRight()"+view1.getRight()+"view1.getBottom()"+view1.getBottom());
		
		AppLogger.d("view2.getWidth():"+view2.getWidth()+"---view2.getHeight()"+view2.getHeight()+
				"view2.getLeft()"+view2.getLeft()+"view2.getTop()"+view2.getTop()+"view2.getRight()"+view2.getRight()+"view2.getBottom()"+view2.getBottom());

	*/}

	
/*	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {//事件分发
		// TODO Auto-generated method stub
		
		width = view1.getWidth();
		height = view1.getHeight();
		int right = view1.getRight();
		int left = view1.getLeft();
		top = view1.getTop();
		bottom = view1.getBottom();
		
		x = (int)ev.getX();
		y = (int)ev.getY();
		
		AppLogger.d("view1.getWidth():"+view1.getWidth()+"---view1.getHeight()"+view1.getHeight()+
				"view1.getLeft()"+view1.getLeft()+"view1.getTop()"+view1.getTop()+"view1.getRight()"+view1.getRight()+"view1.getBottom()"+view1.getBottom()
				+"x=="+x+"y=="+y
				);
		//  requestLayout(); //重绘VIEW
		return super.dispatchTouchEvent(ev);
		
		
	} */
	
	private boolean isActionDown(MotionEvent event) {
		Rect rect = new Rect();
		view1.getHitRect(rect);
		boolean isIn = rect.contains((int)event.getX()-view1.getWidth()/2, (int)event.getY());
		if(isIn){
		//	heartView.setVisibility(View.GONE);
			return true;
		}
		return false;
	}
	
  
	@Override
	public boolean onTouchEvent(MotionEvent event) {//事件响应 
		// TODO Auto-generated method stub
		int x = (int) event.getX();
		switch(event.getAction()){
		 case MotionEvent.ACTION_DOWN:
			 AppLogger.d("ACTION_DOWN---"+isActionDown(event));
			 return isActionDown(event);
			 
			 
		 case MotionEvent.ACTION_MOVE:
			 AppLogger.d("ACTION_MOVE");
			 AppLogger.d("x=="+x+"y=="+y);
			 ObjectAnimator anim = ObjectAnimator.ofFloat(view1,"x",(int) event.getX());
			 anim.start();
			 
			  return true;
		 case MotionEvent.ACTION_UP:
			 AppLogger.d("ACTION_UP");
			 ObjectAnimator anim1 = ObjectAnimator.ofFloat(view1,"x",0);
			 anim1.start();
			 return true;
		 
			 
		 }
		return super.onTouchEvent(event);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}
	
	/*@Override
	public void invalidate(int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		view1.layout(l, t, r, b);
		AppLogger.d("invalidate");
		super.invalidate(l, t, r, b);
	}
	
	 @Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		AppLogger.d("onLayout");
		if(isLayout){
			view1.layout(100, 300, 300, 500);
		}
	}*/

}
