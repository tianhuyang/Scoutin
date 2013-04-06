package com.scoutin.logic;

import javax.ejb.Remote;

import com.scoutin.entities.Card;

@Remote
public interface CardBeanRemote {

	public Card createCard(Card card);
}
