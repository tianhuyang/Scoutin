package com.scoutin.entities;

// Generated Apr 15, 2013 6:05:19 AM by Hibernate Tools 4.0.0

/**
 * CardrepostsId generated by hbm2java
 */
public class CardrepostsId implements java.io.Serializable {

	private int accountId;
	private long cardId;

	public CardrepostsId() {
	}

	public CardrepostsId(int accountId, long cardId) {
		this.accountId = accountId;
		this.cardId = cardId;
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
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
		if (!(other instanceof CardrepostsId))
			return false;
		CardrepostsId castOther = (CardrepostsId) other;

		return (this.getAccountId() == castOther.getAccountId())
				&& (this.getCardId() == castOther.getCardId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAccountId();
		result = 37 * result + (int) this.getCardId();
		return result;
	}

}
