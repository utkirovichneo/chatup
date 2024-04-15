# User Model

Bu classning maqsadi foydalanuvchilar ma'lumotlarini o'z ichiga olish uchun User modelini ta'rifi.

## Xususiyatlar

1. **name**: *Saqlanadigan kontaktning ismi.*
2. **number**: *Kontaktning telefon raqami.*
3. **userID**: *Saqlovchining id raqami.*
4. **contactID**: *Kontaktning userID raqami.*

## Metodlar

1. Bo'sh konstruktor.
```java
public Contact() {
}
```
2. Barcha xususiyatlar bilan parametrlarga ega konstruktor.
```java
public Contact(String name, String number, String contactUserID, String userID) {
    this.name = name;
    this.number = number;
    this.contactUserID = contactUserID;
    this.userID = userID;
}
   ```
3. Kontaktning ismini set qilish uchun metod
```java
public void setName(String name) {
    this.name = name;
    }
```
4.  Kontaktning ismini olish
```java
public String getName() {
        return name;
    }
```
5. Kontaktning raqamini olish
```java
public String getNumber() {
        return number;
    }
```
6. Userning ID raqamini olish
```java
public String getUserID() {
        return userID;
    }
```
7. Kontaktning userID raqamini olish
```java
public String getContactUserID(){
        return contactUserID;
}
```

## Qo'llanish

Kontakt modeli User tomonidan User sifatida ro'yxatdan o'tgan foydalanuvchilarni ro'yxatga olishda ishlatiladi

