package uz.pdp.front.utils;

import java.util.Scanner;

public interface Util {
    static String enterStr(String hint){
        System.out.print(hint);
        return new Scanner(System.in).nextLine();
    }
    static int enterInt(String hint){
        System.out.print(hint);
        return new Scanner(System.in).nextInt();
    }
}
