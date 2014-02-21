package com.wedcel.androidsample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class CustomeLayout extends RelativeLayout {
	private Context context;
	
	public CustomeLayout(Context context) {
		super(context); 
		CustomeLayout.this.context = context;
	}
	
	public CustomeLayout(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		CustomeLayout.this.context = context;
	}

	public CustomeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		CustomeLayout.this.context = context;
	}

 
	
	/**
	 * 这个方法里可以得到一个其他资源
	 */
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}

	/**
	 * 对拖拽图片不同的点击事件处理
	 */
	/*		@Override
public boolean onTouchEvent(MotionEvent event) {
		int X = (int) event.getX();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: 
			AppLogger.d("ACTION_DOWN");
			isUp = false;
			locationX = (int) event.getX();
			Log.i(TAG, "是否点击到位=" + isActionDown(event));
			return isActionDown(event);//判断是否点击了滑动区域
			
		case MotionEvent.ACTION_MOVE: //保存x轴方向，绘制图画
			AppLogger.d("ACTION_MOVE");
			isUp = false;
			locationX = X;
			invalidate(); //重新绘图
			return true;
			
		case MotionEvent.ACTION_UP: //判断是否解锁成功
			
		AppLogger.d("ACTION_UP");
			isUp = true;
			if(!isLocked()){ //没有解锁成功,动画应该回退
				handleActionUpEvent(event); //动画回退
			}
			return true;
		}return super.onTouchEvent(event);
	}
	*/
 
 
 
/*	*//**
	 * 绘制拖动时的图片
	 *//*
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	*/
	
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
			View view = getChildAt(0);
			view.layout(l, 200, r,b/2);
		
			View view1 = getChildAt(1);
			view1.layout(l,  b/2, r,  b);
			
		super.onLayout(changed, l, t, r, b);
	}
 
 
	/**
	 * 获取屏幕宽度
	 * @return
	 */
	private int getScreenWidth(){
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int width = manager.getDefaultDisplay().getWidth();
		return width;
	}
	
	/**
	 * 获取屏幕宽度
	 * @return
	 */
	private int getScreenHeight(){
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int height = manager.getDefaultDisplay().getHeight();
		return height;
	}
 
 
}
