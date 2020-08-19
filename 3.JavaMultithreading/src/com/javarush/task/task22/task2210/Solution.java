package com.javarush.task.task22.task2210;

import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {

    }
    public static String [] getTokens(String query, String delimiter) {

        StringTokenizer tokenizer =new StringTokenizer (query, delimiter);
        int count=0;
        String[] strings=new String[tokenizer.countTokens ()];
        while (tokenizer.hasMoreTokens())
        {
            strings[count] = tokenizer.nextToken();
            count++;

        }

        return strings;
    }
}
