package com.wedcel.androidsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.otomod.ad.AdAppBanner;
import com.otomod.ad.AdDynamicBanner;
import com.otomod.ad.AdStaticBanner;
import com.wedcel.androidsample.R;
 

public class AdsBaiLingActivity extends Activity{
	
	private LinearLayout staticads,appads,dynaads;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adbailing);
		
		staticads = (LinearLayout) findViewById(R.id.staticads);
		appads = (LinearLayout) findViewById(R.id.appads);
		dynaads = (LinearLayout) findViewById(R.id.dynaads);
		
	
		
		new AdStaticBanner(this, staticads);
		new AdAppBanner(this, appads);
		new AdDynamicBanner(this, dynaads);
	
		
  }
	
	
	
	
	 
}
