package com.scoutin.actions.card;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scoutin.entities.Account;
import com.scoutin.entities.Comment;
import com.scoutin.exception.ScoutinError;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.CardService;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.JSONUtils;
import com.scoutin.vos.card.SaveCommentVO;

public class SaveCommentAction extends ActionSupport implements ServletRequestAware, ModelDriven<SaveCommentVO>{

	private static final long serialVersionUID = -113156085596203959L;
	private HttpServletRequest request;
	private Map<String, Object> dataMap;
	private SaveCommentVO saveCommentVO;
	
	public SaveCommentAction()
	{
		dataMap = new HashMap<String, Object>();
		saveCommentVO = new SaveCommentVO(); 
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}
	
	@Override
	public SaveCommentVO getModel() {
		// TODO Auto-generated method stub
		return saveCommentVO;
	}
	
	public void validate(){
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Card_CreateComment_Input_Status, ScoutinError.Card_CreateComment_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String createComment() throws Exception
	{
		boolean succeed = true;
		Comment comment = new Comment();
		BeanUtils.copyProperties(comment, saveCommentVO);
		Account account = (Account)request.getSession(true).getAttribute("user");
		try{
			comment = CardService.commentCard(account.getAccountId(),saveCommentVO.getCardId(),comment);
			dataMap.put("comment", comment);
		}catch(ScoutinException e){
			succeed = false;
			String localizedMessage = getText(e.getMessage(),e.getMessage());
			JSONUtils.putStatus(dataMap, e.getStatus(), localizedMessage);
		}
		
		if(succeed)
			JSONUtils.putOKStatus(dataMap);
		
		return SUCCESS;
	}
	
	public String editComment() throws Exception
	{
		Map<String,Object> properties = new TreeMap<String,Object>();
		CommonUtils.describe(properties, saveCommentVO);
		Account account = (Account)request.getSession(true).getAttribute("user");
		properties.put("accountId", account.getAccountId());
		
		return SUCCESS;
	}
	
	public String execute() throws Exception
	{
		return createComment();
	}
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
}
