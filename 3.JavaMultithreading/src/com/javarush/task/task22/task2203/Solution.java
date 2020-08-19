package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string==null||string.isEmpty ())
        {
            throw new TooShortStringException ();
        }
        try{ String[] s = string.split ("\t");
            String ss;
            if (s.length<=2)
            {
                throw new TooShortStringException ();
            }else {

                int a=string.indexOf ('\t');
                ss=string.substring (string.indexOf ("\t")+1,string.indexOf ('\t',a+1));
            }

            return ss;}
        catch (Exception e)
        {
            throw new TooShortStringException ();
        }


    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
