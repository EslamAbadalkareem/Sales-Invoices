package com.sales.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LineWindow extends JDialog{
    private JTextField itemNameField;
    private JTextField itemCountField;
    private JTextField itemPriceField;
    private JLabel itemNameLabel;
    private JLabel itemCountLabel;
    private JLabel itemPriceLabel;
    private JButton okButt;
    private JButton cancelButt;
    
    public LineWindow(Invoice_Frame frame)
    {
        itemNameField = new JTextField(20);
        itemNameLabel = new JLabel("Item Name");
        itemCountField = new JTextField(20);
        itemCountLabel = new JLabel("Item Count");
        itemPriceField = new JTextField(20);
        itemPriceLabel = new JLabel("Item Price"); 
        okButt = new JButton("OK");
        cancelButt = new JButton("Cancel");    
        okButt.setActionCommand("createLineOK");
        cancelButt.setActionCommand("createLineCancel");
        okButt.addActionListener(frame.getController());
        cancelButt.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        add(itemNameLabel);
        add(itemNameField);
        add(itemCountLabel);
        add(itemCountField);
        add(itemPriceLabel);
        add(itemPriceField);
        add(okButt);
        add(cancelButt);
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }
    public JTextField getItemCountField() {
        return itemCountField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
}