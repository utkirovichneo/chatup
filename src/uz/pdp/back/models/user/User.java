package uz.pdp.back.models.user;

import uz.pdp.back.models.basemodel.BaseModel;

import java.util.Objects;

public class User extends BaseModel{
    private String fullname;
    private String nickname;
    private String number;
    private String password;

    public User() {
    }
    public User(String fullname, String nickname, String number, String password) {
        this.fullname = fullname;
        this.nickname = nickname;
        this.number = number;
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFullname() {
        return fullname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(fullname, user.fullname)
                && Objects.equals(nickname, user.nickname)
                && Objects.equals(number, user.number)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, nickname, number, password);
    }

    @Override
    public String toString() {
        return "Nickname: %s%nPhone number: %s%n".formatted(getNickname(), getNumber());
    }
}