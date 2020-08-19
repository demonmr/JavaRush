package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
      try {
            String[] s = string.split (" ");
            String ss="";
            if (s.length<5)
            {
                throw new TooShortStringException ();
            }
            for (int i = 1; i <s.length ; i++) {

                if ((i<5&&i==s.length-1)||(i<5))
                {
                    ss+=s[i]+" ";
                }
            }
            return ss.trim ();}
      catch (RuntimeException e)
      {
          throw new TooShortStringException ();

      }



    }

    public static class TooShortStringException extends RuntimeException {
    }
}
