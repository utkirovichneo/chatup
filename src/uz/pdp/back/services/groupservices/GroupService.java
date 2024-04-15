package uz.pdp.back.services.groupservices;

import uz.pdp.back.models.message.Message;
import uz.pdp.back.services.baseservice.BaseService;

import java.util.List;

public interface GroupService{
    void createGroup(String name);
    void deleteGroup(String name);
    void sendMessage(String chatId, String message);
    List<Message> getMessages(String chatId);
    void subscribeGroup(String chatId, String groupId);
}