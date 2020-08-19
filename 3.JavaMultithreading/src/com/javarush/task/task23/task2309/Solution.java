package com.javarush.task.task23.task2309;

import com.javarush.task.task23.task2309.vo.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/*
Анонимность иногда так приятна!
*/
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.getUsers());
        print(solution.getLocations());
        print(solution.getSubscription());
    }

    public static void print(List list) {
        String format = "Id=%d, name='%s', description=%s";
        for (Object obj : list) {
            NamedItem item = (NamedItem) obj;
            System.out.println(String.format(format, item.getId(), item.getName(), item.getDescription()));
        }
    }

    public static List<Subscription> getSubscription(){
        return new AbstractDbSelectExecutor<Subscription>(){

            @Override
            public int size () {
                return 0;
            }

            @Override
            public boolean isEmpty () {
                return false;
            }

            @Override
            public boolean contains (Object o) {
                return false;
            }

            @Override
            public Iterator<Location> iterator () {
                return null;
            }

            @Override
            public Object[] toArray () {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray (T[] a) {
                return null;
            }

            @Override
            public boolean add (Location location) {
                return false;
            }

            @Override
            public boolean remove (Object o) {
                return false;
            }

            @Override
            public boolean containsAll (Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll (Collection<? extends Location> c) {
                return false;
            }

            @Override
            public boolean addAll (int index, Collection<? extends Location> c) {
                return false;
            }

            @Override
            public boolean removeAll (Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll (Collection<?> c) {
                return false;
            }

            @Override
            public void clear () {

            }

            @Override
            public Location get (int index) {
                return null;
            }

            @Override
            public Location set (int index, Location element) {
                return null;
            }

            @Override
            public void add (int index, Location element) {

            }

            @Override
            public Location remove (int index) {
                return null;
            }

            @Override
            public int indexOf (Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf (Object o) {
                return 0;
            }

            @Override
            public ListIterator<Location> listIterator () {
                return null;
            }

            @Override
            public ListIterator<Location> listIterator (int index) {
                return null;
            }

            @Override
            public List<Location> subList (int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public String getQuery() {
                return String.format("SELECT * FROM %S",Subscription.class.getSimpleName());
            }
        }.execute();
    }
    public List<User> getUsers(){
        return new AbstractDbSelectExecutor<User>() {
            @Override
            public int size () {
                return 0;
            }

            @Override
            public boolean isEmpty () {
                return false;
            }

            @Override
            public boolean contains (Object o) {
                return false;
            }

            @Override
            public Iterator<Location> iterator () {
                return null;
            }

            @Override
            public Object[] toArray () {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray (T[] a) {
                return null;
            }

            @Override
            public boolean add (Location location) {
                return false;
            }

            @Override
            public boolean remove (Object o) {
                return false;
            }

            @Override
            public boolean containsAll (Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll (Collection<? extends Location> c) {
                return false;
            }

            @Override
            public boolean addAll (int index, Collection<? extends Location> c) {
                return false;
            }

            @Override
            public boolean removeAll (Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll (Collection<?> c) {
                return false;
            }

            @Override
            public void clear () {

            }

            @Override
            public Location get (int index) {
                return null;
            }

            @Override
            public Location set (int index, Location element) {
                return null;
            }

            @Override
            public void add (int index, Location element) {

            }

            @Override
            public Location remove (int index) {
                return null;
            }

            @Override
            public int indexOf (Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf (Object o) {
                return 0;
            }

            @Override
            public ListIterator<Location> listIterator () {
                return null;
            }

            @Override
            public ListIterator<Location> listIterator (int index) {
                return null;
            }

            @Override
            public List<Location> subList (int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public String getQuery() {
                return String.format ("SELECT * FROM %S",User.class.getSimpleName ()) ;
            }
        }.execute();

    }
    public List<Location> getLocations(){
        return new AbstractDbSelectExecutor<Location>() {
            @Override
            public int size () {
                return 0;
            }

            @Override
            public boolean isEmpty () {
                return false;
            }

            @Override
            public boolean contains (Object o) {
                return false;
            }

            @Override
            public Iterator<Location> iterator () {
                return null;
            }

            @Override
            public Object[] toArray () {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray (T[] a) {
                return null;
            }

            @Override
            public boolean add (Location location) {
                return false;
            }

            @Override
            public boolean remove (Object o) {
                return false;
            }

            @Override
            public boolean containsAll (Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll (Collection<? extends Location> c) {
                return false;
            }

            @Override
            public boolean addAll (int index, Collection<? extends Location> c) {
                return false;
            }

            @Override
            public boolean removeAll (Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll (Collection<?> c) {
                return false;
            }

            @Override
            public void clear () {

            }

            @Override
            public Location get (int index) {
                return null;
            }

            @Override
            public Location set (int index, Location element) {
                return null;
            }

            @Override
            public void add (int index, Location element) {

            }

            @Override
            public Location remove (int index) {
                return null;
            }

            @Override
            public int indexOf (Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf (Object o) {
                return 0;
            }

            @Override
            public ListIterator<Location> listIterator () {
                return null;
            }

            @Override
            public ListIterator<Location> listIterator (int index) {
                return null;
            }

            @Override
            public List<Location> subList (int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public String getQuery() {
                return String.format("SELECT * FROM %S",Location.class.getSimpleName());
            }
        }.execute();
    }
    public List<Server> getServers(){
        return new AbstractDbSelectExecutor<Server>() {
            @Override
            public int size () {
                return 0;
            }

            @Override
            public boolean isEmpty () {
                return false;
            }

            @Override
            public boolean contains (Object o) {
                return false;
            }

            @Override
            public Iterator<Location> iterator () {
                return null;
            }

            @Override
            public Object[] toArray () {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray (T[] a) {
                return null;
            }

            @Override
            public boolean add (Location location) {
                return false;
            }

            @Override
            public boolean remove (Object o) {
                return false;
            }

            @Override
            public boolean containsAll (Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll (Collection<? extends Location> c) {
                return false;
            }

            @Override
            public boolean addAll (int index, Collection<? extends Location> c) {
                return false;
            }

            @Override
            public boolean removeAll (Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll (Collection<?> c) {
                return false;
            }

            @Override
            public void clear () {

            }

            @Override
            public Location get (int index) {
                return null;
            }

            @Override
            public Location set (int index, Location element) {
                return null;
            }

            @Override
            public void add (int index, Location element) {

            }

            @Override
            public Location remove (int index) {
                return null;
            }

            @Override
            public int indexOf (Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf (Object o) {
                return 0;
            }

            @Override
            public ListIterator<Location> listIterator () {
                return null;
            }

            @Override
            public ListIterator<Location> listIterator (int index) {
                return null;
            }

            @Override
            public List<Location> subList (int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public String getQuery() {
                return String.format("SELECT * FROM %S",Server.class.getSimpleName());
            }
        }.execute();
    }
    public List<Subject> getSubjects(){
        return new AbstractDbSelectExecutor<Subject>() {
            @Override
            public int size () {
                return 0;
            }

            @Override
            public boolean isEmpty () {
                return false;
            }

            @Override
            public boolean contains (Object o) {
                return false;
            }

            @Override
            public Iterator<Location> iterator () {
                return null;
            }

            @Override
            public Object[] toArray () {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray (T[] a) {
                return null;
            }

            @Override
            public boolean add (Location location) {
                return false;
            }

            @Override
            public boolean remove (Object o) {
                return false;
            }

            @Override
            public boolean containsAll (Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll (Collection<? extends Location> c) {
                return false;
            }

            @Override
            public boolean addAll (int index, Collection<? extends Location> c) {
                return false;
            }

            @Override
            public boolean removeAll (Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll (Collection<?> c) {
                return false;
            }

            @Override
            public void clear () {

            }

            @Override
            public Location get (int index) {
                return null;
            }

            @Override
            public Location set (int index, Location element) {
                return null;
            }

            @Override
            public void add (int index, Location element) {

            }

            @Override
            public Location remove (int index) {
                return null;
            }

            @Override
            public int indexOf (Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf (Object o) {
                return 0;
            }

            @Override
            public ListIterator<Location> listIterator () {
                return null;
            }

            @Override
            public ListIterator<Location> listIterator (int index) {
                return null;
            }

            @Override
            public List<Location> subList (int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public String getQuery() {
                return "SELECT * FROM SUBJECT";
            }
        }.execute();
    }
}
