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
import uz.pdp.logging.TelegramAlarmHandler;

import java.util.List;
import java.util.Objects;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static uz.pdp.front.utils.Util.*;
import static uz.pdp.front.utils.UtilMain.*;

public class UI {
    static {
        String file = UI.class.getClassLoader().getResource("logging.properties").getFile();
        System.setProperty("java.util.logging.config.file", file);
    }
    private static Logger logger =Logger.getLogger(UI.class.getSimpleName());
    private static final UserService userService = new UserServiceimp();
    private static User user = new User();
    private static final MessageService messageService = new MessageServiceimp();
    private static final ContactService contactService = new ContactServiceimp();
    private static Contact contact = new Contact();

    public static void main(String[] args){
                Handler handler = new TelegramAlarmHandler();
                    logger.addHandler(handler);
        loop1 : while (true) {
            signMenu();
            switch (enterInt("Menuni tanlang...")) {
                case 1 -> {
                    user = signUp();
                    try{
                        if (user == null) {
                            throw new RuntimeException("Bunday account mavjud emas😒😒😒");
                        }
                            logger.log(Level.INFO, "Accountga kirildi");
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
                    catch (Exception e){
                        logger.log(Level.INFO, "Bunday account mavjud emas", e);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
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
        String fullname = enterFullname();
        String nickName = enterNickName();
        String phoneNumber = enterPhoneNumber();
        String password = enterPassword();
        createAccount(phoneNumber, fullname, nickName, password);
    }

    private static void createAccount(String phoneNumber, String fullname, String nickName, String password) {
        String isThere = userService.searchUser(phoneNumber);
        try{
            if(isThere != null){
                throw new RuntimeException("Bunday raqam oldin ro'yxatdan o'tgan🛑🛑🛑");
            }
            userService.create(new User(fullname, nickName, phoneNumber, password));
            System.out.println("Account yaratildi✅✅✅");
            System.out.println("========================\n");
        }
        catch (RuntimeException e){
            logger.log(Level.INFO, "Bu account oldin ro'yxatdan o'tgan");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private static String enterPassword() {
        String password = enterStr("Parol kiriting: ");
        while (!passwordIsValid(password)) {
            System.out.println("Parol uzunligi kamida 8 bo'lsin. Kamida 1 ta katta va 1 ta kichik harfdan foydalaning." +
                    "\n!@#&_\\-<> shu belgilardan bittasidan foydalaning. Kamida 1 ta son qatnashsin");
            password = enterStr("Parol kiriting: ");
        }
        return password;
    }

    private static String enterPhoneNumber() {
        String phoneNumber = enterStr("Telefon raqamingizni kiriting (Misol uchun +998123456789): ");
        while (!numberIsValid(phoneNumber)) {
            System.out.println("Mavjud bo'lmagan turdagi raqamni kiritdingiz: Misol uchun +998977152600 ko'rinishida kiriting");
            phoneNumber = enterStr("Telefon raqamingizni kiriting: ");
        }
        return phoneNumber;
    }

    private static String enterNickName() {
        String nickName = enterStr("Nick name kiriting: ");
        while (!nicknameIsValid(nickName)) {
            System.out.println("Nick name harflar va sonlardan tashkil topsin. Belgisifatida \"_ -\" belgilar ishlatilsin");
            nickName = enterStr("Nick name kiriting: ");
        }
        return nickName;
    }

    private static String enterFullname() {
        String fullname = enterStr("Ismingizni kiriting: ");
        while (!fullnameIsValid(fullname)) {
            System.out.println("Ism xato kiritildi! Kamida ism uzunligi 5 ga teng bo'lsin");
            fullname = enterStr("Ismingizni kiriting: ");
        }
        return fullname;
    }

    private static boolean fullnameIsValid(String fullname){
        Pattern pattern = Pattern.compile("^[A-Za-z\\p{Punct}]{5,}$");
        Matcher matcher = pattern.matcher(fullname);
        return matcher.matches();
    }

    private static boolean nicknameIsValid(String nickname){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9_-]{3,15}$");
        Matcher matcher = pattern.matcher(nickname);
        return matcher.matches();
    }

    private static boolean numberIsValid(String phoneNumber){
        Pattern pattern = Pattern.compile("^(\\+998)((55|77|88|9[013479])(\\d{7}))$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    private static boolean passwordIsValid(String password){
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#&_\\-<>])(?=\\S+$).{8,30}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
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
            try {
                if (contactID == null) {
                    throw new RuntimeException("Bunday foydalanuvchi ro'yxatdan o'tmagan🛑🛑🛑");
                }
                contactService.create(new Contact(contactName, contactNumber, contactID, userID));
                System.out.println("Kontakt saqlandi✅✅✅");
            }
            catch (RuntimeException e){
                logger.log(Level.INFO, "Bunday account ro'yxatdan o'tmagan", e);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
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
                if (message1.getSecondID().equals(user.getId())) {
                    getMessageMe(message1);
                } else {
                    System.out.println(message1);
                }
                if (!Objects.equals(message1.getSecondID(), user.getId()))
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