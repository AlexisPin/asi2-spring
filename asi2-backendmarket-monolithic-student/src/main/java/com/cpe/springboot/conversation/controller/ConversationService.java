package com.cpe.springboot.conversation.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cpe.springboot.card.model.CardModel;
import com.cpe.springboot.common.tools.DTOMapper;
import com.cpe.springboot.conversation.model.ConversationModel;
import com.cpe.springboot.user.model.UserModel;
import com.model.UserDTO;


@Service
public class ConversationService {

	private final ConversationRepository conversationRepository;

	public ConversationService(ConversationRepository conversationRepository) {
		this.conversationRepository = conversationRepository;
	}

	public Optional<ConversationModel> getConversationByUser1IdAndUser2Id(String user1Id, String user2Id) {
		 Optional<ConversationModel> message =  conversationRepository.findByUser1IdAndUser2Id(Integer.valueOf(user1Id),Integer.valueOf(user2Id) );
		if(message.isPresent()) {
			return message;
		}
		return conversationRepository.findByUser1IdAndUser2Id(Integer.valueOf(user2Id),Integer.valueOf(user1Id));
	}
	
	public Optional<ConversationModel> getConversationByUser1IdAndUser2Id(Integer user1Id, Integer user2Id) {
		 Optional<ConversationModel> message =  conversationRepository.findByUser1IdAndUser2Id(user1Id,user2Id );
		if(message.isPresent()) {
			return message;
		}
		return conversationRepository.findByUser1IdAndUser2Id(user2Id,user1Id );
	}
	
	public ConversationModel addConversation(ConversationModel conversation) {
		return conversationRepository.save(conversation);
	}

}
