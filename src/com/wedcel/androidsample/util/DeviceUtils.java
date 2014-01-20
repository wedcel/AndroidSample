package com.wedcel.androidsample.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.WindowManager;

public class DeviceUtils {

	public DeviceUtils() {
		super();
	}

	/**
	 * gps是否可用
	 * 
	 * @param context
	 * @return
	 */

	public static boolean isGpsEnable(Context context) {
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * 判断能否使用网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isHasNetWork(Context context) {
		// ConnectivityManager conMan = (ConnectivityManager) context
		// .getSystemService(Context.CONNECTIVITY_SERVICE);
		// android.net.NetworkInfo.State mobile = conMan.getNetworkInfo(
		// ConnectivityManager.TYPE_MOBILE).getState();
		// android.net.NetworkInfo.State wifi = conMan.getNetworkInfo(
		// ConnectivityManager.TYPE_WIFI).getState();
		//
		// if (mobile == android.net.NetworkInfo.State.CONNECTED
		// || wifi == android.net.NetworkInfo.State.CONNECTED) {
		// return true;
		// }
		// return false;

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = cm.getActiveNetworkInfo();
		// if(network != null){
		// return network.isAvailable();
		// }
		// return false;

		if (network == null || !network.isConnected()) {
			return false;
		}
		if (network.isRoaming()) {
			return true;
		}
		return true;
	}

	/**
	 * 判断当前网络是否是wifi
	 * 
	 * @param context
	 */
	public static boolean IsWiFi(Context context) {
		ConnectivityManager connectMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectMgr.getActiveNetworkInfo();
		if (info != null) {
			if (info.getType() == ConnectivityManager.TYPE_WIFI) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否为飞行模式
	 * 
	 * @param context
	 * @return
	 */
	public static boolean IsAirModeOn(Context context) {
		return (Settings.System.getInt(context.getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, 0) == 1 ? true : false);
	}

  
	/**
	 * 判断SD是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isCanUseSd(Context context) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {

			return false;
		}
	}

	/**
	 * 根据分辨率判断是否平板
	 * 
	 * @param mContext
	 * @return
	 */
	public static boolean istable(Context mContext) {
		Boolean r = false;
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			Configuration con = mContext.getResources().getConfiguration();
			try {
				Method mIsLayoutSizeAtLeast = con.getClass().getMethod(
						"isLayoutSizeAtLeast", int.class);
				r = (Boolean) mIsLayoutSizeAtLeast.invoke(con, 0x00000004); // Configuration.SCREENLAYOUT_SIZE_XLARGE
				return r;
			} catch (Exception x) {
				x.printStackTrace();
				r = false;
			}
		}
		return r;
	}

	/**
	 * 判断是平板还是手机
	 * 
	 * @return
	 */
	public static boolean isTabletDevice(Context mContext) {
		boolean is = false;
		try {
			TelephonyManager telephony = (TelephonyManager) mContext
					.getSystemService(Context.TELEPHONY_SERVICE);
			int type = telephony.getPhoneType();
			if (istable(mContext)) {
				// 分辨率判断是平板，可能是分辨率大的手机，然后根据能否通话判断是否平板
				if (type == TelephonyManager.PHONE_TYPE_NONE) {
					// Tablet
					is = true;
				} else {
					// phone
					is = false;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return is;
	}

	/**
	 * 判断是否是平板 谷歌IO提供的 官方方法
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	/**
	 * 获取手机唯一识别码 imei 设备序列号 没卡不变
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		String MAC = "0";
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			if (telephonyManager.getDeviceId()!=null) {
				MAC = telephonyManager.getDeviceId();
			}
			
		} catch (Exception ex) {

		}
		return MAC;
	}

	/**
	 * 获取IMSI 卡的序列号 变卡就变
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMSI(Context context) {
		String IMSI = "0";
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			if (telephonyManager.getSubscriberId()!=null) {
				IMSI = telephonyManager.getSubscriberId();
				
			}
		} catch (Exception ex) {

		}
		return IMSI;
	}

	/**
	 * 获取androidID
	 * 
	 * @param context
	 * @return
	 */
	public static String getAndroidID(Context context) {
		String androidId = "0";
		androidId = Settings.Secure.getString(context.getContentResolver(),
				Settings.Secure.ANDROID_ID);
		return androidId;
	}

	/**
	 * 获取蓝牙地址
	 */
	public static String getBlueTooth(Context mContext) {
		try {
			BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
			if (btAdapter.getAddress() != null) {
				return btAdapter.getAddress().replaceAll(":", "-");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getMAC(Context mContext) {
		try {
			WifiManager wifi = (WifiManager) mContext
					.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifi.getConnectionInfo();
			if (info.getMacAddress() != null) {
				String str = info.getMacAddress();
				return str.replaceAll(":", "-");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getSerialnum() {
		String serialnum = "0";
		try {
			Class<?> c = Class.forName("android.os.SystemProperties");

			Method get = c.getMethod("get", String.class, String.class);

			serialnum = (String) (get.invoke(c, "ro.serialno", "unknown"));

		} catch (Exception ignored) {
			ignored.printStackTrace();
		}
		return serialnum;

	}
	
 
	/**
	 * 获取设备型号
	 * 
	 * @return
	 */
	public static String getModel() {
		return Build.MODEL;
	}

	/**
	 * 获取操作系统版本
	 * 
	 * @return
	 */
	public static String getOSVesion() {
		return Build.VERSION.RELEASE;

	}

	/**
	 * 获取网络类型 获取手机卡类型，移动、联通、电信 根据imsi卡号，判定属于拿个运营商
	 * 
	 * @return
	 */
	public static String getNetType(Context mContext) {
		String type = "";
		try {
			TelephonyManager iPhoneManager = (TelephonyManager) mContext
					.getSystemService(Context.TELEPHONY_SERVICE);
			String iNumeric = iPhoneManager.getSimOperator();
			if (iNumeric.length() > 0) {
				if (iNumeric.equals("46000") || iNumeric.equals("46002")) {
					type = "中国移动";
				} else if (iNumeric.equals("46001")) {
					type = "中国联通";
				} else if (iNumeric.equals("46003")) {
					type = "中国电信";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return type;

	}

	/**
	 * 获取CPU型号
	 * 
	 * @return// 1-cpu型号 //2-cpu频率
	 */
	public static String[] getCpuMsg() {
		String str1 = "/proc/cpuinfo";
		String str2 = "";
		String[] cpuInfo = { "", "" };
		String[] arrayOfString;
		try {
			FileReader fr = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
			str2 = localBufferedReader.readLine();
			arrayOfString = str2.split("\\s+");
			for (int i = 2; i < arrayOfString.length; i++) {
				cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
			}
			str2 = localBufferedReader.readLine();
			arrayOfString = str2.split("\\s+");
			cpuInfo[1] += arrayOfString[2];
			localBufferedReader.close();
		} catch (IOException e) {
		}
		return cpuInfo;

	}

	/**
	 * 获取分辨率
	 * 
	 * @return
	 */
	public static Integer[] getResolution(Context mContext) {
		// 在service中也能得到高和宽
		Integer[] a = new Integer[2];
		int width;
		int height;
		WindowManager mWindowManager = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		width = mWindowManager.getDefaultDisplay().getWidth();
		height = mWindowManager.getDefaultDisplay().getHeight();
		a[0] = width;
		a[1] = height;
		return a;

	}

	/**
	 * 当前可用内存大小
	 * 
	 * @return
	 */
//	public static String getAvailMemory(Context mContext) {
//		// 获取android当前可用内存大小
//		ActivityManager am = (ActivityManager) mContext
//				.getSystemService(Context.ACTIVITY_SERVICE);
//		MemoryInfo mi = new MemoryInfo();
//		am.getMemoryInfo(mi);
//		// mi.avaiMem;当前系统可用内存
//		return Formatter.formatFileSize(((Activity) mContext).getBaseContext(),
//				mi.availMem);
//		// 将获得的内存大小规格化
//
//	}

	/**
	 * 获取所有内存
	 * 
	 * @return
	 */
	public static String getTotalMemory(Context mContext) {
		String str1 = "/proc/meminfo";// 系统内存信息文件
		String str2;
		String[] arrayOfString;
		long initial_memory = 0;

		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(
					localFileReader, 8192);
			str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统内存大小
			arrayOfString = str2.split("\\s+");
			for (String num : arrayOfString) {
				Log.i(str2, num + "\t");
			}
			initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位KB
			localBufferedReader.close();
		} catch (IOException e) {

		}
		return Formatter.formatFileSize(((Activity) mContext).getBaseContext(),
				initial_memory);
		// Byte转位KB或MB

	}

	/**
	 * 获取是否越狱
	 * 
	 * @return
	 */
	public static boolean isRoot() {
		boolean bool = false;
		try {
			if ((!new File("/system/bin/su").exists())
					&& (!new File("/system/xbin/su").exists())) {
				bool = false;
			} else {
				bool = true;
			}
		} catch (Exception e) {
		}
		return bool;
	}

	/**
	 * 获取包名
	 * 
	 * @param context
	 * @return
	 */
	public static String getCurProcessName(Context context) {
		int pid = android.os.Process.myPid();
		ActivityManager mActivityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
				.getRunningAppProcesses()) {
			if (appProcess.pid == pid) {
				return appProcess.processName;
			}
		}
		return null;
	}

	/**
	 * 获取版本name信息
	 * 
	 * @return
	 */
	public static String getVersion(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e1) {
			e1.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取版本name信息
	 * 
	 * @return
	 */
	public static int getVersionCode(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	/**
	 * 判断是否有sd卡
	 * 
	 * @return
	 */
	public static boolean isHasSDCard() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取当前的手机号
	 * 
	 * @return
	 */
	public static String getLocalNumber(Context mContext) {
		try {
			TelephonyManager tManager = (TelephonyManager) mContext
					.getSystemService(Context.TELEPHONY_SERVICE);
			if (tManager.getLine1Number()!=null) {
				return tManager.getLine1Number();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	/**
	 * 根据IMEI判断是否是虚拟机 没有IMEI的设备默认不合法
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isEmulator(Context context) {
		try {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			String imei = tm.getDeviceId();
			if ((imei != null && imei.equals("000000000000000"))
					|| TextUtils.isEmpty(imei)) {
				return true;
			}
			return (Build.MODEL.equals("sdk"))
					|| (Build.MODEL.equals("google_sdk"));
		} catch (Exception ioe) {

		}
		return false;
	}
	
	
	
	
	public static String getSinChar(Context context){
		PackageManager pm = context.getPackageManager();
		PackageInfo pi = null;
		try {
			pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Signature[] signs = pi.signatures;
		return signs[0].toCharsString();
	}
	
	
	public static int getSinHash(Context context){
		PackageManager pm = context.getPackageManager();
		PackageInfo pi = null;
		try {
			pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Signature[] signs = pi.signatures;
		return signs[0].hashCode();
	}
}
