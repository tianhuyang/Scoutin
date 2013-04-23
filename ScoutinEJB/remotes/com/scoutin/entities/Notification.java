package com.scoutin.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Notification entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NOTIFICATION", catalog = "Scoutin")
public class Notification implements java.io.Serializable {

	// Fields

	private Long notificationId;

	// Constructors

	/** default constructor */
	public Notification() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "NOTIFICATION_ID", unique = true, nullable = false)
	public Long getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

}