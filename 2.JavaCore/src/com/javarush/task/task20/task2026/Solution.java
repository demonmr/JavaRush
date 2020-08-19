package com.javarush.task.task20.task2026;

import java.util.HashMap;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };
        byte[][] a3 = new byte[][]{
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };
        byte[][] a4 = new byte[][]{
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");

    }

    public static int getRectangleCount(byte[][] a) {
        int  result=0;
        int firstrow=0;
        int secondrow=0;
        int row=0;

        for (int i = 0; i <a.length ; i++) {
            for (int j = 0; j <a[i].length ; j++) {
                if (a[i][j]==1)
                {
                    firstrow=0;
                    for (int k = i; k<a.length ; k++) {
                    for (int l = j; l < a[k].length; l++) {
                                if (a[k][l] == 0)
                                {
                                    break;
                                }
                                else {
                                    if (k == i)
                                    {
                                        firstrow++;

                                    }
                                        row++;
                                    a[k][l]=0;

                                }

                        secondrow=row;
                            }

                        if (firstrow!=row)
                        {

                            row=0;

                            break;
                        }else {
                            row = 0;
                        }

                        }
                    if (firstrow==secondrow) {
                        result++;
                        secondrow=0;
                    }
                    }



                }

        }


        return result;
    }
}
