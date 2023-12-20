package com.cpe.springboot.message.model;

import java.sql.Timestamp;

import com.cpe.springboot.msgreceiver.Message;

public class MessageDTO  {
	private Integer id;
	private String content;
	private Timestamp timestamp;
	private Integer senderId;
	private Integer receiverId;
	

	public MessageDTO() {

	}
	
	

	public MessageDTO(Message message) {
		this.content = message.getContent();
		this.timestamp = message.getTimestamp();
		this.senderId = message.getSenderId();
		this.receiverId = message.getReceiverId();
	}



	public MessageDTO(MessageModel mModel) {
		this.id = mModel.getId();
		this.content = mModel.getContent();
		this.timestamp = mModel.getTimestamp();
		if (mModel.getSender() != null) {
			this.senderId = mModel.getSender().getId();
		} else {
			this.senderId = null;
		}
		
		if (mModel.getConversation() != null) {
			Integer userId = mModel.getConversation().getUser1().getId();
			if(userId == mModel.getSender().getId()) {
				this.receiverId = mModel.getConversation().getUser2().getId();
			}
			this.receiverId = userId;
		} else {
			this.receiverId = null;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
