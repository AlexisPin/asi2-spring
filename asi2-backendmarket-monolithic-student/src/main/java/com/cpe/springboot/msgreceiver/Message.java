package com.cpe.springboot.msgreceiver;

import java.sql.Timestamp;

public class Message {

	private String content;
	private Timestamp timestamp;
	private Integer senderId;
	private Integer receiverId;

    public Message() {
    }

    public Message(String content, Timestamp timestamp, Integer senderId, Integer receiverId) {
		super();
		this.content = content;
		this.timestamp = timestamp;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	
}
