package uz.pdp.back.models.groups;

import uz.pdp.back.models.basemodel.BaseModel;

public class Group extends BaseModel {
    private String groupName;
    private String userID;
    private String body;

    public Group(String groupName, String userID, String body) {
        this.groupName = groupName;
        this.userID = userID;
        this.body = body;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", userID='" + userID + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
