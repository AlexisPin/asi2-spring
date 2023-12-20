package com.cpe.springboot.conversation.model;

import com.cpe.springboot.card.model.CardModel;
import com.cpe.springboot.message.model.MessageModel;
import com.cpe.springboot.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class ConversationModel {
	@Id
	private Integer id;

	@OneToOne()
    @JoinColumn()
    private UserModel user1;
	
	@OneToOne()
    @JoinColumn()
    private UserModel user2;
	
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy = "conversation")
	private Set<MessageModel> messageList = new HashSet<>();

	public ConversationModel() {
		super();
	}
	
	public ConversationModel( ConversationModel cModel) {
		this.id = cModel.getId();
	}

	 public ConversationModel(UserModel user1, UserModel user2) {
	        this.user1 = user1;
	        this.user2 = user2;
	 }
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public UserModel getUser1() {
		return user1;
	}

	public void setUser1(UserModel user1) {
		this.user1 = user1;
	}

	public UserModel getUser2() {
		return user2;
	}

	public void setUser2(UserModel user2) {
		this.user2 = user2;
	}
	
	public Set<MessageModel> getMessageList() {
		return messageList;
	}

	public void setMessageList(Set<MessageModel> messageList) {
		this.messageList = messageList;
	}

	public void addAllMessageList(Collection<MessageModel> messageList) {
		this.messageList.addAll(messageList);
	}


	public void addMessage(MessageModel message) {
		this.messageList.add(message);
		message.setConversation(this);
	}

	private boolean checkIfMessage(MessageModel m){
		for(MessageModel m_m: this.messageList){
			if(m_m.getId()==m.getId()){
				return true;
			}
		}
		return false;
	}
}
