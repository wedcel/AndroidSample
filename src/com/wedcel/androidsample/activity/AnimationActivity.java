package com.wedcel.androidsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.wedcel.androidsample.R;
import com.wedcel.androidsample.animation.animation.AnimatorSet;
import com.wedcel.androidsample.animation.animation.ObjectAnimator;
import com.wedcel.androidsample.animation.animation.ValueAnimator;
 
/**
 * 用的NineOldAndroids的lib
 * 这个Lib一些参数只支持SDK12以上的
 * 但可以打成jar包用在2.3以下的手机上
 * @author www
 * @data 2014-1-22
 * @timr 下午12:54:34 
 *
 */
public class AnimationActivity extends Activity{
	private Button objanim,valueanim,animview;
	private int height;
	private int aninViewHeight,animViewWidth;
	private TextView text;
	private boolean hasMeasured;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation	);
		
		
		objanim = (Button)findViewById(R.id.objanim);
		valueanim = (Button)findViewById(R.id.valueanim);
		animview = (Button)findViewById(R.id.animview);
		
		//得到view的位置
		ViewTreeObserver vto = animview.getViewTreeObserver();
		  vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
	        {
	            public boolean onPreDraw()
	            {
	                if (hasMeasured == false)//这个方法在之后可能会被反复调用  所以限制下
	                {

	                	aninViewHeight = animview.getMeasuredHeight();
	                	animViewWidth = animview.getMeasuredWidth();
	                
	                	text.setText(" animview.getTop()"+ animview.getTop());
	                    hasMeasured = true;

	                }
	                return true;
	            }
	        });
		
		text = (TextView)findViewById(R.id.text);
		
		height = getWindowManager().getDefaultDisplay().getHeight();
		
		objanim.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				   ObjectAnimator animDown = ObjectAnimator.ofFloat(animview, "y",
						   animview.getTop(), height -aninViewHeight*3).setDuration(500);
				
				   
				   ObjectAnimator animUp = ObjectAnimator.ofFloat(animview, "y",
						   height -aninViewHeight*3, animview.getTop()).setDuration(500);
				   
				   animUp.setInterpolator(new DecelerateInterpolator());
				   
				   AnimatorSet s1 = new AnimatorSet();
				   s1.play(animUp);
//	          /     s1.playSequentially(animDown, animUp);
	               
	               s1.setTarget(animview);
	               s1.start();
			}
		});
		
	
		valueanim.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ValueAnimator anim = new ValueAnimator();
				anim.setFloatValues(100,1);
				anim.setDuration(500);
				anim.setTarget(animview);
				anim.start();
				
			}
		});
		
	
		
  }
	
	
	
	
	 
}
