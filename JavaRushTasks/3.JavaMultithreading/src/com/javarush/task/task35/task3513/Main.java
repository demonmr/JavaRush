package com.javarush.task.task35.task3513;



public class Main {
    //testing data
   static int score=0;
    static int maxTile=0;
    public static void main (String[] args) {
      Model model = new Model();
       Tile[] mass = {new Tile (0),new Tile (0),new Tile (0),new Tile (0)};
        Tile[] mass1 = {new Tile (8),new Tile (4),new Tile (4),new Tile (0)};
        Tile[] mass2 = {new Tile (4),new Tile (4),new Tile (4),new Tile (0)};

        for (int i = 0; i <mass1.length ; i++) {
            System.out.print(mass1[i].value+" ");
        }
        System.out.println();
        for (int i = 0; i <mass2.length ; i++) {
            System.out.print(mass2[i].value+" ");
        }
      /*   rec (mass);
       rec (mass1);
        rec (mass2);*/
    }



   private static void rec(Tile[] tiles){
       compress (tiles);
        Tile start = tiles[0];
        for (int i = 1; i <tiles.length ; i++) {
            if (start.value==tiles[i].value){
                start.value+=tiles[i].value;
                score+= start.value;
                tiles[i].value=0;
                if (start.value>maxTile){
                    maxTile=start.value;
                }

            }
            else {
                start = tiles[i];
            }

        }
       compress (tiles);
        System.out.println ("!!!!!!"+start.value);
        for (int i = 0; i <tiles.length ; i++) {
            System.out.print (tiles[i].value+" ");
        }
        System.out.println ();
        System.out.println ("---"+score);
        System.out.println ("=="+maxTile);

    }
    private static void compress(Tile[] tiles){
        boolean flag=false;
        Tile temps;
        for (int i = 0; i <tiles.length ; i++) {
            for (int j = 0; j <tiles.length ; j++) {
                if (i<j&&tiles[i].value==0){
                    temps=tiles[i];
                    tiles[i]=tiles[j];
                    tiles[j]=temps;
                    flag=true;
                }
            }
        }
      /*  List<Tile> temp = new ArrayList<> ();
        List<Tile> nuls = new ArrayList<> ();
        for (int i = 0; i <tiles.length; i++) {
            if (tiles[i].value==0){
                nuls.add (tiles[i]);
            }
            else {
                temp.add (tiles[i]);
            }
        }
        for (int i = 0; i <temp.size () ; i++) {
            tiles[i]=temp.get (i);
        }
        for (int i = 0; i <nuls.size () ; i++) {
            tiles[temp.size ()+i]=nuls.get (i);
        }*/
    }
}
