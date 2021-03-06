package com.pca.gui;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class  ReadExcelPok {
    public static ArrayList<NumNom> nomFR() throws IOException {
        ArrayList<NumNom> nn = new ArrayList();
        try {

            String directerie ="/Users/fxc/IdeaProjects/jebay8/data/dataPasDeCommit/ajoutCartes/FR/";
            FileInputStream inputStream = new FileInputStream(new File(directerie+"ajoutCartes.xls"));

            // Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

            // Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                nn.add(new NumNom(row.getCell(0).toString(),row.getCell(1).toString()));
                //System.out.println(row.getCell(0) + " <====> " + row.getCell(1));

            }
        } catch (Exception ex) {
            System.out.print("E R R E U R : "+ex);
        }
        return nn;
    }
}

class NumNom implements Comparable<NumNom>{
    public String num;
    public String nom;
    NumNom(String num,String nom){
        this.num = num;
        this.nom = nom;
    }
    @Override
    public int compareTo(NumNom o) {
        return this.num.compareTo(o.num);
    }
}

