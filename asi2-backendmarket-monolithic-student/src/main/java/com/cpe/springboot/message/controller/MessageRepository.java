package com.cpe.springboot.message.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cpe.springboot.message.model.MessageModel;

public interface MessageRepository extends CrudRepository<MessageModel, Integer> {
	
	List<MessageModel> findByConversationId(Integer conversationId);

}