package network;

import java.io.Serializable;

public class Message implements Serializable {
    private MessageType messageType;
    private MessageContentType messageContentType;
    private String content;

    public Message(MessageType type, MessageContentType contentType, String content){
        this.messageType = type;
        this.messageContentType = contentType;
    }

    public MessageType getType(){
        return messageType;
    }

    public MessageContentType getContentType(){
        return messageContentType;
    }

    public String getContent(){
        return content;
    }

    public void setMessageType(MessageType type) {
        this.messageType = type;
    }

    public void setMessageContentType(MessageContentType contentType){
        this.messageContentType = contentType;
    }

    public void setContent(String content){
        this.content = content;
    }
}
