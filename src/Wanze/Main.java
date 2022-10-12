package Wanze;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        String text = "Auf der Mauer auf der Lauer sitzt'ne a kleine Wanze";
        while (text.length() > 0){
            System.out.println(text);
            text = text.substring(0, text.length() -1);
            text = text.strip();
        }

     }


}