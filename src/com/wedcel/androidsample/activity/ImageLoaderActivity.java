package com.wedcel.androidsample.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.wedcel.androidsample.R;
import com.wedcel.androidsample.util.AnimateFirstDisplayListener;
/**
 * ImageLoader 加载并缓存文件 
 * 并且拿到图片的缓存地址使用
 * @author www
 * @data 2014-1-20
 * @timr 下午6:02:34 
 *
 */
public class ImageLoaderActivity extends Activity{
	DisplayImageOptions options;
	private TextView path;
	private ImageView image;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imageload);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_launcher)
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.imageScaleType(ImageScaleType.EXACTLY)
		.displayer(new FadeInBitmapDisplayer(300))//FadeInBitmapDisplayer可以完全显示  圆角的那个不行
		.build();
		imageLoader.getDiscCache();
		path = (TextView) findViewById(R.id.path);
		image = (ImageView) findViewById(R.id.image);
		imageLoader.displayImage("http://a.hiphotos.baidu.com/image/w%3D2048/sign=2f620bfc0846f21fc9345953c21c6a60/cb8065380cd791236f75d173af345982b2b78024.jpg", image, options, new AnimateFirstDisplayListener());
		
		path.setText(StorageUtils.getCacheDirectory(this)+":"+
				new Md5FileNameGenerator().generate("http://a.hiphotos.baidu.com/image/w%3D2048/sign=2f620bfc0846f21fc9345953c21c6a60/cb8065380cd791236f75d173af345982b2b78024.jpg"));
	  }
}
