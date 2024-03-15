package uz.pdp.front.ui;

import uz.pdp.back.enums.StatusMessage;
import uz.pdp.back.models.contact.Contact;
import uz.pdp.back.models.message.Message;
import uz.pdp.back.models.user.User;
import uz.pdp.back.services.contactservice.ContactService;
import uz.pdp.back.services.contactservice.ContactServiceimp;
import uz.pdp.back.services.massageservice.MessageService;
import uz.pdp.back.services.massageservice.MessageServiceimp;
import uz.pdp.back.services.userservice.UserService;
import uz.pdp.back.services.userservice.UserServiceimp;

import java.util.List;
import java.util.Objects;

import static uz.pdp.front.utils.Util.*;
import static uz.pdp.front.utils.UtilMain.*;

public class UI {

    private static final UserService userService = new UserServiceimp();
    private static User user = new User();
    private static final MessageService messageService = new MessageServiceimp();
    private static final ContactService contactService = new ContactServiceimp();
    private static Contact contact = new Contact();

    public static void main(String[] args) {
        loop1 : while (true) {
            signMenu();
            switch (enterInt("Menuni tanlang...")) {
                case 1 -> {
                    user = signUp();
                    if(user != null){
                        loop2 : while (true) {
                            menu();
                            switch (enterInt("Manuni tanlang...")) {
                                case 1 -> {
                                    loop3 : while (true) {
                                        telegramMenu();
                                        switch (enterInt("Tanlang...")) {
                                            case 1 -> newMessage();
                                            case 2 -> correspondence();
                                            case 3 -> writeOnContact();
                                            case 4 -> writeOnNick();
                                            case 0 -> {
                                                break loop3;
                                            }
                                            default -> {
                                            }
                                        }
                                    }
                                }
                                case 2 -> getMyContact();
                                case 3 -> createContact();
                                case 0 -> {break loop2;}
                                default -> System.out.println("Xato tanlov🛑\n");
                            }
                        }
                    }
                    else {
                        System.out.println("Bunday account mavjud emas😒😒😒");
                    }
                }
                case 2 -> signIn();
                case 0 -> {
                    break loop1;
                }
                default -> System.out.println("Xato tanlov🛑");
            }
        }
    }
    private static void signIn(){
        String fullname = enterStr("Ismingizni kiriting: ");
        String nickName = enterStr("Nick name kiriting: ");
        String phoneNumber = enterStr("Telefon raqamingizni kiriting: ");
        String password = enterStr("Parol kiriting: ");
        String isThere = userService.searchUser(phoneNumber);
            if(isThere == null){
                userService.create(new User(fullname, nickName, phoneNumber, password));
                System.out.println("Account yaratildi✅✅✅");
                System.out.println("========================\n");
            }
            else {
                System.out.println("Bunday raqam oldin ro'yxatdan o'tgan🛑🛑🛑");
            }
    }

    private static User signUp(){
        String number = enterStr("Raqamingizni kiriting: ");
        String password = enterStr("Parolni kiriting: ");
        return userService.login(number, password);
    }

    private static void createContact(){
        String contactName = enterStr("Ismini kiriting: ");
        String contactNumber = enterStr("Raqamini kiriting: ");
        String userID = user.getId();
        String contactID = userService.searchUser(contactNumber);
        if(contactID!= null){
                contactService.create(new Contact(contactName, contactNumber, contactID, userID));
                System.out.println("Kontakt saqlandi✅✅✅");
            }
            else {
                System.out.println("Bunday foydalanuvchi ro'yxatdan o'tmagan🛑🛑🛑");
            }
    }
    private static void getMyContact(){
        List<Contact> contactByID = contactService.getContactByID(user.getId());
        for (Contact contact1 : contactByID) {
            System.out.println(contact1);
        }
    }
    private static void sendSMS(String firstID, String secondID){
        messageService.sendMessage(new Message(
                enterStr("\t\t\t\tXabar yozing...\n\t\t\t\t"),
                firstID,
                secondID));
    }
    private static void writeOnContact(){
        List<Contact> contactByID = contactService.getContactByID(user.getId());
            if(contactByID.isEmpty()) System.out.println("Sizda kontaktlar mavjud emas");
            else {
                for (int i = 0; i < contactByID.size(); i++) {
                    System.out.println(i + 1 + " - " + contactByID.get(i));
                }
                int index = enterInt("Kontaktni tanlang...") - 1;
                conversation(contact.getContactUserID());
                String firstID = contactByID.get(index).getContactUserID();
                String secondID = user.getId();
                    sendSMS(firstID, secondID);
            }
        }
    private static void writeOnNick() {
        String nick = enterStr("Nick name kiriting: ");
        if (userService.searchWithNick(nick)!=null) {
            String nickid = userService.searchWithNick(nick);
            conversation(nickid);
            chooseSendSMSYesOrNo(nickid);
        }
    }
    private static void chooseOldConversation(List<String> cpdIDs) {
        if(!cpdIDs.isEmpty()) {
            int index = enterInt("Yozishmani tanlang...") - 1;
            String id = cpdIDs.get(index);
            conversation(id);
            chooseSendSMSYesOrNo(id);
        }
        else{
            System.out.println("Yozishmalar mavjud emas");
            System.out.println("========================\n");
        }
    }

    private static void conversation(String id) {
        List<Message> messageList = messageService.ourCorrespondence(id, user.getId());
        if (!messageList.isEmpty()) {
            for (Message message1 : messageList) {
                if (message1.getUserID().equals(user.getId())) {
                    getMessageMe(message1);
                } else {
                    System.out.println(message1);
                }
                if (!Objects.equals(message1.getUserID(), user.getId()))
                    message1.editStatus();
            }
        }
    }

    private static void chooseConversation(List<String> cpdIDs) {
        List<String> contactsID = contactService.contactsID(user.getId());
        List<Contact> contactByID = contactService.getContactByID(user.getId());
        int counter = 1;
        for (String cpdID : cpdIDs) {
            if(contactsID.contains(cpdID)){
                for (Contact contact1 : contactByID) {
                    if (Objects.equals(contact1.getContactUserID(), cpdID)) {
                        System.out.println((counter++) + " - " + contact1.getName());
                    }
                }
            }
            else System.out.println((counter++) + " - " + userService.get(cpdID).getNickname());
        }
        chooseOldConversation(cpdIDs);
    }
    private static void getMessageMe(Message message1) {
        System.out.println("\t\t\t\t" + message1.getBody());
        System.out.println("\t\t\t\t" + message1.getLocaleDate());
        System.out.println("\t\t\t\t" + (Objects.equals(message1.getStatus(), StatusMessage.was_read) ? "✅" : "🕛"));
    }

    private static void correspondence() {
        List<String> cpdIDs = messageService.correspondenceID(user.getId());
        chooseConversation(cpdIDs);
    }

    private static void newMessage() {
        List<String> cpdIDs = messageService.newMessageUserID(user.getId());
        chooseConversation(cpdIDs);
    }

    private static void chooseSendSMSYesOrNo(String id) {
        int choose = enterInt("Yangi xabar yozasizmi:\n1. Ha\t\t2.Yo'q\n");
        if(choose == 1){
            String secondID = user.getId();
            sendSMS(id, secondID);
        }
    }
}