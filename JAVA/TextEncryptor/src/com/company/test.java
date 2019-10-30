package com.company;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class test implements ItemListener {


    test() {
        JFrame pane = new JFrame();
        pane.setLayout(new GridBagLayout());

        JButton button;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JButton button1 = new JButton("Button 1");

        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        JLabel label1 = new JLabel("TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST");
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel2.add(button4);
        JPanel top = new JPanel(new GridBagLayout());

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.ipady = 30;      //make this component tall
        c.gridwidth = 3;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;

        top.add(panel1, c);
        c.weightx = 0.5;
        c.ipady = 0;      //make this component tall
        c.gridwidth = 3;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 1;
        top.add(panel2, c);

        c.weightx = 0.5;
        c.ipady = 10;      //make this component tall
        c.gridwidth = 3;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(top, c);
        panel2.setBackground(Color.decode("#336673"));


        button = new JButton("Long-Named Button 4");
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;      //make this component tall
        c.weightx = 1;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(button, c);

        /*
        button = new JButton("5");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        pane.add(button, c);


         */




        pane.setVisible(true);
        pane.setSize(500, 200);
        pane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    public static void main(String[] args) {
        new test();


    }

    public void itemStateChanged(ItemEvent evt) {
    }
}