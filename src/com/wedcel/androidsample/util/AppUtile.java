package com.wedcel.androidsample.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

public class AppUtile {
	/**
	 * 判断有否安装
	 * 
	 * @param mContext
	 * @param packagename
	 */
	public static boolean checkInstall(Context mContext, String packagename) {
		// PackageInfo packageInfo;
		// try {
		// packageInfo = mContext.getPackageManager().getPackageInfo(
		// packagename, 0);
		// } catch (NameNotFoundException e) {
		// packageInfo = null;
		// e.printStackTrace();
		// return false;
		// }
		// if (packageInfo == null) {
		// return false;
		// } else {
		// return true;
		// }
		final PackageManager packageManager = mContext.getPackageManager();// 获取packagemanager
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
		List<String> pName = new ArrayList<String>();// 用于存储所有已安装程序的包名
		// 从pinfo中将包名字逐一取出，压入pName list中
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				pName.add(pn);
			}
		}
		return pName.contains(packagename);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE

	}

	/**
	 * 打开某应用
	 * 
	 * @param mContext
	 * @param packagename
	 */
	public static void openApp(Context mContext, String packagename) {
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		PackageManager pm = mContext.getPackageManager();
		List<ResolveInfo> appList = pm.queryIntentActivities(mainIntent, 0);
		int i = 0;
		for (; i < appList.size(); i++) {
			ResolveInfo ri = appList.get(i);
			if (packagename.trim().equals(ri.activityInfo.packageName.trim())) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_LAUNCHER);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setComponent(new ComponentName(
						ri.activityInfo.applicationInfo.packageName,
						ri.activityInfo.name));
				mContext.startActivity(intent);
				// ((Activity) mContext).startActivityForResult(intent, 1);
				break;
			}
		}
	}

	/**
	 * 安装
	 * 
	 * @param mContext
	 * @param packageName
	 */
	public static void installApk(Context mContext, String apkName) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setDataAndType(
				Uri.parse("file://" + "" + apkName),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);

		// String[] args2 = { "chmod", "604", PathGloble.getAppPath()+apkName};
		// Runtime.getRuntime().exec(args2);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// Intent intent = new Intent(Intent.ACTION_VIEW);
		// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// intent.setDataAndType(Uri.parse("file://"
		// +PathGloble.getAppPath()+apkName),
		// "application/vnd.android.package-archive");
		//
		// mContext.startActivity(intent);

	}

	public static void exit(Context mContext) {
		android.os.Process.killProcess(android.os.Process.myPid());
		((Activity) mContext).finish();
	}

	/*
	 * 获取所有手机非系统应用
	 */
	public static String getAllNotSysApp(Context mContext) {
		StringBuffer sb = new StringBuffer();
		List<PackageInfo> packages = mContext.getPackageManager()
				.getInstalledPackages(0);
		for (int i = 0; i < packages.size(); i++) {
			PackageInfo packageInfo = packages.get(i);
			String pagName = packageInfo.packageName;
			if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				sb.append(pagName );// 如果非系统应用，则添加至String
				sb.append("|");
			}

		}
		return sb.toString();
	}


	/**
	 * 获取程序是否在后台运行
	 * 
	 * @param mContext
	 * @return
	 */
	public static boolean isRunBackground(Context mContext) {
		ActivityManager activityManager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			// 应用程序位于堆栈的顶层
			if ("".equals(tasksInfo.get(0).topActivity
					.getPackageName())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断某个服务是否存在
	 * 
	 * @param context
	 * @param className
	 * @return
	 */
	public static boolean isServiceExisted(Context context, String className) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(Integer.MAX_VALUE);

		if (!(serviceList.size() > 0)) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			RunningServiceInfo serviceInfo = serviceList.get(i);
			ComponentName serviceName = serviceInfo.service;

			if (serviceName.getClassName().equals(className)) {
				return true;
			}
		}
		return false;
	}
}
