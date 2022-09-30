package com.carolyn.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.carolyn.springboot.entities.Appuser;
import com.carolyn.springboot.entities.Chat;
import com.carolyn.springboot.entities.Message;

import com.carolyn.springboot.repositories.AppUserRepository;
import com.carolyn.springboot.repositories.ChatRepositoyr;

import com.carolyn.springboot.repositories.MessageRepository;
import com.carolyn.springboot.services.AppuserService;
import com.carolyn.springboot.services.LikeServide;

enum Status { MESSAGE}



@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private final LikeServide likeService;
    @Autowired
    private final AppuserService appuserService;
    @Autowired
    private final AppUserRepository appUserRepository;
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final ChatRepositoyr chatRepositoyr;



    public ChatController(LikeServide likeService, AppuserService appuserService, AppUserRepository appUserRepository, MessageRepository messageRepository,ChatRepositoyr chatRepositoyr) {
        this.likeService = likeService;
        this.appuserService = appuserService;
        this.appUserRepository = appUserRepository;
        this.messageRepository = messageRepository;
        this.chatRepositoyr =chatRepositoyr;
    }



    @MessageMapping("/private-message")
    public Message receivePrivate(@Payload Message message) {

        Appuser sender = appUserRepository.findByFirstName(message.getSenderName());

        
        if(checkFirstTimeChatting(message.getSenderName(), message.getReceiverName())) {
           
            Appuser receiver = appUserRepository.findByFirstName(message.getReceiverName());

            Chat chatInSender = new Chat();
            chatInSender.setUser(sender);
            chatInSender.setFirstName(receiver.getFirstName());
            chatInSender.setImageUrl(receiver.getImageUrl());
            chatInSender.setPersonId(receiver.getId());

            Chat chatInReceiver = new Chat();
            chatInReceiver.setUser(receiver);
            chatInReceiver.setFirstName(sender.getFirstName());
            chatInReceiver.setImageUrl(sender.getImageUrl());
            chatInReceiver.setPersonId(sender.getId());

            chatRepositoyr.save(chatInSender);
            chatRepositoyr.save(chatInReceiver);
            System.out.print(sender.getImageUrl());
      

        }


        
  
        System.out.print(message.getReceiverName());
        System.out.print(message.getStatus());

   
            Message newMessage = new Message();
            newMessage.setReceiverName(message.getReceiverName());
            newMessage.setMessage(message.getMessage());
            newMessage.setSenderName(message.getSenderName());
            messageRepository.save(newMessage);


            message.setImageUrl(sender.getImageUrl());
        



        simpMessagingTemplate.convertAndSend("/topic/"+message.getReceiverName()+"/private", message);
        // simpMessagingTemplate.convertAndSendToUser("/topic/"+message.getReceiverName(),"/private", message); //uer/David/private
        return message;

    }


    public boolean checkFirstTimeChatting( String senderFirtName, String receiverFirstName) {
        Appuser sender = appUserRepository.findByFirstName(senderFirtName);
      
        for(Chat chat: sender.getChats()) {
            if(chat.getFirstName().equals(receiverFirstName)){
                return false;
            }
        }

        return true;

        

    }

    
}
