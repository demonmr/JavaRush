package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (System.in));
            //BufferedReader bufferedReader1 = new BufferedReader (new FileReader (bufferedReader.readLine ()));
            Scanner fileReader = new Scanner (new File (bufferedReader.readLine ()));
            String text="";
            while (fileReader.hasNext ())
            {
                text+=fileReader.nextLine ()+"\n";
            }
            String sq=text.replace ('\n',' ');
            String[] s=sq.split (" ");
            StringBuilder results = new StringBuilder ();

            for (int i = 0; i <s.length ; i++) {
                for (int j = 0; j <s.length ; j++) {

                    StringBuilder as=new StringBuilder (s[j]);
                    String sr=as.reverse ().toString ();
                if (s[i].equals (sr)&&i!=j&&results.indexOf (s[i])==-1&&s[i].length ()>1)
                {
                    results.append (s[i]+" "+s[j]+"\n");
                    Pair pair = new Pair ();
                    pair.first=s[i];
                    pair.second=s[j];
                    result.add (pair);
                }
                if (s[i].length ()==1&&i!=j&&s[i].equals (sr)&&results.indexOf (s[i]+" "+s[i])==-1)
                {  results.append (s[i]+" "+s[j]+"\n");
                    Pair pair = new Pair ();
                    pair.first=s[i];
                    pair.second=s[j];
                    result.add (pair);}
                }
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public static class Pair {
        String first;
        String second;
        public Pair()
        {

        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                        first == null ? second :
                            second == null ? first :
                                first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
