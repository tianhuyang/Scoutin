package com.scoutin.actions.card;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scoutin.entities.Account;
import com.scoutin.entities.Card;
import com.scoutin.entities.CardBody;
import com.scoutin.exception.ScoutinError;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.CardService;
import com.scoutin.utilities.CommonUtils;
import com.scoutin.utilities.JSONUtils;
import com.scoutin.utilities.UploadUtils;
import com.scoutin.vos.card.SaveCardVO;
import com.scoutin.vos.card.SaveCardBodyVO;

public class SaveCardAction extends ActionSupport implements ServletRequestAware
{
	private static final long serialVersionUID = -3691178891261906745L;
	private HttpServletRequest request;
	private Map<String, Object> dataMap;
	public SaveCardVO saveCardVO;
	public SaveCardBodyVO saveCardBodyVO;
	private String method;
	
	public SaveCardAction(){
		dataMap = new HashMap<String, Object>();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		this.method = ActionContext.getContext().getName();
	}
		
	public void validate()
	{
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Card_CreateCard_Input_Status, ScoutinError.Card_CreateCard_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String createCard() throws Exception{
		boolean succeed = true;
		Card card = new Card();
		CardBody CardBody = new CardBody();
		try{
			BeanUtils.copyProperties(card, saveCardVO);
			BeanUtils.copyProperties(CardBody, saveCardBodyVO);
		}catch(IllegalAccessException e1){
			e1.printStackTrace();
		}catch(InvocationTargetException e1){
			e1.printStackTrace();
		}
		Account account = (Account)request.getSession(true).getAttribute("user");
		try{
			String url = UploadUtils.uploadImage(account.getAccountId(), saveCardBodyVO.getFile(), saveCardBodyVO.getFileFileName(), saveCardBodyVO.getFileContentType());
			CardBody.setUrl(url);
			card = CardService.createCard(account.getAccountId(),saveCardVO.getAlbumIds(),card,CardBody);
			dataMap.put("card", card);
		}catch(ScoutinException e){
			succeed = false;
			String localizedMessage = getText(e.getMessage(),e.getMessage());
			JSONUtils.putStatus(dataMap, e.getStatus(), localizedMessage);
		}catch(IOException e){
			succeed = false;
			JSONUtils.putStatus(dataMap, ScoutinError.Image_Upload_Failure_Status, ScoutinError.Image_Upload_Failure_Message);
		}
		
		//success
		if(succeed)
			JSONUtils.putOKStatus(dataMap);
		return SUCCESS;
	}
	
	public String editCard() throws Exception{
		Map<String,Object> cardProperties = new TreeMap<String,Object>();
		Map<String,Object> CardBodyProperties = new TreeMap<String,Object>();
		Map<String,Object> properties = null;
		if(saveCardVO!=null)
			CommonUtils.describe(cardProperties, saveCardVO);
		if(saveCardBodyVO!=null)
			CommonUtils.describe(CardBodyProperties, saveCardBodyVO);
		Account account = (Account)request.getSession(true).getAttribute("user");
		try{
			properties = CardService.editCard(account.getAccountId(), cardProperties, CardBodyProperties);
			JSONUtils.putOKStatus(dataMap);
			dataMap.putAll(properties);
		}catch(ScoutinException e){
			String localizedMessage = getText(e.getMessage(),e.getMessage());
			JSONUtils.putStatus(dataMap, e.getStatus(), localizedMessage);
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	public SaveCardVO getSaveCardVO() {
		return saveCardVO;
	}

	public void setSaveCardVO(SaveCardVO saveCardVO) {
		this.saveCardVO = saveCardVO;
	}

	public SaveCardBodyVO getSaveCardBodyVO() {
		return saveCardBodyVO;
	}

	public void setSaveCardBodyVO(SaveCardBodyVO saveCardBodyVO) {
		this.saveCardBodyVO = saveCardBodyVO;
	}


}
