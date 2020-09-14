package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public static void main (String[] args) {
        recurse (33);
    }
    public static void recurse(int n) {
    int s=2;
    while (s<=n){
        if (n%s==0){
            if (s!=n) {
                System.out.print (s + " ");
                recurse (n / s);
            }

        else {
            System.out.println (s);
        }
        return;
        }
        s++;
    }
    }
}
