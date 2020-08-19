package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        boolean flag=false;
        List<Integer> list = new LinkedList<>();
        try {
            if (args.length<256) {
                char[] mass = args[0].toCharArray();
                for (int i = 0; i < mass.length; i++) {
                    int a = mass[i];
                    if (a<32||a>127)
                    {
                     flag=true;

                    }


                }
                if (!flag){
                    for (int i = 2; i <=36 ; i++) {
                        try {
                            BigInteger bigInteger = new BigInteger(args[0],i);
                            list.add(i);
                        } catch (NumberFormatException e) {
                        }
                    }
                    if (list.size()>0)
                    {
                        System.out.println(list.get(0));
                    }
                    else
                    {
                        System.out.println("incorrect");
                    }
                }
            }
        } catch (Exception e) {
        }

        //напишите тут ваш код
    }
}