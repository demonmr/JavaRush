package com.javarush.task.task25.task2502;

import java.io.IOException;
import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            wheels = new LinkedList<>();;
            String[] s=loadWheelNamesFromDB();
            //init wheels here
            if (s.length!=4)
                throw new IllegalArgumentException();
            else
            {

              for(int i=0;i<s.length;i++)
                {
                    wheels.add(Wheel.valueOf(s[i]));


                }
            }

        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
   
    }
}
