
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;




public class InvoicesTable1 extends AbstractTableModel {
    
    private ArrayList<InvoiceInfo> invoices ;

    private String[] columnsNames ={"Nomber","Date","Customar","Total"};
     
    public InvoicesTable1(ArrayList<InvoiceInfo> invoices) {
        this.invoices = invoices;
    }

    
    @Override
    public int getRowCount() {

    return invoices.size();
    }

    @Override
    public int getColumnCount() {

      return columnsNames.length;
    }

   
    @Override
    
    public String getColumnName(int column){
    
        return columnsNames[column];
    
    };
    
    public Object getValueAt(int rowIndex, int columnIndex) {

       InvoiceInfo invoice =invoices.get(rowIndex);
       switch (columnIndex){
           case 0 :return invoice.getNum();
           case 1 :return invoice.getDate();
           case 2 :return invoice.getCustomer();
           case 3 : return invoice.getTotalInvoice();
           default : return "";
       }
    }
}
