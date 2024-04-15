package uz.pdp.back.services.groupservices;

import uz.pdp.back.models.groups.Group;
import uz.pdp.back.models.message.Message;

import java.util.ArrayList;
import java.util.List;

public class GroupServiceimp implements GroupService{
    private List<Group> groups;

    public GroupServiceimp() {
        groups = new ArrayList<Group>();
    }

    @Override
    public void createGroup(String name) {
        
    }

    @Override
    public void deleteGroup(String name) {

    }

    @Override
    public void sendMessage(String chatId, String message) {

    }

    @Override
    public List<Message> getMessages(String chatId) {
        return List.of();
    }

    @Override
    public void subscribeGroup(String chatId, String groupId) {

    }
}