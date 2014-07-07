package com.vspl.android.prototype.png.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * This class gives the fonts which used in the development wherever it wants. * 
 * @Date 27 May, 2014
 * @author VSPL
 */
public class FontUtils {

	public static Typeface getHelveticaNeueBoldTypeface(Context context){
		// created custom font "helvetica_neue_bold.ttf"..
		return Typeface.createFromAsset(context.getAssets(),"fonts/helvetica_neue_bold.ttf");
	}
	
	public static Typeface getHelveticaNeueTypeface(Context context){
		// created custom font "helvetica_neue.ttf"..
		return Typeface.createFromAsset(context.getAssets(),"fonts/helvetica_neue.ttf");
	}
	
	public static Typeface getHelveticaNeueCondensedBoldTypeface(Context context){
		// created custom font "helvetica_neue_condensed_bold.ttf"..
		return Typeface.createFromAsset(context.getAssets(),"fonts/helvetica_neue_condensed_bold.ttf");
	}
	
	public static Typeface getVerdanaTypeface(Context context){
		// created custom font "verdana.ttf"..
		return Typeface.createFromAsset(context.getAssets(),"fonts/verdana.ttf");
	}
}
