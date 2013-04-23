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
import com.scoutin.vos.card.RepostCardVO;

public class RepostCardAction extends ActionSupport implements ServletRequestAware, ModelDriven<RepostCardVO>{
	
	private static final long serialVersionUID = 886206200486155745L;
	private HttpServletRequest request;
	private RepostCardVO repostCardVO;
	private Map<String, Object> dataMap;
	public RepostCardAction()
	{
		dataMap = new HashMap<String, Object>();
		repostCardVO = new RepostCardVO();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}
	
	@Override
	public RepostCardVO getModel() {
		// TODO Auto-generated method stub
		return repostCardVO;
	}
	
	public void validate(){
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Account_Signup_Input_Status, ScoutinError.Account_Signup_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}

	public String execute() throws Exception
	{
		boolean succeed = true;
		Map<String, Object> properties = new TreeMap<String, Object>();
		CommonUtils.describe(properties, repostCardVO);
		Account account = (Account)request.getSession(true).getAttribute("user");
		properties.put("accountId", account.getAccountId());
		try{
			Card card = CardService.repostCard(properties);
			dataMap.put("card", card);
		}catch(ScoutinException e){
			succeed = false;
			String localizedMessage = getText(e.getMessage(),e.getMessage());
			JSONUtils.putStatus(dataMap, e.getStatus(), localizedMessage);
		}
		
		if(succeed)
			JSONUtils.putOKStatus(dataMap);
		return SUCCESS;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}
}
