package com.cpe.springboot.msgreceiver;

import com.cpe.springboot.message.controller.MessageService;
import com.cpe.springboot.message.model.MessageDTO;
import com.cpe.springboot.user.controller.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.RichUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;

@Component
public class BusListener {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired 
    MessageService messageService;

    private void doReceive(String busName, TextMessage message) {
        try {
            String clazz = message.getStringProperty("ObjectType");
            Object o = objectMapper.readValue(message.getText(), Class.forName(clazz));

            if (o instanceof RichUserDTO) {
                RichUserDTO user = (RichUserDTO)o;
                System.out.println(user.getAction());
                switch (user.getAction()) {
                    case DELETE:
                        userService.deleteUser(String.valueOf(user.getUser().getId()));
                        break;
                    case UPDATE:
                        userService.updateUser(user.getUser());
                        break;
                    case CREATE:
                        userService.addUser(user.getUser());
                        break;
                }
            }
            if(o instanceof Message) {
            	Message mMessage = (Message)o;
            	MessageDTO messageDto = new MessageDTO(mMessage);
            	messageService.addMessage(messageDto);
            	
            }
            System.out.println("[BUSLISTENER] [CHANNEL "+busName+"] RECEIVED String MSG=["+message.getText()+"]");
        } catch (IOException | JMSException | ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(destination = "queue.update_user", containerFactory = "queueConnectionFactory")
    public void receiveMessageResult(TextMessage message) {
        doReceive("queue.update_user", message);
    }
    
    @JmsListener(destination = "queue.chat_messages", containerFactory = "queueConnectionFactory")
    public void receiveChatMessageResult(TextMessage message) {
        doReceive("queue.chat_messages", message);
    }
}
