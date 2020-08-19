package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }
        protected B clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException ();
        }

        public String getName() {
            return name;
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }
        protected C clone()
        {
            B b = new B (this.getI (),this.getJ (),this.getName ());
            return new C(b.getI (),b.getJ (),b.name);
        }
    }

    public static void main(String[] args) {

    }
}
