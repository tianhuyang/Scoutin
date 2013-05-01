package com.scoutin.actions.card;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
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
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public RepostCardVO getModel() {
		// TODO Auto-generated method stub
		return repostCardVO;
	}
	
	public void validate(){
		super.validate();
		if(this.hasFieldErrors()){
			JSONUtils.putStatus(dataMap, ScoutinError.Card_RepostCard_Input_Status, ScoutinError.Card_RepostCard_Input_Message);
			dataMap.put("fieldErrors", this.getFieldErrors());
		}
	}
	
	public String execute() throws Exception
	{
		boolean succeed = true;
		Card card = new Card();
		try{
			BeanUtils.copyProperties(card, repostCardVO);
		}catch(IllegalAccessException e1){
			e1.printStackTrace();
		}catch(InvocationTargetException e1){
			e1.printStackTrace();
		}
		Account account = (Account)request.getSession(true).getAttribute("user");
		try{
			card = CardService.repostCard(account.getAccountId(),repostCardVO.getAlbumIds(),card,repostCardVO.getCardbodyId());
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
