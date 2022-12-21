
package com.sales.model;

import java.util.ArrayList;


public class InvoiceInfo {
   private int num;
   private String date;
   private String customer;
   private ArrayList<LineInfo> lines ;
   

    public InvoiceInfo() {
    } 

    public InvoiceInfo(int age, String date, String customer) {
        this.num = age;
        this.date = date;
        this.customer = customer;
    }

    public double getTotalInvoice(){
    
        double total = 0.0 ;
        for (LineInfo line : getLines()){
        
        total+=line.getTotalLine();
        } 
        
    return total;
   }
   
    
    public ArrayList<LineInfo> getLines() {
        if (lines==null)
            lines = new ArrayList<>();
        
        return lines;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int age) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "InvoiceInfo{" + "num=" + num + ", date=" + date + ", customer=" + customer + '}';
    }

             public String getAsCSV() {
        return num + "," + date + "," + customer;
    }
    
}
