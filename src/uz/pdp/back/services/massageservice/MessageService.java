package uz.pdp.back.services.massageservice;

import uz.pdp.back.models.message.Message;

import java.util.List;

public interface MessageService {
    void sendMessage(Message message);
    List<Message> ourCorrespondence(String contactID, String userID);
    List<String> correspondenceID(String userID);
    List<String> newMessageUserID(String userID);
}