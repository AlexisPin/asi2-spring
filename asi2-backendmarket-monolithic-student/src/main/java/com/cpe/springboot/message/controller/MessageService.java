package com.cpe.springboot.message.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cpe.springboot.card.Controller.CardModelService;
import com.cpe.springboot.card.model.CardModel;
import com.cpe.springboot.common.tools.DTOMapper;
import com.cpe.springboot.conversation.controller.ConversationService;
import com.cpe.springboot.conversation.model.ConversationModel;
import com.cpe.springboot.message.model.MessageDTO;
import com.cpe.springboot.message.model.MessageModel;
import com.model.UserDTO;
import com.cpe.springboot.user.controller.UserService;
import com.cpe.springboot.user.model.UserModel;

@Service
public class MessageService {

	private final MessageRepository messageRepository;
	private final ConversationService conversationService;
	private final UserService userService;

	public MessageService(MessageRepository messageRepository, ConversationService conversationService, UserService userService) {
		this.messageRepository = messageRepository;
		this.conversationService = conversationService;
		this.userService= userService;
	}

	public List<MessageModel> getMessagesByUser1IdAndUser2Id(String user1Id, String user2Id) {
		Optional<ConversationModel> conversation = conversationService.getConversationByUser1IdAndUser2Id(user1Id, user2Id);
		if(conversation.isPresent()) {
			return messageRepository.findByConversationId(conversation.get().getId());
		}
		return null;
	}

	public MessageDTO addMessage(MessageDTO message) {
		ConversationModel conversation;
		Optional<ConversationModel> oConversation = conversationService.getConversationByUser1IdAndUser2Id(message.getReceiverId(), message.getSenderId());
		
		Optional<UserModel> sender = userService.getUser(message.getSenderId());
		Optional<UserModel> receiver = userService.getUser(message.getReceiverId());
		
		if(sender.isEmpty() && receiver.isEmpty()) {
			return null;
		}
		
		if(oConversation.isEmpty()) {
			conversation = new ConversationModel(sender.get(), receiver.get());
			conversationService.addConversation(conversation);
			
		}
		else {
			conversation = oConversation.get();
		}
		MessageModel newMessage = new MessageModel(message.getContent(), message.getTimestamp(), sender.get(), conversation);
		
		MessageModel mBd = messageRepository.save(newMessage);
		
		return DTOMapper.fromMessageModelToMessageDTO(mBd);
	}
}
