package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            if (args[0] != null) {
                Path path = Paths.get(args[0]);
                List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
               // System.out.println(list.size()+list.get(0));
               //List<Character> s= Arrays.stream(list.stream().toArray()).collect(Collectors.toSet());
                TreeSet<Character> set = new TreeSet<>();
                for (String st:list
                     ) {
                    char[] ar=st.toCharArray();
                    for (int i = 0; i <ar.length ; i++) {
                     //  if ((ar[i]>64&&ar[i]<91)||(ar[i]>96&&ar[i]<123))
                        set.add(Character.toLowerCase(ar[i]));
                     //   System.out.println((int)ar[i]+" "+ar[i]);
                    }
                }
              //  set.stream().limit(5).forEach(System.out::print);
               // System.out.println(set.size());
                    set.stream().filter(Character::isLetter).limit(5).forEach(System.out::print);
            }

        }
    }
}
