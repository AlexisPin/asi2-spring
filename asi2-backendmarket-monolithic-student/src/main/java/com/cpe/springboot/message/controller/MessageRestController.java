package com.cpe.springboot.message.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cpe.springboot.common.tools.DTOMapper;
import com.cpe.springboot.message.model.MessageDTO;
import com.cpe.springboot.message.model.MessageModel;

//ONLY FOR TEST NEED ALSO TO ALLOW CROOS ORIGIN ON WEB BROWSER SIDE
@CrossOrigin
@RestController

public class MessageRestController {

	private final MessageService messageService;
	
	public MessageRestController(MessageService messageService) {
		this.messageService=messageService;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/messages/{user1Id}/{user2Id}")
		private List<MessageDTO> getMessagesByUser1IdAndUser2Id(@PathVariable String user1Id, @PathVariable String user2Id) {
		List<MessageDTO> dmessageList = new ArrayList<MessageDTO>();
		List<MessageModel> mmessageList= messageService.getMessagesByUser1IdAndUser2Id(user1Id, user2Id);
		if(mmessageList.size() > 0) {
			for(MessageModel message: mmessageList) {
				dmessageList.add(DTOMapper.fromMessageModelToMessageDTO(message));
			}
			return dmessageList;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User1 id:"+user1Id+ "or User2 id" +user2Id+", not found",null);
	}
	
}
