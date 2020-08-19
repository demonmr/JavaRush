package com.javarush.task.task26.task2601;

import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
Integer[] mass={3,13, 8, 15, 5, 17,};
        int med=searchMediana (mass);
        System.out.println (med);
        Integer[]res=sort (mass);
        for (int i = 0; i < res.length; i++) {
            System.out.println (res[i]);
        }
    }

    public static Integer[] sort(Integer[] array) {
        List<Integer> list=new ArrayList <>();
        for (int i = 0; i <array.length ; i++) {
             list.add (array[i]);
        }
        int mediana=searchMediana (array);
        Comparator<Integer> compareByMediana = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int delt=Math.abs(mediana-o1)-Math.abs(mediana-o2);
                if  (delt!=0)
                    return delt;
                else
                    return compare (o1,o2);
            }
        };
        Collections.sort(list, compareByMediana);

        Integer[] result = new Integer[list.size ()];
        int a=0;
        for (Integer ia:list
             ) {
            result[a]=ia;
            a++;

        }
        //implement logic here
        return result;
    }

    public static Integer searchMediana(Integer[] array)
    {
               Arrays.sort (array);
        if (array.length%2!=0)
            return array[array.length/2];
        else
            return (array[array.length/2-1]+array[array.length/2])/2;
    }

}
