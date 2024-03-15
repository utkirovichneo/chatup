package uz.pdp.back.models.message;

import uz.pdp.back.enums.StatusMessage;
import uz.pdp.back.models.basemodel.BaseModel;

import java.time.LocalTime;
import java.util.Objects;
import java.util.StringJoiner;

public class Message extends BaseModel{
    private String body;
    private String toUserID;
    private String userID;
    private StatusMessage status;
    private String date;

    public Message() {
    }

    public Message(String body, String toUserID, String userID) {
        this.body = body;
        this.toUserID = toUserID;
        this.userID = userID;
        this.status = StatusMessage.was_not_read;
            LocalTime localTime = LocalTime.now();
        this.date  = "%s:%s".formatted(localTime.getHour(), localTime.getMinute());
    }
    public String getBody() {
        return body;
    }
    public String getToUserID(){
        return toUserID;
    }
    public String getUserID() {
        return userID;
    }
    public StatusMessage getStatus() {
        return status;
    }

    public void editStatus(){
        this.status = StatusMessage.was_read;
    }
    public String getLocaleDate(){
        return date;
    }

    @Override
    public String toString() {
        var sj = new StringJoiner("\n");
        sj.add(getBody());
        sj.add(this.date);
        sj.add((Objects.equals(this.status, StatusMessage.was_read)?"✅":"🕛"));
        return sj.toString();
    }
}