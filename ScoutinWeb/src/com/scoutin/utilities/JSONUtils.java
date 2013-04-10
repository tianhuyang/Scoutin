package com.scoutin.utilities;

import java.util.Map;

import com.scoutin.exception.ScoutinError;

public class JSONUtils implements JSONConstants {

	public static String statusToJSONString(int status, String msg) {
		return '"' + JSONConstants.KEY_STATUS + "\":" + status + ",\""
				+ JSONConstants.KEY_MESSAGE + "\":\"" + msg + '"';
	}

	public static String OKJSONString() {
		return '"' + JSONConstants.KEY_STATUS + "\":" + ScoutinError.OK_Status
				+ ",\"" + JSONConstants.KEY_MESSAGE + "\":\""
				+ ScoutinError.OK_Message + '"';

	}
	public static void putStatus(Map<String,Object> map,int status,String message){
		map.put(JSONConstants.KEY_STATUS , status);
		map.put(JSONConstants.KEY_MESSAGE , message);
	}
	
	public static void putOKStatus(Map<String,Object> map){
		map.put(JSONConstants.KEY_STATUS , ScoutinError.OK_Status);
		map.put(JSONConstants.KEY_MESSAGE , ScoutinError.OK_Message);
	}

}
