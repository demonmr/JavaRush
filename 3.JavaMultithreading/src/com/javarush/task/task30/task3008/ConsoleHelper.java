package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (System.in));

    public static void writeMessage(String message){
        System.out.println (message);
    }
    public static String readString(){
        try {
            String str = bufferedReader.readLine ();
            bufferedReader.close ();
            return str;
        }catch (IOException e)
        {
            System.out.println ("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            return readString ();
        }

    }
    public static int readInt() {
        try {
            int number = Integer.parseInt (readString ());
            return number;
        }catch (NumberFormatException e)
        {
            System.out.println ("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            return readInt ();
        }

    }
}
