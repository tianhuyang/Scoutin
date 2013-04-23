package com.scoutin.actions.card;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scoutin.entities.Account;
import com.scoutin.entities.Card;
import com.scoutin.exception.ScoutinError;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.CardService;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.JSONUtils;
import com.scoutin.vos.card.SaveCardVO;

public class SaveCardAction extends ActionSupport implements ServletRequestAware, ModelDriven<SaveCardVO>
{
	private static final long serialVersionUID = -3691178891261906745L;
	private HttpServletRequest request;
	private Map<String, Object> dataMap;
	private SaveCardVO saveCardVO;
		
	public SaveCardAction(){
		dataMap = new HashMap<String, Object>();
		saveCardVO = new SaveCardVO();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}
	
	@Override
	public SaveCardVO getModel() {
		// TODO Auto-generated method stub
		return saveCardVO;
	}
	
	public void validate()
	{
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Account_Signup_Input_Status, ScoutinError.Account_Signup_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String createCard() throws Exception{
		boolean succeed = true;
		Map<String,Object> properties = new TreeMap<String,Object>();
		CommonUtils.describe(properties, saveCardVO);
		Account account = (Account)request.getSession(true).getAttribute("user");
		properties.put("accountId",account.getAccountId());
		try{
			Card card = CardService.createCard(properties);
			dataMap.put("card", card);
		}catch(ScoutinException e){
			succeed = false;
			String localizedMessage = getText(e.getMessage(),e.getMessage());
			JSONUtils.putStatus(dataMap, e.getStatus(), localizedMessage);
		}
		
		//success
		if(succeed)
			JSONUtils.putOKStatus(dataMap);
		return SUCCESS;
	}
	
	public String editCard() throws Exception{
		Map<String,Object> properties = new TreeMap<String,Object>();
		CommonUtils.describe(properties, saveCardVO);
		Account account = (Account)request.getSession(true).getAttribute("user");
		properties.put("accountId",account.getAccountId());
		
		return SUCCESS;
	}
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

}