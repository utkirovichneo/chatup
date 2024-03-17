# ContactService Model

Bu class userlar tomonidan dastur foydalanuvchilarini kontakt sifatida saqlashda yordam beradi

## Xususiyatlar

**contactList**: *userlar tomonidan saqlangan kontaktlarni o'z ichiga oladi.*

## Metodlar

1. Konstruktor.
```java
public ContactServiceimp() {
    this.contactList = new ArrayList<>();
}
   ```
2. User tomonidan yaratilgan kontaktni saqlaydi
```java
public void create(Contact contact) {
        if (contact == null) {
            return;
        }
        contactList.add(contact);
    }
```
3. User tomonidan saqlangan kontaktni ismini o'zgartiradi
```java
public Contact edit(String id, String newContactName) {
        for (Contact contact : contactList) {
            if(contact.getUserID().equals(id)) {
                contact.setName(newContactName);
            return contact;
            }
        }
        return null;
    }
```
4. User tomonidan saqlangan kontaktlarni ro'yxatdan o'chiradi
```java
public void delete(String id) {
        for (Contact contact : contactList) {
            if (contact.getUserID().equals(id)) {
                contactList.remove(contact);
                return;
            }
        }
    }
```
5. ID raqam bo'yicha contactni qaytaradi
```java
public Contact get(String id) {
        for (Contact contact : contactList) {
            if (contact.getUserID().equals(id)) {
                return contact;
            }
        }
        return null;
    }
```
6. Userning ID raqami bo'yicha unga tegishli barcha kontaktlarni List shaklida qaytaradi
```java
public List<Contact> getContactByID(String id) {
        List<Contact> contactListByid = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getUserID().equals(id)) {
                contactListByid.add(contact);
            }
        }
        return contactListByid;
    }
```







## Qo'llanish
Userlar tomonidan Message jo'natuvchi, eski messagelarni saqlovchi va id lar bo'yicha xabarlarni qaytaruvchi class