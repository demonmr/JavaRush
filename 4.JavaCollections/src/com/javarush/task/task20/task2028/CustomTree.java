package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements  Cloneable, Serializable {
     Entry<String> root;
     private ArrayList<Entry> list = new ArrayList<>();
    private ArrayList<Entry> deleteItem = new ArrayList<> ();
    public String getParent(String s) {
        for (Entry temp:list
             ) {
            if (temp.elementName.equals(s))
            {
                return temp.parent.elementName;
            }
        }
        return null;
    }

    static class Entry<T> implements Serializable
    {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }
        public boolean isAvailableToAddChildren()
        {
            return availableToAddLeftChildren|availableToAddRightChildren;
        }
    }

    public CustomTree() {
        this.root = new Entry<>("0");
        list.add(0, root);
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        int size=0;
        for (Entry t:list
             ) {
            if (t!=null)
            {
                size++;
            }
        }


        return size-1;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean add(String element) {
        Entry elements;
        for (Entry elem:list
             ) {
            if (elem.isAvailableToAddChildren()) {
                if (elem.availableToAddLeftChildren)
                {
                    elem.availableToAddLeftChildren=false;
                    elements  = new Entry(element);
                    elements.parent = elem;
                    elem.leftChild=elements;
                    list.add(elements);
                    return true;
                }
                else if (elem.availableToAddRightChildren){
                    elem.availableToAddRightChildren=false;
                    elements = new Entry(element);
                    elements.parent = elem;
                    elem.rightChild=elements;
                    list.add(elements);
                    return true;
                }
            }
        }

       return false;
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }


            for (Entry t:list
                 ) {
                if (t.elementName.equals(o.toString()))
                {
                    if (!t.isAvailableToAddChildren ()) {
                        deleteItem.add(t);
                        if (!t.availableToAddRightChildren) {
                            deleteItem.add(t.rightChild);
                            t.parent.rightChild = null;
                            t.parent.availableToAddRightChildren = true;
                        }
                        if (!t.availableToAddLeftChildren) {
                            deleteItem.add (t.leftChild);
                            t.parent.rightChild = null;
                            t.parent.availableToAddRightChildren = true;

                        }
                    }
                    else
                    {
                        if (t.parent.rightChild.elementName.equals (t.elementName)) {
                            t.parent.rightChild = null;
                            t.parent.availableToAddRightChildren = true;
                            deleteItem.add (t);
                        }
                             else if (t.parent.leftChild.elementName.equals (t.elementName)) {
                            t.parent.leftChild = null;
                            t.parent.availableToAddLeftChildren = true;
                            deleteItem.add (t);

                        }

                    }

                }

            }





        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
