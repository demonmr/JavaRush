package com.javarush.task.task20.task2020;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.logging.Logger;

/* 
Сериализация человека
*/
public class Solution {

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greeting;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greeting = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
        private void readObject(ObjectInputStream in) throws Exception
        {
            firstName=(String) in.readObject ();
            lastName = (String) in.readObject ();
            country = (String) in.readObject ();
            sex = (Sex) in.readObject ();

        }
        private void writeObject(ObjectOutputStream out) throws Exception
        {
            out.writeObject (firstName);
            out.writeObject (lastName);
            out.writeObject (country);
            out.writeObject (sex);

        }
    }

    enum Sex {
        MALE,
        FEMALE
    }

    public static void main(String[] args) {

    }
}
