# User Model

Bu classning maqsadi foydalanuvchilar ma'lumotlarini o'z ichiga olish uchun User modelini ta'rifi.

## Xususiyatlar

1. **fullname**: *Foydalanuvchining to'liq ismi.*
2. **nickname**: *Foydalanuvchi niknomi.*
3. **number**: *Foydalanuvchi telefon raqami.*
4. **password**: *Foydalanuvchi paroli.*

## Metodlar

1. Bo'sh konstruktor.
```java
   public User() {
    }
   ```
2. Barcha xususiyatlar bilan parametrlarga ega konstruktor.
```java
   public User(String fullname, String nickname, String number, String password) {
        this.fullname = fullname;
        this.nickname = nickname;
        this.number = number;
        this.password = password;
    }
   ```
3. Foydalanuvchi niknomini o'zgartirish uchun metod.
```java
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
```
4. Foydalanuvchi to'liq ismini qaytaruvchi metod.
```java
    public String getFullname() {
        return fullname;
    }
```
5. Foydalanuvchi niknomini qaytaruvchi metod.
```java
    public String getNickname() {
        return nickname;
    }
   ```
6. Foydalanuvchi telefon raqamini qaytaruvchi metod.
```java
    public String getNumber() {
        return number;
}
```
7. Foydalanuvchi parolini qaytaruvchi metod.
```java
    public String getPassword() {
        return password;
    }
```
## Qo'llanish

User modelini yaratish va unga ma'lumot qo'shish, o'qish va o'zgartirish uchun moslashtirilgan.

