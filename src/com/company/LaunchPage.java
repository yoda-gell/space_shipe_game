package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class LaunchPage implements ActionListener {

    ImageIcon img;
    JFrame frame = new JFrame();
    JLabel label = new JLabel(img);
    JButton button = new JButton(" START GAME ");
    JButton button2 = new JButton( " EXIT " );

    LaunchPage(){
        img = new ImageIcon("oneWind.Jpg");

        label.setSize(img.getIconWidth(),img.getIconHeight());

        label =new JLabel(img);


        button2.setBounds(img.getIconWidth()/4,img.getIconHeight()/2-img.getIconHeight()/11,300,40);
        button2.setFocusable(false);
        button2.setOpaque(false);
        // button.setContentAreaFilled(false);
        button2.setBorderPainted(true);
        button2.setFont(new Font("MV Boil", Font.PLAIN, 30));
        button2.setForeground(Color.RED);
        button2.setBackground(Color.BLACK);
        button2.addActionListener(this);


        button.setBounds(img.getIconWidth()/4,img.getIconHeight()/2-img.getIconHeight()/6,300,40);
        button.setFocusable(false);
        button.setOpaque(false);
       // button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        button.setFont(new Font("MV Boil", Font.PLAIN, 30));
        button.setForeground(Color.CYAN);
        button.setBackground(Color.BLACK);
        button.addActionListener(this);

        frame.add(button2);
        frame.add(button);
        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        //frame.setLayout(null);
        frame.setSize(img.getIconWidth(),img.getIconHeight());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            frame.dispose();
//           myFrame myFrame =new myFrame();
            new myFrame();
        }
        if(e.getSource() == button2){
            System.exit(0);
        }
    }


}
