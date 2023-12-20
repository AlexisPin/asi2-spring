package com.cpe.springboot.conversation.model;

import com.cpe.springboot.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


public class ConversationDTO {
	@Id
	private Integer id;

	private Integer user1Id;
	
    private Integer user2Id;

	public ConversationDTO() {
		super();
	}
	
	public ConversationDTO( ConversationModel cModel) {
		this.id = cModel.getId();
		if (cModel.getUser1() != null) {
			this.user1Id = cModel.getUser1().getId();
		} else {
			this.user1Id = null;
		}
		
		if (cModel.getUser2() != null) {
			this.user1Id = cModel.getUser2().getId();
		} else {
			this.user1Id = null;
		}
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUser1Id() {
		return user1Id;
	}
	
	public void setUser1Id(Integer user1Id) {
		this.user1Id = user1Id;
	}
	
	public Integer getUser2Id() {
		return user2Id;
	}
	
	public void setUser2Id(Integer user2Id) {
		this.user2Id = user2Id;
	}
	


}
