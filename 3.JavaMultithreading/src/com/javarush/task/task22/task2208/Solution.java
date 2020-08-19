package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
Map<String,String> map = new TreeMap<> ();
        map.put ("name","Ivanov");
        map.put ("country","Ukraine");
        map.put ("city","Kiev");
        map.put ("age",null);
        System.out.println (getQuery (map));
    }
    public static String getQuery(Map<String, String> params) {
       int leng = params.size ();
       int i=1;
        StringBuilder s=new StringBuilder ("");
        for (HashMap.Entry<String,String> map:params.entrySet ()
             ) {

            if (map.getValue ()!=null&&i<leng)
            {
                s.append (map.getKey ()+" = '"+map.getValue ()+"' and ");
            }
            if (map.getValue ()!=null&&i==leng)
            {
                s.append (map.getKey ()+" = '"+map.getValue ()+"'");
            }
            i++;

        }
        return s.toString ();

    }
}
