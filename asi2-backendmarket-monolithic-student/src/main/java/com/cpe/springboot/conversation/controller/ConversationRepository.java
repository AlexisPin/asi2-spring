package com.cpe.springboot.conversation.controller;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cpe.springboot.conversation.model.ConversationModel;

public interface ConversationRepository extends CrudRepository<ConversationModel, Integer> {
	
	Optional<ConversationModel> findByUser1IdAndUser2Id(Integer user1Id,Integer user2Id);

}