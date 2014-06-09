package com.vspl.android.prototype.png.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import android.content.Context;

import com.vspl.android.prototype.png.logger.Logger;

/**
 * This class contains functions which used in through out the development.
 * @Date 27 May, 2014
 * @author VSPL
 */
public class MethodUtils {
	
	// get the KEY array form the LinkedHashMap
	public static String[] getKeysArrayFromLinkedHashMap(LinkedHashMap<String, String> data){

		String[] mKeys;		
		mKeys = data.keySet().toArray(new String[data.size()]);
		Logger.vLog("MethodsUtils -> getKeysArrayFromLinkedHashMap", "mKeys size : "+mKeys.length);
		return mKeys;
	}
	
	public static List<String> getArrayList(Context context, String arrName){
		
		Logger.vLog("getArrayList", "Array Name : "+ arrName);
		List<String> mResArrayList;		
		List<String> mResMutableList = null;
		
		if (arrName.equalsIgnoreCase("Earrings")) {
			
			// returns immutable string converts to MUTABLE
			mResArrayList = Arrays.asList(ConstantUtils.earrings);
			mResMutableList = new ArrayList<String>(mResArrayList);
			
		}else if (arrName.equalsIgnoreCase("Necklaces")) {
			
			// returns immutable string converts to MUTABLE
			mResArrayList = Arrays.asList(ConstantUtils.necklaces);
			mResMutableList = new ArrayList<String>(mResArrayList);
			
		}
		
		return mResMutableList;
	}
}
