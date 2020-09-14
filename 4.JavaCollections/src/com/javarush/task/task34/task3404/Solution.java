package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recurse(final String expression, int countOperation) {
        String[] split = expression.split ("\\(");
        if (split.length<=2)
        {
            String res=split[0]+split[1];
            if (res.contains (" ")){
            res.replaceAll ("\\s","");
            }
            operations (res);

        }
        else {
            String math = split[split.length - 1].substring (0, split[split.length - 1].indexOf (")"));
            System.out.println (math);
        }
        //implement
    }
    private float operations(String res){
        String[] mass;
        BigDecimal resultat=null;
        if (res.contains ("+")){
        mass = res.split ("[+]");
        BigDecimal bigDecimal = new BigDecimal (mass[0]);
        BigDecimal bigDecimal1 = new BigDecimal (mass[1]);
        resultat = bigDecimal.add (bigDecimal1);

        }
        else if (res.contains ("-")){

        }else  if (res.contains ("^")){

        }
        else if (res.contains ("*")){

        }
        else if (res.contains ("/"))
        {

        }
        else if (res.contains ("sin")){

        }else if (res.contains ("tan")){

        }else if (res.contains ("cos")){

        }
        resultat.setScale (2);
        return resultat.floatValue ();
    }

    public Solution() {
        //don't delete
    }
}
