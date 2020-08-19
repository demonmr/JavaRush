package com.parseFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.collections4.list.TreeList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class ParseFile {
    public static void main (String[] args) {
        try {
            HashSet<String> ibanlist= new LinkedHashSet<> ();



            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("res");
            Row row;
            int i=3;
            long inn = 0;
            String name = "";
            long mfo = 0;
            int rowid=3;
            String iban = "";
            BufferedReader reader = new BufferedReader (new InputStreamReader(new FileInputStream("D:\\export.txt"), "Windows-1251"));

          //  BufferedReader reader = new BufferedReader (new FileReader (), "UTF-8");
            String str;
            List<String> list = new ArrayList<> ();
            while ((str = reader.readLine ()) != null) {
                if (str.contains ("RCPT_INN")) {
                    inn = Long.parseLong (str.split ("=")[1]);
                }
                if (str.contains ("RCPT_NAME")) {
                    name = str.split ("=")[1].trim ();
                }
                if (str.contains ("RCPT_BANK_BIC")) {
                    mfo = Long.parseLong (str.split ("=")[1]);
                }
                if (str.contains ("RCPT_ACCOUNT")) {

                    iban = str.split ("=")[1].trim ();
                }

                if (inn != 0 && !name.equals ("") && mfo != 0 && !iban.equals ("")) {
                    if (iban.contains ("UA")) {
                    int st=ibanlist.size ();
                    ibanlist.add (iban);
                            int end = ibanlist.size ();
                            if (end-st>0) {
                                row = sheet.createRow (rowid);
                                row.createCell (4, CellType.NUMERIC).setCellValue (inn);
                                row.createCell (5, CellType.STRING).setCellValue (iban);
                                rowid++;
                                list.add (name + " " + inn + "-" + iban + ":" + mfo);
                            }
                    }
                    i++;
                    name = "";
                    inn = 0;
                    iban = "";
                    mfo = 0;
                }


            }
            reader.close ();
            File file = new File("D:\\resul12t.xls");
            file.getParentFile().mkdirs();
            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
           System.out.println("Created file: " + file.getAbsolutePath());
        }catch (Exception e)
        {
            e.printStackTrace ();
        }
    }
}
