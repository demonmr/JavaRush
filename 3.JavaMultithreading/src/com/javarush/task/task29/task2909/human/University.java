package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University{
    private String name;
    private int age;
    private List<Student> students=new ArrayList<> ();;


    public University(String name, int age) {
        this.name=name;
        this.age=age;

    }

    public Student getStudentWithAverageGrade(double ball) {
        for (Student sts:students
             ) {
            if (sts.getAverageGrade ()==ball)
            {
                return sts;
            }

        }
        //TODO:
        return null;
    }
    public void setName (String name) {
        this.name = name;
    }
    public String getName () {
        return name;
    }
    public int getAge () {
        return age;
    }

    public void setAge (int age) {
        this.age = age;
    }
    public List<Student> getStudents () {
        return students;
    }

    public void setStudents (List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithMaxAverageGrade() {
        double ball=0;
        Student student=null;
        for (Student sts:students
             ) {
            if (ball<sts.getAverageGrade ())
            {
                ball=sts.getAverageGrade ();
                student=sts;
            }

        }
        return student;
    }

    public Student getStudentWithMinAverageGrade(){
        double ball=100;
        Student st =null;
        for (Student sts:students
             ) {
            if (ball>sts.getAverageGrade ())
            {
                ball=sts.getAverageGrade ();
                st=sts;
            }

        }
        return st;
    }
    public void expel(Student student){
        students.remove(student);
    }

}