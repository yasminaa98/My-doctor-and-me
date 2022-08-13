package com.example.medapp.Med_chat;

public class Chat {
    private String sender;
    private String receiver;
    private String Message;
    private boolean isseen;

    public Chat(String sender, String receiver, String Message, boolean isseen) {
        this.sender = sender;
        this.receiver = receiver;
        this.Message = Message;
        this.isseen = isseen;
    }

    public Chat() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public boolean isIsseen() {
        return isseen;
    }

    public void setIsseen(boolean isseen) {
        this.isseen = isseen;
    }

}
