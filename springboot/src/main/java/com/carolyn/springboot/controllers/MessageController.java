package com.carolyn.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carolyn.springboot.entities.Message;
import com.carolyn.springboot.services.MessageService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="api/message")
public class MessageController {


    @Autowired
    private final MessageService messageService;

    
 public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }



  @GetMapping(path="/get")
  public  ResponseEntity<List<Message>> getAllMessage() {
    
    return ResponseEntity.ok().body(messageService.findAllMessage());
}
 

@GetMapping(path="/get/{user_firstName}/{receiver_firstName}")


public ResponseEntity<List<Message>> getAllConversation(@PathVariable("user_firstName") String senderName, @PathVariable("receiver_firstName") String receiverName) {
    return ResponseEntity.ok().body(messageService.findAllConversation(senderName, receiverName));

}
  

  @PostMapping(path="/create-message")
  public ResponseEntity< Message> createMessage(@RequestBody Message message)  {

    System.out.println(message);
      return ResponseEntity.ok().body(messageService.createNewMessage(message));

  }

    
}
