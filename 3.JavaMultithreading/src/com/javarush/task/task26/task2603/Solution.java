package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {
    public static void main (String[] args) {

    }

    public static class CustomizedComparator<T> implements Comparator<T> {
        private Comparator<T>[] comparators;
        public CustomizedComparator(Comparator...comparators) {
            this.comparators =  comparators;
        }
        @Override
        public int compare (T o1, T o2) {
            for (Comparator comp:comparators
                 ) {
            int result=comp.compare (o1,o2);
            if (result==0)
                continue;
            else
                return result;

            }

            return 0;
        }
    }
}
