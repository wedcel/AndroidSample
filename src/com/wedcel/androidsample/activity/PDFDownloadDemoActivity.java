package com.wedcel.androidsample.activity;

import java.io.File;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.wedcel.androidsample.R;
import com.wedcel.androidsample.util.file.FileDownloaderHttpHelper.DownloadListener;
import com.wedcel.androidsample.util.http.JavaHttpUtility;

public class PDFDownloadDemoActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	 
		Button button = (Button)findViewById(R.id.button);
		button.setText("下载文件");
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new DownloadAsyn().execute("");
		            
			}
		});
		
	  }
	
	private class DownloadAsyn extends AsyncTask<String, Integer, Boolean> {
		@Override
		protected Boolean doInBackground(String... reqs) {
		
				
			
			new JavaHttpUtility().doGetSaveFile("http://www.odont.ku.dk/docs/henvisningsblanket_oral_medicin.pdf",Environment.getExternalStorageDirectory().getAbsolutePath()+
					File.separator+"henvisningsblanket_oral_medicin.pdf", new DownloadListener(){
				@Override
				public void pushProgress(int progress, int max) {
					// TODO Auto-generated method stub
					super.pushProgress(progress, max);
					
				}
				@Override
				public void completed() {
					// TODO Auto-generated method stub
					Looper.prepare();
					Looper.loop();
					Toast.makeText(PDFDownloadDemoActivity.this,"下载已经完成！", Toast.LENGTH_SHORT).show();
					super.completed();
				}
				@Override
				public void cancel() {
					// TODO Auto-generated method stub
					super.cancel();
				}
			});
			return true;
		}
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
	}
}
 