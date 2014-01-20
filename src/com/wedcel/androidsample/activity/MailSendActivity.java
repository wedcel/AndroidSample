package com.wedcel.androidsample.activity;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.wedcel.androidsample.R;

public class MailSendActivity extends Activity{
	private Button 	sendMail,sendMail2,sendHtmlMail,sendHtmlMailFile;
	private TextView showhtml;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mailsend);
		
		sendMail = (Button)findViewById(R.id.sendMail);
		sendMail2 = (Button)findViewById(R.id.sendMail2);
		sendHtmlMail = (Button)findViewById(R.id.sendHtmlMail);
		sendHtmlMailFile = (Button)findViewById(R.id.sendHtmlMailFile);
		showhtml = (TextView)findViewById(R.id.showhtml);
		
		sendMail.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"));
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"369924173@qq.com"});
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject");
				emailIntent.putExtra(Intent.EXTRA_TEXT, "body");
				startActivity(Intent.createChooser(emailIntent, null));
			}
		});
		
		//这种方式  GMAIL不识别body
		sendMail2.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				StringBuffer uriSb = new StringBuffer();
				uriSb.append("mailto:wedcel@163.com")
				.append("?subject=").append("subject")
				.append("&body=").append("body");
				Uri uri = Uri.parse(uriSb.toString());
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
				startActivity(Intent.createChooser(emailIntent, null));
			}
		});	
		
		
		/**
		 * <a href=> url  is OK  but  custome text is not
		 */
		sendHtmlMail.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String body = "<html>   <body>  <div>" +
						"<font size=\"large\" color=\"red\" face=\"italic\">  Recidiverende after (blister, blegner) er en af de hyppigste mundslimhindesygdomme, " +
						"<a href=\"http://dtf.fapprikken.dk/images/figur1.jpg\">http://dtf.fapprikken.dk/images/figur1.jpg</a>" +
						"</div> </body></html>";
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"));
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"369924173@qq.com"});
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject");
				emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(body));
				startActivity(Intent.createChooser(emailIntent, null));
			}
		});
		
		sendHtmlMailFile.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			/*	String body = "<html>   <body>  <div>" +
						"<font size=\"large\" color=\"red\" face=\"italic\">  Recidiverende after (blister, blegner) er en af de hyppigste mundslimhindesygdomme, " +
						"<a href=\"http://dtf.fapprikken.dk/images/figur1.jpg\">http://dtf.fapprikken.dk/images/figur1.jpg</a>" +
						"<a href=\"http://dtf.fapprikken.dk/images/figur1.jpg\">Test a jpg</a>" +
						"</div> </body></html>";*/
				String body = "<font size=\"18\" color=\"red\">测试html的显示</font>"+"" +
						"<img src=\""+Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator
						+"2.jpg"+"\">";
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"));
				//emailIntent.setType("application/octet-stream");
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"369924173@qq.com"});
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject");
				emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(body));
			//	emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+File.separator+"install.txt")));
				startActivity(Intent.createChooser(emailIntent, "请选择邮件发送软件"));
			}
		});
		
		
		showhtml.setText(Html.fromHtml("<font size=\"18\" color=\"red\">测试html的显示</font>"+"" +
				"<img src=\""+Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator
				+"2.jpg"+"\">"));
		System.out.println(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator
				+"2.jpg");
	  }
	
	
}

/*
 * 如果发送html格式的文件
 * 需要用Html.fromHtml
 * 支持以下标签
<a href="...">
<b>
<big>
<blockquote>
<br>
<cite>
<dfn>
<div align="...">
<em>
<font size="..." color="..." face="...">
<h1>
<h2>
<h3>
<h4>
<h5>
<h6>
<i>
<img src="...">
<p>
<small>
<strike>
<strong>
<sub>
<sup>
<tt>
<u>
 * 
 * 1. Re: How do I make the email client display HTML email?
 
Newbie Bronze
hammondnav 2012-4-27 上午11:26 （回复 chriswei）
Mine seems to load HTML emails without any issues. I just sent a test email to my Exchange address and it came through just fine.
 
The option to load HTML emails as HTML is enabled by default, but maybe you can try toggling it off and back on. To get to the option:
Open the Mail app.
Tap Menu (top right corner).
Tap Settings.
Tap Sync, Send & Receive.
Under Message format, you can disable and enable HTML.
 
Hope this helps!
 * 
 * 
 */
