# Message Model

Bu classning maqsadi Messagelarni yaratish va uni saqlashdan iborat

## Xususiyatlar

1. **body**: *Jo'natiladigan xabarning bodysi.*
2. **firstID**: *Xabar jo'natuvchi.*
3. **secondID**: *Xabar qabul qiluvchi.*
4. **status**: *Xabar o'qildimi yoki yo'q.*
5. **date**: *Xabar jo'natilgan vaqt.*

## Metodlar

1. Bo'sh konstruktor.
```java
   public Message() {
    }
   ```
2. Barcha xususiyatlar bilan parametrlarga ega konstruktor.
```java
   public Message(String body, String toUserID, String userID) {
    this.body = body;
    this.firstID = toUserID;
    this.secondID = userID;
    this.status = StatusMessage.was_not_read;
    LocalTime localTime = LocalTime.now();
    this.date  = "%s:%s".formatted(localTime.getHour(), localTime.getMinute());
    }
   ```
3. Xabarni bodysini olish
```java
    public String getBody() {
        return body;
    }
```
4. Jo'natuvchi idsini olish
```java
    public String getFirstID(){
    return firstID;
}
```
5. Qabul qiluvchi idsini olish
```java
   public String getSecondID() {
   return secondID;
   }
```
6. Xabar statusini olish
```java
   public StatusMessage getStatus() {
   return status;
   }
```
7. Xabar statusini o'zgartirish
```java
    public void editStatus(){
    this.status = StatusMessage.was_read;
}
```

## Qo'llanish
Message yaratish, bir id dan ikkinchi id ga xabar qoldirishda foydalaniladi