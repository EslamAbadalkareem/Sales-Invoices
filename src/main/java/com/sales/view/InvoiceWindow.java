package com.sales.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InvoiceWindow extends JDialog {
    private JTextField customerNameField;
    private JTextField invoDateField;
    private JLabel customerNameLbl;
    private JLabel invDateLbl;
    private JButton okButt;
    private JButton cancelButt;

    public InvoiceWindow(Invoice_Frame frame)
    {
        customerNameLbl = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        invDateLbl = new JLabel("Invoice Date:");
        invoDateField = new JTextField(20);
        okButt = new JButton("OK");
        cancelButt = new JButton("Cancel");   
        okButt.setActionCommand("createInvoiceOK");
        cancelButt.setActionCommand("createInvoiceCancel");   
        okButt.addActionListener(frame.getController());
        cancelButt.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        add(invDateLbl);
        add(invoDateField);
        add(customerNameLbl);
        add(customerNameField);
        add(okButt);
        add(cancelButt);
        pack();
        }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getInvoDateField() {
        return invoDateField;
    }
}