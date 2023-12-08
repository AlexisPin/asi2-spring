package com.cpe.springboot.msgreceiver;

import com.cpe.springboot.user.controller.UserService;
import com.cpe.springboot.user.model.UserDTO;
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

    private void doReceive(String busName, TextMessage message) {
        try {
            String clazz = message.getStringProperty("ObjectType");
            Object o = objectMapper.readValue(message.getText(), Class.forName(clazz));

            if (o instanceof RichUserDTO) {
                RichUserDTO user = (RichUserDTO)o;
                System.out.println(user.getAction());
                switch (user.getAction()) {
                    case DELETE:
                        break;
                    case UDPATE:
                        userService.updateUser(user.getUser());
                        break;
                    case CREATE:
                        break;
                }
            }
            System.out.println("[BUSLISTENER] [CHANNEL "+busName+"] RECEIVED String MSG=["+message.getText()+"]");
        } catch (IOException | JMSException | ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(destination = "queue.update_user", containerFactory = "queueConnectionFactory")
    public void receiveMessageResult(TextMessage message) {
        doReceive("queue.update_user\"", message);
    }
}
