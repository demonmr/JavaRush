package com.javarush.task.task22.task2211;

import java.io.*;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (new FileInputStream (args[0]), "Windows-1251"));
        PrintWriter fileWriter = new PrintWriter  (args[1], "Windows-1251");
        while (bufferedReader.ready ())
        {
            fileWriter.print (bufferedReader.readLine ()+"\n");
            fileWriter.flush ();
        }
        bufferedReader.close ();
        fileWriter.close ();
    }
}
