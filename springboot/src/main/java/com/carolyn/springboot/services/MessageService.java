package com.carolyn.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carolyn.springboot.entities.Message;
import com.carolyn.springboot.repositories.MessageRepository;

@Service
@Transactional
public class MessageService {

    @Autowired
    private final MessageRepository messageRepository;



    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAllMessage() {
        return messageRepository.findAll();
    }

    public Message createNewMessage(Message message) {
        Message newMessage = new Message();
        newMessage.setReceiverName(message.getReceiverName());
        newMessage.setSenderName(message.getSenderName());
        newMessage.setMessage(message.getMessage());

        System.out.println(newMessage);

        return  messageRepository.save(newMessage);
    }

    public List<Message> findAllConversation(String senderName, String receiverName) {
        return messageRepository.findMessages(senderName, receiverName);
    }


   



    
}
