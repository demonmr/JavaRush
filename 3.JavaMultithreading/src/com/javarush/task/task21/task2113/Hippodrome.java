package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    public static Hippodrome game;

    public Hippodrome(List list)
    {
        this.horses=list;
    }
    public static void main (String[] args) throws InterruptedException {
        List<Horse> list=new ArrayList<> ();
        Horse horse1 =new Horse ("Лошадка 1",3,0);
        Horse horse2 =new Horse ("Лошадка 2",3,0);
        Horse horse3 =new Horse ("Лошадка 3",3,0);
        list.add (horse1);
        list.add (horse2);
        list.add (horse3);

        Hippodrome.game=new Hippodrome (new ArrayList ());
        game.setHorses (list);
        game.run ();
        game.printWinner ();

    }

    public List<Horse> getHorses () {
        return horses;
    }

    public void setHorses (List<Horse> horses) {
        this.horses = horses;
    }
    public void run() throws InterruptedException {
        for (int i = 1; i <=100 ; i++) {
            move ();
            print ();
            Thread.sleep(200);

        }
    }
    public void print(){
        for (Horse h:horses
        ) {
            h.print ();

        }
        for (int i = 0; i <10 ; i++) {
            System.out.println ();
        }
    }
    public void move(){

        for (Horse h:horses
             ) {
            h.move ();
            h.print ();

        }
    }
    public Horse getWinner(){
        double dist=0;
        for (Horse h:horses
             ) {

            if (dist<h.getDistance ())
            {
                dist=h.getDistance ();
            }
        }
        for (Horse h:horses
        ) {

            if (dist==h.getDistance ())
            {
             return h;
            }
        }

        return null;
    }
    public void printWinner(){
        System.out.println ("Winner is "+getWinner ().getName ()+"!");
    }
}
