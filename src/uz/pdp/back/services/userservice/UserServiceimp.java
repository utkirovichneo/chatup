package uz.pdp.back.services.userservice;

import uz.pdp.back.models.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserServiceimp implements UserService{

    private List<User> userList;

    public UserServiceimp() {
        this.userList = new ArrayList<>();
        userList.add(new User("Pavel Durov", "Pavel_Durov", "1", "1"));
    }

    @Override
    public void create(User user) {
        if (user == null) {
            return;
        }
            userList.add(user);
    }

    public User edit(String id, String newNickName) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                user.setNickname(newNickName);
                return user;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                userList.remove(user);
                return;
            }
        }
    }

    @Override
    public User get(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String searchUser(String phoneNumber) {
        String id = null;
        for (User user : userList) {
            if (user.getNumber().equals(phoneNumber)) {
                id = user.getId();
            }
        }
        return id;
    }

    @Override
    public String searchWithNick(String nick) {
        for (User user : userList) {
            if (Objects.equals(user.getNickname(), nick)) {
             return user.getId();
            }
        }
        return null;
     }

    @Override
    public List<User> getList() {
        return userList;
    }

    @Override
    public User login(String number, String password) {
        for (User user : userList) {
            if (user.getNumber().equals(number)&&
                    user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
