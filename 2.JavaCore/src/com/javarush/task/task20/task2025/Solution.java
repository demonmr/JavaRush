package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        int temp=0;
        int a=0;
        long nv=N;
        long chislo=0;
        List<Integer> res = new ArrayList<> ();
        List<Integer> list = new ArrayList<> ();
        while (nv>0)
        {
            temp++;
            nv=nv/10;
        }
        for (int i = 0; i <=N ; i++) {
            int b=i;
            int count=0;
            while (b>0)
            {

                list.add ((int) (b%10));
                count++;
                b=b/10;
            }
            if (count==temp)
                Collections.sort (list);
            {for (int j:list
                 ) {
               a+=Math.pow (j,count);
               chislo+=(chislo+j)*10;

            }
            if (a==N) {
                res.add (a);
            }
            }
            chislo=0;
            list.clear ();
            count=0;
            a=0;
        }


        long[] result = null;
        return result;
    }

    public static void main(String[] args) {
    getNumbers (370);
    }
}
