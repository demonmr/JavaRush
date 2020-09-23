package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<K,V>();   //TODO add your code here
    private Class<?> clazz;

    public V getByKey(K key, Class<V> clazz) throws Exception {

        final Object[] res = new Object[1];
        if ( cache.get(key) ==null)
        {
            Constructor[]constructors = clazz.getDeclaredConstructors();
            if (constructors.length>0) {
                Arrays.stream(constructors).forEach(constructor -> {
                    if (constructor.getParameterCount()==1)
                    {
                       // System.out.println(constructor.getParameters()[0].getType());
                     //   System.out.println(key.getClass());
                        if (constructor.getParameters()[0].getType().equals(key.getClass()))
                        {
                            try {
                              res[0] = (V) constructor.newInstance(key);
                             cache.put(key, (V)res[0]);


                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

return (V)res[0];
        }
        else {
            return cache.get(key);
        }


        //TODO add your code here
    }

    public boolean put(V obj) {
        final Object[] key = new Object[1];

        Class clazs = obj.getClass();
        boolean flag =false;
        Method[] methods = clazs.getDeclaredMethods();
        Arrays.stream(methods).forEach(element->{
            if (element.getName().equals("getKey")&&element.getParameterCount()==0)
            {
                element.setAccessible(true);
                try {
                    key[0] = element.invoke(obj);
                    cache.put((K)key[0],obj);
                    return ;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        });
        if (key[0]!=null)
            return true;
            else
        //TODO add your code here
        return false;
    }

    public int size() {
        return cache.size();
    }
}
