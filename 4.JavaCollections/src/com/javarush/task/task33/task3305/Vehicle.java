package com.javarush.task.task33.task3305;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonAutoDetect
//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
public abstract class Vehicle {
    protected String name;
    protected String owner;
    protected int age;
}