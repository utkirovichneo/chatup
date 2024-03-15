package uz.pdp.front.utils;

public interface UtilMain {
    static void signMenu(){
        System.out.println("1. Kirish");
        System.out.println("2. Ro'yxatdan o'tish");
        System.out.println("0. Dasturni tugatish");
        System.out.println("\n========================\n");
    }
    static void menu(){
        System.out.println("========================\n");
        System.out.println("1. Mening chatlarim");
        System.out.println("2. Mening kontaktlarim");
        System.out.println("3. Kontakt qo'shish");
        System.out.println("0. Chiqish");
        System.out.println("\n========================\n");
    }
    static void telegramMenu() {
        System.out.println("========================\n");
        System.out.println("1. Yangi xabarlar");
        System.out.println("2. Yozishmalar");
        System.out.println("3. Kontakt bo'yicha yozish");
        System.out.println("4. NickName bo'yicha yozish");
        System.out.println("0. Menu");
        System.out.println("========================\n");
    }
}
