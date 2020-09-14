package com.javarush.task.task33.task3309;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* 
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        StringWriter stringWriter = new StringWriter ();
        String t="";
        try {
            JAXBContext context = JAXBContext.newInstance (obj.getClass ());
            Marshaller marshaller = context.createMarshaller ();
            marshaller.setProperty (Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal (obj,stringWriter);
           String result = stringWriter.toString ();
           String com = String.format ("<!--%s-->\n",comment);
           t = result.replaceAll ("<"+tagName+">",com+"\t<"+tagName+">");
           // System.out.println (t);
        } catch (JAXBException e) {
            e.printStackTrace ();
        }


        return t;
    }

    public static void main(String[] args) {
        Cat cat = new Cat ();
        cat.name = "Murka";
        cat.age=100;
        cat.weight=1000;
String s=toXmlWithComment (cat,"age","fuch you");
        System.out.println (s);
    }
    @XmlRootElement()
    @XmlType(name = "cat")
    static class Cat
    {
        @XmlElement(name = "name")
        public String name;
        @XmlElement(name = "age")
        public int age;
        @XmlElement(name = "age")
        public int weight;
    }
}
