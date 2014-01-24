package com.wedcel.androidsample.activity;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wedcel.androidsample.R;
 

public class HtmlFormatActivity extends Activity{
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
		
		
		String html = "<img src =\"file://"+Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"2.png"
				+"\"> <h1>测试数据test  data</h1><br><font color=#C90643 font-family=Verdana-Italic>The second data</font>";
		//Html.fromHtml(html, imageGetter, tagHandler);
		textView.setText( Html.fromHtml(html,new MyImageGetter(),null));
		
		
		linear.addView(textView);
		view.addView(linear);
		setContentView(view);
		
		
		
  }
	
	
	
	
	private class MyImageGetter implements Html.ImageGetter {

	    @Override
	    public Drawable getDrawable(String arg0) {
	        Bitmap bitmap;
	        try {
	            bitmap = BitmapFactory.decodeStream(
	                    (InputStream) new URL(arg0).getContent(), null, null);
	            @SuppressWarnings("deprecation")
	            Drawable drawable = new BitmapDrawable(bitmap);
	            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
	                    drawable.getIntrinsicHeight());
	            return drawable;
	        } catch (Exception e) {
	            Drawable d = getResources().getDrawable(R.drawable.ic_launcher);
	            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
	            return d;
	        }
	    }
	}
}
