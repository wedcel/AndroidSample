package com.wedcel.androidsample.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.wedcel.androidsample.view.CustomeLayout;
 
 
public class CustomLayoutActivity extends Activity{
	
 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.activity_customlayout);
		 
		CustomeLayout layout  = new CustomeLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);
		
		LinearLayout linearLayout1= new LinearLayout(this);
		linearLayout1.setBackgroundColor(Color.WHITE);
		
		
		LinearLayout linearLayout2 = new LinearLayout(this);
		linearLayout2.setBackgroundColor(Color.BLACK);
		layout.addView(linearLayout1);
		layout.addView(linearLayout2);
		
		setContentView(layout);
  }
	
	
	
	
	 
}
