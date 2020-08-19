package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution savedObject = new Solution(5);
        FileOutputStream fileOutput = new FileOutputStream("D:/cat.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
        outputStream.writeObject(savedObject);
        fileOutput.close();
        outputStream.close();

        FileInputStream fiStream = new FileInputStream("D:/cat.txt");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);
        Solution loadObject = (Solution) objectStream.readObject();
        fiStream.close();
        objectStream.close();
        if (savedObject.string.equals(loadObject.string))
        {
            System.out.println("ok");
        }
        System.out.println(new Solution(4));
    }

     private final transient String pattern = "dd MMMM yyyy, EEEE";
    private  transient Date currentDate;
     private  transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }


    @Override
    public String toString() {
        return this.string;
    }


    public void writeExternal(ObjectOutput out) throws IOException {
       out.writeObject(temperature);
        out.writeObject(currentDate);
        out.writeObject(string);
    }


    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        temperature = in.readInt();
                currentDate = new Date();
                currentDate.setTime(in.readLong());
        string = (String) in.readObject();
    }
}
