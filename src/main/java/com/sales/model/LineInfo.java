    
package com.sales.model;


public class LineInfo {
    
    
   
    private String item;
    private double price;
    private int count;
    private InvoiceInfo invoice;

    public LineInfo() {
    }

    public LineInfo( String item, double price, int count, InvoiceInfo invoice) {
      
        this.item = item;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
    }

    public double getTotalLine (){
    
    return price*count;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "LineInfo{" + "num=" + invoice.getNum() + ", item=" + item + ", price=" + price + ", count=" + count + '}';
    }

    public InvoiceInfo getInvoice() {
        return invoice;
    }

    public String getAsCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
