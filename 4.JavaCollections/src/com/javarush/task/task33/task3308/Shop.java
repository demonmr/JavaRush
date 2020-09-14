package com.javarush.task.task33.task3308;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name ="shop")
@XmlType(name = "shop")
public class Shop {

    public Goods goods;
    @XmlElement(name = "count")
    public int count;
    @XmlElement(name = "profit")
    public double profit;
    @XmlElement(name = "secretData")
    public String[] secretData;
    public static class Goods{
        @XmlElement(name = "names")
        List<String> names = new ArrayList<> ();
    }
}
