package com.wedcel.androidsample.util;

import java.util.ArrayList;
import java.util.List;

import android.text.Html;
import android.text.Spanned;

public abstract class StringUtils {
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 清除str前面的不可见字符
	 * 
	 * @param str
	 * @return
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	/**
	 * 使用delimiter来分割str，生成数组
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static String[] delimitedListToStringArray(String str,
			String delimiter) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] { str };
		}

		List<String> result = new ArrayList<String>();
		if ("".equals(delimiter)) {
			result.add(str);
		} else {
			int pos = 0;
			int delPos = 0;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(str.substring(pos, delPos));
				pos = delPos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) {
				// Add rest of String, but not in case of empty input.
				result.add(str.substring(pos));
			}
		}
		return result.toArray(new String[result.size()]);
	}
	
	
	
	/**
	 * 格式化用户账号显示信息
	 * @param src 
	 * @param type 1.支付宝提现，2,财富通3,QQ币，4话费
	 * @return
	 */
	public static String formatString(String src,int type){
		String dest = "";
		switch(type){
		case 1:
			if(src.contains("@")){
				dest = "支付宝账号("+src.substring(0, 1)+"****"+src.substring(src.indexOf("@"), src.length())+")";
			}else{
				dest = "支付宝账号("+(src.length()>=5?src.substring(0, src.length()-4):src)+"****"+")";
			}
			break;
		case 2:
			dest = "财付通账号("+(src.length()>=5?src.substring(0, src.length()-4):src)+"****"+")";
			break;
		case 3:
			dest = "QQ("+(src.length()>=5?src.substring(0, src.length()-4):src)+"****"+")";
			break;
		case 4:
			dest = "电话号码("+(src.length()>=5?src.substring(0, src.length()-4):src)+"****"+")";
			break;
	
		}
		
		
		return dest;
	}
	
	public static Spanned getErrorString(String msg){
		 return Html.fromHtml("<font color=#000>"+msg+"</font>");
	}
}
