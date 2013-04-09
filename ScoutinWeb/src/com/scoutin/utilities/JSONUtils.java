package com.scoutin.utilities;

import java.util.Map;

import com.scoutin.exception.ScoutinError;

public class JSONUtils implements JSONConstants {

	public static String statusToJSONString(int status, String msg) {
		return '"' + JSONConstants.KYE_STATUS + "\":" + status + ",\""
				+ JSONConstants.KYE_MESSAGE + "\":\"" + msg + '"';
	}

	public static String OKJSONString() {
		return '"' + JSONConstants.KYE_STATUS + "\":" + ScoutinError.OK_Status
				+ ",\"" + JSONConstants.KYE_MESSAGE + "\":\""
				+ ScoutinError.OK_Message + '"';

	}
	public static void putStatus(Map<String,Object> map,int status,String message){
		map.put(JSONConstants.KYE_STATUS , status);
		map.put(JSONConstants.KYE_MESSAGE , message);
	}
	
	public static void putOKStatus(Map<String,Object> map){
		map.put(JSONConstants.KYE_STATUS , ScoutinError.OK_Status);
		map.put(JSONConstants.KYE_MESSAGE , ScoutinError.OK_Message);
	}

}
