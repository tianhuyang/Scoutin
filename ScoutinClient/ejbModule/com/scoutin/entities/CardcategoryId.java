package com.scoutin.entities;

// Generated Apr 6, 2013 8:06:34 AM by Hibernate Tools 4.0.0

/**
 * CardcategoryId generated by hbm2java
 */
public class CardcategoryId implements java.io.Serializable {

	private short categoryId;
	private long cardId;

	public CardcategoryId() {
	}

	public CardcategoryId(short categoryId, long cardId) {
		this.categoryId = categoryId;
		this.cardId = cardId;
	}

	public short getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(short categoryId) {
		this.categoryId = categoryId;
	}

	public long getCardId() {
		return this.cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CardcategoryId))
			return false;
		CardcategoryId castOther = (CardcategoryId) other;

		return (this.getCategoryId() == castOther.getCategoryId())
				&& (this.getCardId() == castOther.getCardId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCategoryId();
		result = 37 * result + (int) this.getCardId();
		return result;
	}

}
