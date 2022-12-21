
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class LinesTable2 extends AbstractTableModel {

    
    private ArrayList<LineInfo> lines;
    private String[] columnsNames ={"Nomber","Item Named","Item Price","count","Itme Total"};

    public LinesTable2(ArrayList<LineInfo> lines) {
        this.lines = lines;
    }
    
    
    
    @Override
    public int getRowCount() {

     return lines.size();
    }

    @Override
    public int getColumnCount() {

     return columnsNames.length; 
    }
    
   public String getColumnName(int c){
   
   return columnsNames[c];
   }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

     LineInfo line = lines.get(rowIndex);
     
     switch (columnIndex) {
         case 0 :return line.getInvoice().getNum();
         case 1 :return line.getItem();
         case 2 :return line.getPrice();
         case 3 :return line.getCount();
         case 4 :return line.getTotalLine();
         default:return "";
     }
     
    }
    
    
}
