package com.scoutin.logic;

import java.util.Map;

import javax.ejb.Remote;

import com.scoutin.entities.Card;
import com.scoutin.entities.Comment;

@Remote
public interface CardBeanRemote {

	public Card createCard(Map<String,Object> properties);
	public Card repostCard(int cardId, long albumId);
	public Comment commentCard(Comment comment);
}
