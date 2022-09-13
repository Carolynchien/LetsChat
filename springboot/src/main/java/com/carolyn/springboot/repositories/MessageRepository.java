package com.carolyn.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import com.carolyn.springboot.entities.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

    @Query(value="SELECT * FROM  message WHERE(sender_name=:senderName AND receiver_name=:receiverName)OR(sender_name=:receiverName AND receiver_name=:senderName)", nativeQuery = true)
   List<Message> findMessages (String senderName, String receiverName);

   @Query(value="SELECT * FROM message",nativeQuery = true)
   List<Message> findAll();


  
    

    
}
