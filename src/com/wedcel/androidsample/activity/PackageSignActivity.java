package com.wedcel.androidsample.activity;

import com.wedcel.androidsample.util.DeviceUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
 /**
  * 获取apk签名的haskcode  防止篡改打包
  * @author www
  * @data 2014-1-20
  * @timr 下午6:35:12 
  *
  */
public class PackageSignActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ScrollView view = new ScrollView(this);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		view.setScrollbarFadingEnabled(false);
		
		LinearLayout linear = new LinearLayout(this);
		linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		linear.setOrientation(LinearLayout.VERTICAL);
		
		TextView textView = new TextView(this);
		textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		textView.setText("签名文件char:"+DeviceUtils.getSinChar(this));
		
		TextView textView2 = new TextView(this);
		textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		textView2.setText("签名文件hashcode:"+DeviceUtils.getSinHash(this));
		
		linear.addView(textView2);
		linear.addView(textView);
		view.addView(linear);
		setContentView(view);
		
  }
}
