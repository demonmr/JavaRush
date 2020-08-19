package com.javarush.task.task22.task2209;

import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) {
        //...
        //StringBuilder result = getLine("Нью-Йорк","Мельбурн","Вена","Амстердам","Киев");
        //StringBuilder result1 = getLine("Киев", "Нью-Йорк", "Амстердам", "Вена", "Мельбурн");
        StringBuilder result2 = getLine("трам", "Нет", "труД", "муд", "доМ", "мандарин", "март");

        //System.out.println(result.toString());
       // System.out.println(result1.toString());
        System.out.println(result2.toString());
    }

    public static StringBuilder getLine(String...words) {
        if(words == null || words.length == 0) return new StringBuilder("");
        if(!words[0].isEmpty() && words.length == 1) return  new StringBuilder(words[0]);
        ArrayList<String> list = new ArrayList<String>();
        Collections.addAll(list, words);
        Collections.sort(list);
        List<String> temp=new ArrayList<> ();
        for (int j=0 ; j < list.size() - 2;j++) {
            for (int i = j+1; i < list.size(); i++) {
                if (list.get(j).substring (list.get(j).length()-1).equalsIgnoreCase (list.get(i).substring (0,1))    ) {
                    String s = list.get(j+1);
                    list.set(j+1, list.get(i));
                    list.set(i, s);
                    break;
                }
            }
        }
        for (int i = 0; i <list.size () ; i++) {

               if (i>0&&i<list.size ()-1)
               {
                   if (!list.get(i).substring (0,1).equalsIgnoreCase (list.get (i-1).substring (list.get (i-1).length ()-1))||!list.get(i).substring (list.get (i).length ()-1).equalsIgnoreCase (list.get (i+1).substring (0,1)))
                {
                   temp.add (list.get (i));
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for(String s : list) {
            result.append(s).append(" ");
        }
        //result = result.deleteCharAt(result.length() - 1);
        return result;
    }

    /*public static StringBuilder getLine(String... words) {
        StringBuilder res = new StringBuilder ();
        if (words.length == 0) {
            return new StringBuilder ();
        } else {
            List<String> list = new ArrayList<> (Arrays.asList (words));
            Collections.sort (list);
            List<String> result = new LinkedList<> ();
            List<String> temp = new ArrayList<> ();
            boolean before = false;
            boolean after = false;
            String next = "";
            String prev = "";
            result.add (list.get (0));
            list.remove (0);
            int count = 1;
            while (count != 0) {
                for (int i = 0; i < list.size (); i++) {

                    for (int j = 0; j < result.size (); j++) {
                        if (j > 0) {
                            prev = result.get (j - 1);
                        }
                        if (j < result.size () - 1) {
                            next = result.get (j + 1);
                        }
                        if (!result.get (j).equals (list.get (i)) && !before && !after) {
                            if (result.get (j).substring (0, 1).equalsIgnoreCase (list.get (i).substring (list.get (i).length () - 1)) && result.get (j).substring (result.get (j).length () - 1).equalsIgnoreCase (list.get (i).substring (0, 1))) {
                                result.add (j, list.get (i));
                            } else if (result.get (j).substring (0, 1).equalsIgnoreCase (list.get (i).substring (list.get (i).length () - 1)) && prev.equals ("")) {
                                result.add (j, list.get (i));
                                before = true;
                            } else if (result.get (j).substring (result.get (j).length () - 1).equalsIgnoreCase (list.get (i).substring (0, 1)) && next.equals ("")) {
                                result.add (list.get (i));
                                after = true;
                            } else if ((result.get (j).substring (result.get (j).length () - 1).equalsIgnoreCase (list.get (i).substring (0, 1)) && !next.equals ("")) || (result.get (j).substring (0, 1).equalsIgnoreCase (list.get (i).substring (list.get (i).length () - 1)) && !prev.equals ("")) || (prev.equals ("") && next.equals (""))) {
                                temp.add (list.get (i));

                            }
                        }
                        prev = "";
                        next = "";


                    }

                    before = false;
                    after = false;
                }
                count = temp.size ();
                for (int i = 0; i < temp.size (); i++) {


                    {
                        for (int j = 0; j < result.size (); j++) {
                            if (j > 0) {
                                prev = result.get (j - 1);
                            }
                            if (j < result.size () - 1) {
                                next = result.get (j + 1);
                            }
                            if (!result.get (j).equals (temp.get (i)) && !before && !after) {
                                if (result.get (j).substring (0, 1).equalsIgnoreCase (temp.get (i).substring (temp.get (i).length () - 1)) && prev.equals ("")) {
                                    result.add (j, temp.get (i));
                                    before = true;
                                } else if (result.get (j).substring (result.get (j).length () - 1).equalsIgnoreCase (temp.get (i).substring (0, 1)) && next.equals ("")) {
                                    result.add (temp.get (i));
                                    after = true;
                                }
                            }
                        }
                    }


                }
            }
            String sa = "";
            for (int i = 0; i < temp.size (); i++) {
                sa += temp.get (i) + " ";
            }
            System.out.println (sa);
            for (int i = 0; i < result.size (); i++) {
                if (i != result.size () - 1) {
                    res.append (result.get (i) + " ");
                } else res.append (result.get (i));
            }
        }
        return res;
    }
    */
}
