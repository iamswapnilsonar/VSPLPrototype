package com.vspl.android.prototype.png.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * This class gives the fonts which used in the development wherever it wants. * 
 * @Date 27 May, 2014
 * @author VSPL
 */
public class FontUtils {

	public static Typeface getRobotoLightTypeface(Context context){
		// created custom font "roboto_light.ttf"..
		return Typeface.createFromAsset(context.getAssets(),"fonts/roboto_light.ttf");
	}
	
	public static Typeface getRobotoBackTypeface(Context context){
		// created custom font "roboto_black.ttf"..
		return Typeface.createFromAsset(context.getAssets(),"fonts/roboto_black.ttf");
	}
	
	public static Typeface getRobotoRegularTypeface(Context context){
		// created custom font "roboto_regular.ttf"..
		return Typeface.createFromAsset(context.getAssets(),"fonts/roboto_regular.ttf");
	}
}
