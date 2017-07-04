package com.mcflykid.crawler.lib;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateLib {

	public static String getNowDate(String format){
		return new SimpleDateFormat(format).format(new Date());
	}
	
	public static String getNowDate(){
		return new SimpleDateFormat("HH-mm-ss-dd-MM-yyy").format(new Date());
	}
}
