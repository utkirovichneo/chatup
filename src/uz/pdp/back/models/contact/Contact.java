package uz.pdp.back.models.contact;

import uz.pdp.back.models.basemodel.BaseModel;

public class Contact extends BaseModel {
    private String name;
    private String number;
    private String userID;
    private String contactUserID;

    public Contact() {
    }

    public Contact(String name, String number, String contactUserID, String userID) {
        this.name = name;
        this.number = number;
        this.contactUserID = contactUserID;
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getUserID() {
        return userID;
    }
    public String getContactUserID(){
        return contactUserID;
    }

    @Override
    public String toString() {
        return "Name: %s%nPhone number: %s%n".formatted(getName(), getNumber());
    }
}
