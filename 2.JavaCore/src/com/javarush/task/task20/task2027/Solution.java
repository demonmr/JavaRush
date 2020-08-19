package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/*
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };

        detectAllWords(crossword, "home", "same");




        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<> ();
        List<Word> word=new ArrayList<> ();
        for (int i = 0; i <words.length ; i++) {
            for (int j = 0; j <crossword.length ; j++) {
                for (int k = 0; k <crossword[j].length ; k++) {
                    if (words[i].toCharArray ()[0]==crossword[j][k])
                    {
                        word=search (words[i],j,k,crossword);
                        if (word!=null)
                        {
                            word.toString ();
                            for (Word wordss:word
                                 ) {
                                list.add (wordss);

                            }
                        }

                    }
                }
            }

        }

        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }



    }
    public static List<Word> search(String text,int x, int y,int[][]mass) {
        List<Word> list = new ArrayList<> ();
        int endx = 0;
        int coefx = 0;
        int coefy = 0;
        int endy = 0;
        int resLength = 0;
        for (int i = y; i < mass[x].length; i++) {

            if (mass[x][i] == text.toCharArray ()[resLength]) {
                resLength++;
                if (resLength == text.length ()) {
                    endx = x;
                    endy = i;
                    Word word = new Word (text);
                    word.startX = y;
                    word.startY = x;
                    word.endX = endy;
                    word.endY = endx;
                    list.add (word);

                    break;
                }
            } else
                break;
        }
        resLength = 0;
        for (int i = y; i >= 0; i--) {

            if (mass[x][i] == text.toCharArray ()[resLength]) {
                resLength++;
                if (resLength == text.length ()) {
                    endx = x;
                    endy = i;
                    Word word = new Word (text);
                    word.startX = y;
                    word.startY = x;
                    word.endX = endy;

                    word.endY = endx;
                    list.add (word);
                  break;
                }
            } else {
                break;
            }
        }
        resLength = 0;
        for (int i = x; i < mass.length; i++) {

            if (mass[i][y] == text.toCharArray ()[resLength]) {
                resLength++;
                if (resLength == text.length ()) {
                    endx = i;
                    endy = y;
                    Word word = new Word (text);
                    word.startX = y;
                    word.startY = x;
                    word.endX = endy;

                    word.endY = endx;
                    list.add (word);
                    break;

                }
            } else {
                break;
            }
        }
        resLength = 0;
        for (int i = x; i >= 0; i--) {

            if (mass[i][y] == text.toCharArray ()[resLength]) {
                resLength++;
                if (resLength == text.length ()) {
                    endx = i;
                    endy = y;
                    Word word = new Word (text);
                    word.startX = x;
                    word.startY = y;
                    word.endX = endx;

                    word.endY = endy;
                    list.add (word);
                   break;
                }
            } else {
                break;
            }
        }


        coefx = x;
        coefy = y;
        resLength=0;
        while (coefx < mass.length && coefy < mass[x].length && resLength < text.length ()) {
            if (mass[coefx][coefy]==text.toCharArray ()[resLength])
            {
                resLength++;

            if (resLength == text.length ()) {
                endx = coefx;
                endy = coefy;
                Word word = new Word (text);
                word.startX = y;
                word.startY = x;
                word.endX = endy;

                word.endY = endx;
                list.add (word);

                break;
            }
            }
            else {break;}
            coefx++;
            coefy++;
        }
        coefx = x;
        coefy = y;
        resLength=0;
        while (coefx >= 0 && coefy>=0 && resLength < text.length ()) {
            if (mass[coefx][coefy]==text.toCharArray ()[resLength])
            {
                resLength++;

                if (resLength == text.length ()) {
                    endx = coefx;
                    endy = coefy;
                    Word word = new Word (text);
                    word.startX = y;
                    word.startY = x;
                    word.endX = endy;

                    word.endY = endx;
                    list.add (word);

                    break;
                }
            }
            else {break;}
            coefx--;
            coefy--;
        }
        coefx = x;
        coefy = y;
        resLength=0;
        while (coefx >= 0 && coefy<mass[x].length && resLength < text.length ()) {
            if (mass[coefx][coefy]==text.toCharArray ()[resLength])
            {
                resLength++;

                if (resLength == text.length ()) {
                    endx = coefx;
                    endy = coefy;
                    Word word = new Word (text);
                    word.startX = y;
                    word.startY = x;
                    word.endX = endy;

                    word.endY = endx;
                    list.add (word);
                    break;
                }
            }
            else {break;}
            coefx--;
            coefy++;
        }
        coefx = x;
        coefy = y;
        resLength=0;
        while (coefx <mass.length && coefy>=0 && resLength < text.length ()) {
            if (mass[coefx][coefy]==text.toCharArray ()[resLength])
            {
                resLength++;

                if (resLength == text.length ()) {
                    endx = coefy;
                    endy = coefx;
                    Word word = new Word (text);
                    word.startX = y;
                    word.startY = x;
                    word.endX = endx;

                    word.endY = endy;
                    list.add (word);
                    break;
                }
            }
            else {
                break;
            }
            coefx++;
            coefy--;
        }

return list;
    }

}
