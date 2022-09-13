package com.carolyn.springboot.entities;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity(name = "message")
@Table(name = "message")
public class Message {

   
    @Id
    @SequenceGenerator(name ="message_sequence", sequenceName = "message_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
    generator = "message_sequence")
    private Long id;


    @Column(name = "sender_name",  columnDefinition = "TEXT")
    private String  senderName;
    @Column(name = "receiver_name",  columnDefinition = "TEXT")
    private String  receiverName;
    @Column(columnDefinition = "TEXT")
    private String message;
    @Column(name ="image_url", columnDefinition = "TEXT")
    private String imageUrl;
    private Status status;
    

    @CreationTimestamp
    @Column(name = "create_on")
    private LocalDateTime createdAt;
    


    
    public Message() {
    }



    public Message(String senderName, String receiverName, String message, Status status, String imageUrl) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.message = message;
        this.status = status;
        this.imageUrl=imageUrl;
    }



    

    public String getImageUrl() {
        return imageUrl;
    }



    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public String getSenderName() {
        return senderName;
    }



    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }



    public String getReceiverName() {
        return receiverName;
    }



    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }



    public String getMessage() {
        return message;
    }



    public void setMessage(String message) {
        this.message = message;
    }



    public Status getStatus() {
        return status;
    }



    public void setStatus(Status status) {
        this.status = status;
    }



    

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }



    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    @Override
    public String toString() {
        return "Message [createdAt=" + createdAt + ", id=" + id + ", message=" + message + ", receiverName="
                + receiverName + ", senderName=" + senderName + ", status=" + status + "]";
    }


  
   


  
   





    
    
}
