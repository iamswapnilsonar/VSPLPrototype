package com.vspl.android.prototype.png.logger;

import android.util.Log;

/**
 * This class is for debugging purpose, is should manage with "isDebug" flag.
 * @Date 28 May, 2014
 * @author VSPL
 */
public class Logger {
	
	private static boolean isDebug = true;
	public static boolean isLiveApplication = true;
	
	
	public static void vLog(String tag, String msg) {
		if (isDebug)
			Log.v("PNG Prototype : ", tag + " => " + msg);
	}

	public static void wLog(String tag, String msg) {
		if (isDebug)
			Log.w("PNG Prototype : ", tag + " => " + msg);
	}

	public static void eLog(String tag, String msg) {
		if (isDebug)
			Log.e("PNG Prototype : ", tag + " => " + msg);
	}

	public static void sLog(String msg) {
		if (isDebug)
			System.out.println("FPNG Prototype : " + msg);
	}
}
