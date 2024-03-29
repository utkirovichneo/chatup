package uz.pdp.back.services.massageservice;

import uz.pdp.back.enums.StatusMessage;
import uz.pdp.back.models.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageServiceimp implements MessageService {
    private List<Message> messages;
    public MessageServiceimp() {
        this.messages = new ArrayList<>();
    }
    @Override
    public void sendMessage(Message message) {
        messages.add(message);
    }
    @Override
    public List<Message> ourCorrespondence(String contactID, String userID) {
        List<Message> messageList = new ArrayList<>();
        for (Message message : messages) {
            if (Objects.equals(message.getFirstID(),contactID)&&
                    Objects.equals(message.getSecondID(),userID)||
            Objects.equals(message.getFirstID(),userID)&&
            Objects.equals(message.getSecondID(),contactID)) {
                messageList.add(message);
            }
        }
        return messageList;
    }
    @Override
    public List<String> correspondenceID(String userID) {
        var cpdID = new ArrayList<String>();
        for (Message message : messages) {
            if(Objects.equals(message.getSecondID(), userID)){
                if(cpdID.contains(message.getFirstID())){continue;}
                cpdID.add(message.getFirstID());
            }
            if(Objects.equals(message.getFirstID(), userID)) {
                if(cpdID.contains(message.getSecondID())){continue;}
                cpdID.add(message.getSecondID());
            }
        }
        return cpdID;
    }

    @Override
    public List<String> newMessageUserID(String userID) {
        var newMUID = new ArrayList<String>();
        List<String> cpdIDs = correspondenceID(userID);
        for (String cpdID : cpdIDs) {
            List<Message> messageList = ourCorrespondence(cpdID, userID);
            if(messageList.getLast().getStatus().equals(StatusMessage.was_not_read)){
                newMUID.add(cpdID);
            }
        }
        return newMUID;
    }
}