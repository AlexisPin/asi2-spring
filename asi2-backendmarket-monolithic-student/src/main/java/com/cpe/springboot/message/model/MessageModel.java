package com.cpe.springboot.message.model;

import com.cpe.springboot.conversation.model.ConversationModel;
import com.cpe.springboot.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.model.UserDTO;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class MessageModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String content;
	private Timestamp timestamp;

	@ManyToOne()
    @JoinColumn()
    private UserModel sender;
	
	@ManyToOne()
    @JoinColumn()
    private ConversationModel conversation;

	public MessageModel() {
		super();
	}
	
	public MessageModel( MessageModel mModel) {
		this.content=mModel.getContent();
		this.timestamp=mModel.getTimestamp();
	}

	public MessageModel (String content, Timestamp timestamp, UserModel sender, ConversationModel conversation) {
        this.content = content;
        this.timestamp = timestamp;
        this.sender=sender;
        this.conversation=conversation;
    }
	
	public MessageModel(MessageDTO message) {
		this.id=message.getId();
		this.content=message.getContent();
		this.timestamp=message.getTimestamp();
	
	}
	
	public UserModel getSender() {
		return sender;
	}

	public void setSender(UserModel sender) {
		this.sender = sender;
	}
	
	public ConversationModel getConversation() {
		return conversation;
	}

	public void setConversation(ConversationModel conversation) {
		this.conversation = conversation;
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

	
	

}
