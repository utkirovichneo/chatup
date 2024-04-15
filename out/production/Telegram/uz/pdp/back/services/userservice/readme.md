# UserService Model

Bu class User modeli ustida amallar bajarish, saqlash uchun ishlatiladi

## Xususiyatlar

**userList**: *Userlarning ro'yxatini o'z ichiga oladi.*

## Metodlar

1. Konstruktor.
```java
public UserServiceimp() {
    this.userList = new ArrayList<>();
}
   ```
2. Userlarni yaratuvchi metod
```java
public void create(User user) {
        if (user == null) {
            return;
        }
            userList.add(user);
    }
```
3. Userning nick nomini o'zgartiruvchi metod
```java
public User edit(String id, String newNickName) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                user.setNickname(newNickName);
                return user;
            }
        }
        return null;
    }
```
4. Userni userlar ro'yxatidan o'chiruvchi metod
```java
public void delete(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                userList.remove(user);
                return;
            }
        }
    }
```
5. Userni userlar ro'yxatidan ID raqami bo'yicha olish metodi
```java
public User get(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
```
6. Telefon raqami bo'yicha userni qidirib ID raqamini qaytaruvchi metod
```java
public String searchUser(String phoneNumber) {
        String id = null;
        for (User user : userList) {
            if (user.getNumber().equals(phoneNumber)) {
                id = user.getId();
            }
        }
        return id;
    }
```
7. Userni nick nomi bo'yicha qidiruvchi metod
```java
public String searchWithNick(String nick) {
        for (User user : userList) {
            if (Objects.equals(user.getNickname(), nick)) {
             return user.getId();
            }
        }
        return null;
    }
```
8. Userlarnining login qilish metodi
```java
public User login(String number, String password) {
        for (User user : userList) {
            if (user.getNumber().equals(number)&&
                    user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
```

## Qo'llanish
User modeli yordamida userlarni yaratish va ularni Listga saqlash, o'zgartirish, qidirish, qaytarish va o'chirishni
bajaruvchi class