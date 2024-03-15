package uz.pdp.back.services.userservice;

import uz.pdp.back.models.user.User;
import uz.pdp.back.services.baseservice.BaseService;

public interface UserService extends BaseService<User>{
    User login(String number, String password);
    String searchUser(String phoneNumber);
    String searchWithNick(String nick);
}