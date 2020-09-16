package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable,Cloneable, Set<E> {
    private static final Object PRESENT = new Object ();
    private transient HashMap<E,Object> map;

    public AmigoSet () {
        this.map = new HashMap<> ();
    }

    public AmigoSet (Collection<? extends E> collection) {
        int capacity = Math.max (16,(int)Math.ceil(collection.size()/.75f));
        map = new HashMap<> (capacity);
        for (E e: collection
             ) {
            map.put (e,PRESENT);
        }
    }

    @Override
    public Iterator<E> iterator () {
        Iterator<E> iterator= map.keySet ().iterator ();
        return iterator;
    }

    @Override
    public boolean isEmpty () {
        return map.isEmpty ();
    }

    @Override
    public boolean contains (Object o) {
        return map.containsKey (o);
    }

    @Override
    public boolean remove (Object o) {
        return null==map.remove (o);
    }

    @Override
    public void clear () {
        map.clear ();
    }

    @Override
    public boolean addAll (Collection c) {
        return false;
    }

    @Override
    public Object[] toArray (Object[] a) {
        return new Object[0];
    }

    @Override
    public int size () {
        return map.size ();
    }

    @Override
    public boolean add (E e) {
        return null==map.put (e,PRESENT);
    }

    @Override
    public Object clone (){
        AmigoSet<E> list=null;
        try {
            list= new AmigoSet<> ();
            list.map=(HashMap<E, Object>) map.clone ();
          
        }catch (Exception e)
        {
            throw new InternalError ();
        }


    return list;
    }
}
