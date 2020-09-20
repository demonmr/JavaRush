package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] interfaces =  Collections.class.getDeclaredClasses();
        List<Class> listSuperClass = new ArrayList<>();
        Class superClass;
        for (Class<?> cl:interfaces
             ) {
            superClass =cl.getSuperclass();
            while (superClass!=null)
            {
                listSuperClass.add(superClass);
                superClass=superClass.getSuperclass();
            }
            for (int i = 0; i <listSuperClass.size() ; i++) {
                System.out.println(List.class.isAssignableFrom(listSuperClass.get(i)));
            }
        if (List.class.isAssignableFrom(cl) && Modifier.isStatic(cl.getModifiers()) && Modifier.isPrivate(cl.getModifiers()))
        {

            try {
                Method method = cl.getMethod("get",int.class);
                Constructor constructor = cl.getDeclaredConstructor();
                constructor.setAccessible(true);
                method.invoke(constructor.newInstance(),3);
            } catch (IllegalAccessException| InvocationTargetException| NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                if (e.getCause().toString().contains("IndexOutOfBoundsException"))  return cl;
            }

        }

        }

        return null;


    }
}
