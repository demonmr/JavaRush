package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (this== o) return true;
        if (o==null) return false;
        if (!(o instanceof Solution)) return false;
       Solution solution = (Solution) o;
        if (solution.last!=null&&solution.first!=null &&first!=null&&last!=null)
        {
            return first.equals (solution.first) &&
                    last.equals (solution.last);}
        if (solution.last==null&&solution.first==null &&first==null&&last==null)
            return true;
        if (solution.first!=null)
        {
            return solution.first.equals (first);
        }
        if (solution.last!=null)
        {
            return solution.last.equals (last);
        }
        else return false;
    }
    public int hashCode() {
        return (first==null?0:(int)(first.charAt(first.length()-1)))*31+(int)(last==null?0:(last.charAt(last.length()-1)));
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
