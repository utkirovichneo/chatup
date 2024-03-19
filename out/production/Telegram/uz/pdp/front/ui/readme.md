# UI class
*Bu class bizga dasturimizda UI vazifasini bajaradi va ma`lumotlarni consolda tushunarli ko'rinishda o'qish va buyruqlar berishda yordam beradi*

## Metodlar
1. Main qismida asosiy qismlar, menular va boshqa metodlarga murojaatlar mavjud
2. Bu metod ro'yxatdan o'tish foydalaniladi
```java
private static void signIn(){
        String fullname = enterFullname();
        String nickName = enterNickName();
        String phoneNumber = enterPhoneNumber();
        String password = enterPassword();
        createAccount(phoneNumber, fullname, nickName, password);
    }
```
3. `enterFullname()` - bu metod ro'yxatdan o'tishda foydalanuvchining to'liq ismini olish uchun foydalaniladi
```java
private static String enterFullname() {
        String fullname = enterStr("Ismingizni kiriting: ");
        while (!fullnameIsValid(fullname)) {
            System.out.println("Ism xato kiritildi! Kamida ism uzunligi 5 ga teng bo'lsin");
            fullname = enterStr("Ismingizni kiriting: ");
        }
        return fullname;
    }
```
4. `enterNickName()` - bu metod ro'yxatdan o'tishda foydalanuvchining nickName ini olish uchun foydalaniladi
```java
private static String enterNickName() {
        String nickName = enterStr("Nick name kiriting: ");
        while (!nicknameIsValid(nickName)) {
            System.out.println("Nick name harflar va sonlardan tashkil topsin. Belgisifatida \"_ -\" belgilar ishlatilsin");
            nickName = enterStr("Nick name kiriting: ");
        }
        return nickName;
    }
```
5. `enterPhoneNumber()` - bu metod ro'yxatdan o'tishda foydalanuvchining telefon raqamini olish uchun foydalaniladi
```java
private static String enterPhoneNumber() {
        String phoneNumber = enterStr("Telefon raqamingizni kiriting (Misol uchun +998123456789): ");
        while (!numberIsValid(phoneNumber)) {
            System.out.println("Mavjud bo'lmagan turdagi raqamni kiritdingiz: Misol uchun +998977152600 ko'rinishida kiriting");
            phoneNumber = enterStr("Telefon raqamingizni kiriting: ");
        }
        return phoneNumber;
    }
```
6. `enterPassword()` - bu metod ro'yxatdan o'tishda foydalanuvchining passwordini olish uchun foydalaniladi
```java
private static String enterPassword() {
        String password = enterStr("Parol kiriting: ");
        while (!passwordIsValid(password)) {
            System.out.println("Parol uzunligi kamida 8 bo'lsin. Kamida 1 ta katta va 1 ta kichik harfdan foydalaning." +
                    "\n!@#&_\\-<> shu belgilardan bittasidan foydalaning. Kamida 1 ta son qatnashsin");
            password = enterStr("Parol kiriting: ");
        }
        return password;
    }
```
7. `fullnameIsValid(String fullname)` - bu metod ism ro'yxatdan o'tishga yaroqliligini tekshiradi
```java 
private static boolean fullnameIsValid(String fullname){
        Pattern pattern = Pattern.compile("^[A-Za-z\\p{Punct}]{5,}$");
        Matcher matcher = pattern.matcher(fullname);
        return matcher.matches();
    }
```
8. `nicknameIsValid(String nickname)` - bu metod nick name ro'yxatdan o'tishga yaroqliligini tekshiradi
```java
private static boolean nicknameIsValid(String nickname){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9_-]{3,15}$");
        Matcher matcher = pattern.matcher(nickname);
        return matcher.matches();
    }
```
9. `numberIsValid(String phoneNumber)` - bu metod telefon raqam ro'yxatdan o'tishga yaroqliligini tekshiradi
```java
private static boolean numberIsValid(String phoneNumber){
        Pattern pattern = Pattern.compile("^(\\+998)((55|77|88|9[013479])(\\d{7}))$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
```
10. `passwordIsValid(String password)` - bu metod passwordning ishonchli ekanligini tekshiradi 
```java
private static boolean passwordIsValid(String password){
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#&_\\-<>])(?=\\S+$).{8,30}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
```
11. `signUp()` - bu metod dasturga kirish uchun ishlatiladi. Telefon raqami va passworddan foydalaniladi
```java
private static User signUp(){
        String number = enterStr("Raqamingizni kiriting: ");
        String password = enterStr("Parolni kiriting: ");
        return userService.login(number, password);
    }
```
12. `createContact()` - bu metod user tomonidan o'ziga kontakt yaratish va uni kontaktlari ro'yxatiga qo'shishda foydalaniladi
```java
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
```
13. `getMyContact()` - bu metod userga tegishli barcha kontaktlarni list shaklida olish va uni ekranga chop etish uchun ishlatiladi
```java
private static void getMyContact(){
        List<Contact> contactByID = contactService.getContactByID(user.getId());
        for (Contact contact1 : contactByID) {
            System.out.println(contact1);
        }
    }
```
14. `sendSMS(String firstID, String secondID)` - bu bir userdan ikkinchi userga message jo'natuvchi metod
```java
private static void sendSMS(String firstID, String secondID){
        messageService.sendMessage(new Message(
                enterStr("\t\t\t\tXabar yozing...\n\t\t\t\t"),
                firstID,
                secondID));
    }
```
15. `writeOnContact()` - bu metod userga tegishli kontaktlarga xabar jo'natish uchun qo'llaniladigan metod
```java
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
```
16. `writeOnNick()` - bir user tomonidan ikkinchi userga nickName orqali xabar jo'natishda foydalaniladigan metod
```java
private static void writeOnNick() {
        String nick = enterStr("Nick name kiriting: ");
        if (userService.searchWithNick(nick)!=null) {
            String nickid = userService.searchWithNick(nick);
            conversation(nickid);
            chooseSendSMSYesOrNo(nickid);
        }
    }
```
17. `chooseOldConversation(List<String> cpdIDs)` - bu ikkita user orasida yozishmalar mavjud bo'lsa ekranga chiqaruvchi bo'lmasa mavjud emas yozuvini chiqaruvchi metod
```java
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
```
18. `conversation(String id)` - bu userlarning o'zaro eski yozishmalarini ekranga chop etuvchi metod
```java
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
```
19. `newMessage()` - userga kelgan va o'qilmagan statusda xabarlar ro'yxati
```java
private static void newMessage() {
        List<String> cpdIDs = messageService.newMessageUserID(user.getId());
        chooseConversation(cpdIDs);
    }
```