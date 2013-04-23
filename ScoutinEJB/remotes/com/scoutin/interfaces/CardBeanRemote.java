package com.scoutin.interfaces;

import java.util.Map;

import javax.ejb.Remote;

import com.scoutin.entities.Card;
import com.scoutin.entities.Comment;

@Remote
public interface CardBeanRemote {

	public Card createCard(Map<String,Object> properties);
	public Card repostCard(Map<String,Object> properties);
	public Map<String,Object> editCard(Map<String,Object> properties);
	public Comment commentCard(Map<String, Object> properties);
}
