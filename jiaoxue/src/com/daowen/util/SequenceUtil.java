package com.daowen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SequenceUtil {

	public static  String buildSequence(String prefix){
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHMMss");
		String timestamp = sdf.format(new Date());
		String index=String.valueOf((int)(Math.random()*100));
		index=padLeft(index, 3, "0");
		return  prefix+timestamp+index;
		
	}
	
	/**
	* ÓÒ²¹Î»£¬×ó¶ÔÆë 
	* 
	* @param oriStr
	*            Ô­×Ö·û´® 
	* @param len
	*            Ä¿±ê×Ö·û´®³¤¶È 
	* @param alexin
	*            ²¹Î»×Ö·û 
	* @return Ä¿±ê×Ö·û´® 
	*/
	public static String padRight(String oriStr, int len, String alexin) {
		String str = "";
		int strlen = oriStr.length();
		if (strlen < len) {
			for (int i = 0; i < len - strlen; i++) {
				str = str + alexin;
			}
		}
		str = str + oriStr;
		return str;
	}

	/**
	* ×ó²¹Î»£¬ÓÒ¶ÔÆë
	* 
	* @param oriStr
	*            Ô­×Ö·û´®
	* @param len
	*            Ä¿±ê×Ö·û´®³¤¶È
	* @param alexin
	*            ²¹Î»×Ö·û
	* @return Ä¿±ê×Ö·û´®
	*/
	public static String padLeft(String oriStr, int len, String alexin) {
		String str = "";
		int strlen = oriStr.length();
		if (strlen < len) {
			for (int i = 0; i < len - strlen; i++) {
				str = str + alexin;
			}
		}
		str = oriStr + str;
		return str;
	}
	
	
	
}
	
	
	

