# UserService Model

Bu class Userlar tomonidan yozilgan messagelarni saqlash, jo'natish vazigalarini bajaruvchi class

## Xususiyatlar

**messages**: *Userlarning cabarlarini ro'yxatini o'z ichiga oladi.*

## Metodlar

1. Konstruktor.
```java
public MessageServiceimp() {
        this.messages = new ArrayList<>();
    }
   ```
2. Xabar jo'natish metodi
```java
public void sendMessage(Message message) {
        messages.add(message);
    }
```
3. Xabar jo'natuvchi va qabul qiluvchining o'zaro suxbatini ro'yxatini qaytaruvchi class
```java
public List<Message> ourCorrespondence(String contactID, String userID) {
        List<Message> messageList = new ArrayList<>();
        for (Message message : messages) {
            if (Objects.equals(message.getFirstID(),contactID)&&
                    Objects.equals(message.getSecondID(),userID)||
            Objects.equals(message.getFirstID(),userID)&&
            Objects.equals(message.getSecondID(),contactID)) {
                messageList.add(message);
            }
        }
        return messageList;
    }
```
4. User bilan o'zaro mulaqotda bo'lgan boshqa userlarning ID raqamlarining Listlarini qaytaruvchi metod
```java
public List<String> correspondenceID(String userID) {
        var cpdID = new ArrayList<String>();
        for (Message message : messages) {
            if(Objects.equals(message.getSecondID(), userID)){
                if(cpdID.contains(message.getFirstID())){continue;}
                cpdID.add(message.getFirstID());
            }
            if(Objects.equals(message.getFirstID(), userID)) {
                if(cpdID.contains(message.getSecondID())){continue;}
                cpdID.add(message.getSecondID());
            }
        }
        return cpdID;
    }
```
5. User tomonidan o'qilmagan xabarlarni List ko'rinishida qaytaruvchi metod
```java
public List<String> newMessageUserID(String userID) {
        var newMUID = new ArrayList<String>();
        List<String> cpdIDs = correspondenceID(userID);
        for (String cpdID : cpdIDs) {
            List<Message> messageList = ourCorrespondence(cpdID, userID);
            if(messageList.getLast().getStatus().equals(StatusMessage.was_not_read)){
                newMUID.add(cpdID);
            }
        }
        return newMUID;
    }
```
## Qo'llanish
Userlar tomonidan Message jo'natuvchi, eski messagelarni saqlovchi va id lar bo'yicha xabarlarni qaytaruvchi class