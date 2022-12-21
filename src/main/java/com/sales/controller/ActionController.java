package com.sales.controller;

import com.sales.model.InvoiceInfo;
import com.sales.model.InvoicesTable1;
import com.sales.model.LineInfo;
import com.sales.model.LinesTable2;
import com.sales.view.InvoiceWindow;
import com.sales.view.Invoice_Frame;
import com.sales.view.LineWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Line;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ActionController implements ActionListener,ListSelectionListener 
{
    private Invoice_Frame frame;
    private InvoiceWindow invDialog;
    private LineWindow lineDialog;
    public ActionController(Invoice_Frame frame){
       this.frame=frame;
    };
    
    @Override
    public void actionPerformed(ActionEvent e) {
    String actionCommand = e.getActionCommand();
        System.out.println("Action" + actionCommand);
        switch (actionCommand){    
            case "Load File":
                loadFileMenu();
                break ;
            case "Save File":
                saveFileMenu();
                break ;
            case "Creat New Invoice":
                creatNewInvoiceButt();
                break ;
            case "Delete Invoice":
                deleteInvoiceBuut();
                break ;
            case "Creat New Item":
                creatNewItemButt();
                break ;
            case "Delet Item":
                deletItemButt();
                break ; 
            case "createInvoiceCancel":
                createInvoiceCancelButt();
            case "createInvoiceOK":
                createInvoiceOkButt();
                break ;
            case "createLineOK":
            createLineOK();
            case "createLineCancel":
                createLineCancelButt();
                break ;    
        }
    }

        @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        int selectedIndex = frame.getInvoiceTable().getSelectedRow();
        if(selectedIndex !=-1){
        System.out.println("you selected a row: " + selectedIndex);
        InvoiceInfo currentInvoice= frame.getInvoices().get(selectedIndex);
        frame.getInvoiceNumberLabel().setText(""+currentInvoice.getNum());
        frame.getInvoiceDateLabel().setText(currentInvoice.getDate());
        frame.getCustomerNamelabel().setText(currentInvoice.getCustomer());
        frame.getInvoiceTotallabel().setText(""+currentInvoice.getTotalInvoice());    
        LinesTable2 linesTable2 = new LinesTable2(currentInvoice.getLines());
        frame.getInvoiceItems().setModel(linesTable2);
        linesTable2.fireTableDataChanged();
        }
    }
   
    private void loadFileMenu() {
        JFileChooser filechoser = new JFileChooser();
        try{
       int fresult= filechoser.showOpenDialog(frame);
         if (fresult==JFileChooser.APPROVE_OPTION){
         File headerFile = filechoser.getSelectedFile();
         Path headerPaht = Paths.get(headerFile.getAbsolutePath());
         List <String> hesaderLines= Files.readAllLines(headerPaht);
         System.out.println ("Invoice Readed");      
         ArrayList <InvoiceInfo> invoicesarr = new ArrayList<>();
         for (String headerLine:hesaderLines){
           String[] headersPeacs =  headerLine.split(",");
           int invoiceNumber =Integer.parseInt(headersPeacs[0]); 
           String invoiceDate= headersPeacs[1];
           String customerName= headersPeacs[2];
           InvoiceInfo invoice =new InvoiceInfo(invoiceNumber,invoiceDate,customerName);
           invoicesarr.add(invoice);
         }
        
         fresult =filechoser.showOpenDialog(null);
         if (fresult == JFileChooser.APPROVE_OPTION){
         File lineFile = filechoser.getSelectedFile();
         Path linePtah = Paths.get(lineFile.getAbsolutePath());
         List<String> lineOfLines = Files.readAllLines(linePtah);
         for(String lineLines :lineOfLines){
          String linePeacs[] = lineLines.split(",");
          int invoiceNumber =Integer.parseInt(linePeacs[0]);
          String itemName= linePeacs[1];
          double itemPrice=Double.parseDouble(linePeacs[2]);
          int count=Integer.parseInt(linePeacs[3]);
          InvoiceInfo invo = null;
          for (InvoiceInfo invoice:invoicesarr)
          {
             if (invoice.getNum()==invoiceNumber){  
             invo= invoice;
             break;
             }
          }
         
        LineInfo line = new LineInfo(itemName,itemPrice,count,invo);
        invo.getLines().add(line);
          }
         }
         
         frame.setInvoices(invoicesarr);
         InvoicesTable1 invoicesTable1 = new InvoicesTable1(invoicesarr);
         frame.setInvoiceTabel1(invoicesTable1);
         frame.getInvoiceTable().setModel(invoicesTable1);
         frame.getInvoiceTabel1().fireTableDataChanged();
                 }
        } catch(IOException ex){
        ex.printStackTrace();}
        }

    private void saveFileMenu() {
        ArrayList<InvoiceInfo> invoices = frame.getInvoices();
        String headers = "";
        String lines = "";
        for (InvoiceInfo invoice : invoices) {
            String invCSV = invoice.getAsCSV();
            headers += invCSV;
            headers += "\n";

            for (LineInfo line : invoice.getLines()) {
                String lineCSV = line.getAsCSV();
                lines += lineCSV;
                lines += "\n";
            }
        }
        System.out.println("Check point");
        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                hfw.write(headers);
                hfw.flush();
                hfw.close();
                result = fc.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    FileWriter lfw = new FileWriter(lineFile);
                    lfw.write(lines);
                    lfw.flush();
                    lfw.close();
                }
            }
        } catch (Exception ex) {

        }
          }

    private void creatNewInvoiceButt() {
        
        invDialog = new InvoiceWindow(frame) ;
        invDialog.setVisible(true);
        }

    private void deleteInvoiceBuut() {
      
        int SelectedRow= frame.getInvoiceTable().getSelectedRow();
        if (SelectedRow != -1){
        frame.getInvoices().remove(SelectedRow);
        frame.getInvoiceTabel1().fireTableDataChanged();
        }
    }

    private void creatNewItemButt() {
     
         lineDialog = new LineWindow(frame);
        lineDialog.setVisible(true);
        }

    private void deletItemButt() 
    {    
        int selectedInv =frame.getInvoiceTable().getSelectedRow();
        int selectedRow= frame.getInvoiceItems().getSelectedRow();
        if (selectedInv !=-1 && selectedRow != -1){
            InvoiceInfo invoice = frame.getInvoices().get(selectedInv);
            invoice.getLines().remove(selectedRow);
        LinesTable2 linesTable2 = new LinesTable2(invoice.getLines());
        frame.getInvoiceItems().setModel(linesTable2);
        linesTable2.fireTableDataChanged();
        }
        }
    
        private void createInvoiceOkButt() 
        {
        String date = invDialog.getInvoDateField().getText();
        String customer = invDialog.getCustomerNameField() .getText();
        int number = frame.getNextInvNumber();
        InvoiceInfo invoice = new InvoiceInfo(number,date,customer);
        frame.getInvoices().add(invoice);
        frame.getInvoiceTabel1().fireTableDataChanged();
    }

    private void createInvoiceCancelButt() {    
        invDialog.setVisible(false);
        invDialog.dispose();
        invDialog = null;
    }
    
    private void createLineOK() 
    {
         String item = lineDialog.getItemNameField().getText();
        String countStr = lineDialog.getItemCountField().getText();
        String priceStr = lineDialog.getItemPriceField().getText();
        int count = Integer.parseInt(countStr);
        double price = Double.parseDouble(priceStr);
        int selectedInvoice = frame.getInvoiceTable().getSelectedRow();
        if (selectedInvoice != -1)
        {
            InvoiceInfo invoice = frame.getInvoices().get(selectedInvoice);
            LineInfo line;
             line = new LineInfo(item, price, count, invoice);
            invoice.getLines().add(line);
            LinesTable2 linesTableModel = (LinesTable2) frame.getInvoiceItems().getModel();
            linesTableModel.fireTableDataChanged();
            frame.getInvoiceTabel1().fireTableDataChanged();
        }
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;   
    }

    private void createLineCancelButt() {
            lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }
}
