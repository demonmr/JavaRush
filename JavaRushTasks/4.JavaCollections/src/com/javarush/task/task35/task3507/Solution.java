package com.javarush.task.task35.task3507;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {

        Set<Animal> animals = new HashSet<>();
        try {
            AnimalLoader animalLoader = new AnimalLoader();
            String getpath = URLDecoder.decode(pathToAnimals,"UTF-8");
            File[] files = new File(getpath).listFiles();

            for (File file: files) {
                Class findClass = animalLoader.findClass(file.getAbsolutePath());
                if (Animal.class.isAssignableFrom(findClass)){
                    Constructor[] constructors = findClass.getConstructors();
                    for (Constructor constsr:constructors
                         ) {
                        if (constsr.getParameterCount()==0)
                        {
                            animals.add((Animal)constsr.newInstance());
                        }

                    }
                }
            }
        } catch (UnsupportedEncodingException|ClassNotFoundException|InstantiationException|IllegalAccessException|InvocationTargetException e) {
            e.printStackTrace();
        }

        return animals;
    }

}
class AnimalLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path path = Paths.get(name);
        byte[] buff = new byte[0];
        try {
            buff = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return defineClass(null,buff,0,buff.length);
    }
}
