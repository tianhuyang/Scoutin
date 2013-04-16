package com.scoutin.actions.card;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scoutin.exception.ScoutinError;
import com.scoutin.logic.CardService;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.JSONUtils;
import com.scoutin.vos.card.SaveCommentVO;

public class SaveCommentAction extends ActionSupport implements ServletRequestAware, ModelDriven<SaveCommentVO>{

	private static final long serialVersionUID = -113156085596203959L;
	private HttpServletRequest request;
	private Map<String, Object> dataMap;
	private SaveCommentVO comment;
	
	private long cardId;
	
	public SaveCommentAction()
	{
		dataMap = new HashMap<String, Object>();
		comment = new SaveCommentVO(); 
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}
	
	@Override
	public SaveCommentVO getModel() {
		// TODO Auto-generated method stub
		return comment;
	}
	
	public void validate(){
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Account_Signup_Input_Status, ScoutinError.Account_Signup_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String createComment() throws Exception
	{
		Map<String,Object> properties = new TreeMap<String,Object>();
		CommonUtils.describe(properties, comment);
		CardService.commentCard(properties);
		return SUCCESS;
	}
	
	public String editComment() throws Exception
	{
		Map<String,String[]> properties = new TreeMap<String,String[]>();
		properties.putAll(request.getParameterMap());
		return SUCCESS;
	}
	
	public String execute() throws Exception
	{
		return createComment();
	}
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
}
