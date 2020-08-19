package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable {

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;
        Solution solution = (Solution) o;
        return users.equals (solution.users);
    }

    @Override
    public int hashCode () {
        return Objects.hash (users);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }
    protected Solution clone() throws CloneNotSupportedException {
        Solution solution = new Solution();
        for (Map.Entry<String,User> map:this.users.entrySet()){
            User userOriginal = map.getValue();
            User userClone = new User(userOriginal.age,userOriginal.name);
            solution.users.put(map.getKey(),userClone);
        }
        return solution;
    }
    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
        public User clone() throws CloneNotSupportedException {


            return (User) super.clone ();
        }

        @Override
        public boolean equals (Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return age == user.age &&
                    name.equals (user.name);
        }

        @Override
        public int hashCode () {
            return Objects.hash (age, name);
        }
    }
}
