package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class myFrame implements KeyListener {

    final int enemyWidth  = 30;
    final int enemyHeight = 30;
    final int enemy2Width = 30;
    final int enemy2Height = 30;
    final int shotWidth   = 30;
    final int shotHeight  = 10;
    final int shipWidth   = 50;
    final int shipHeight  = 120;

    int score1 = 0;
    int moreSpeed= 5;


    ImageIcon img, ing,ing1,emy,emy2;
    JButton button =new JButton("Play again");
    JFrame frame = new JFrame();
    JLabel label = new JLabel(img);
    JLabel rokcet = new JLabel(img);
    JLabel shoot = new JLabel(ing1);
    JLabel enemy = new JLabel(emy);
    JLabel enemy2 = new JLabel(emy2);
    JLabel score = new JLabel("score: "+score1);
    JLabel endGame =new JLabel(" GAME OVER ");

    Rectangle shotRect;
    Rectangle enemyRec;
    Rectangle enemy2Rec;
    Rectangle shipRec;

    JProgressBar bar = new JProgressBar(0, 100);

    myFrame() {

        img = new ImageIcon("spa.jpg");
        ing = new ImageIcon("spaceship1.png");
        ing1 = new ImageIcon("Shooting1.png");
        emy = new ImageIcon("eneny space 2.png");
        emy2 = new ImageIcon("eneny space.png");

        button.setBounds(img.getIconWidth()/4,img.getIconHeight()-img.getIconHeight()/2,300,40);
        button.setVisible(false);
        button.setEnabled(false);
        button.setFocusable(false);
        button.setOpaque(false);
        button.setBorderPainted(true);
        button.setFont(new Font("MV Boil", Font.PLAIN, 30));
        button.setForeground(Color.getHSBColor(122,22,233));
        button.setBackground(Color.BLACK);
        button.addActionListener(e ->  new myFrame());

        score.setBounds(20,-20,400,100);
        score.setFont(new Font("MV Boil", Font.BOLD, 20));
        score.setForeground(Color.black);

        endGame.setBounds(img.getIconHeight()/4,img.getIconWidth()/5,500,300);
        endGame.setFont(new Font("MV Boil", Font.BOLD, 50));
        endGame.setForeground(Color.red);
        endGame.setVisible(false);

        enemy2.setBounds(500,0, emy2.getIconWidth(), emy2.getIconHeight());
        enemy2.setIcon(emy2);

        enemy.setBounds(0,0, emy.getIconWidth(), emy.getIconHeight());
        enemy.setIcon(emy);

        enemyRec = new Rectangle(enemy.getX(), enemy.getY(), enemyWidth, enemyHeight);
        enemy2Rec = new Rectangle(500, 0, enemy2Width, enemy2Height);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(img.getIconWidth(), img.getIconHeight());
        frame.setVisible(true);

        shoot.setBounds(ing1.getIconWidth(), ing1.getIconHeight(),210,230);
        shoot.setIcon(ing1);
        shotRect = new Rectangle(shoot.getX(), shoot.getY(), shotWidth, shotHeight);
        shoot.setVisible(false);

        rokcet.setBounds(img.getIconWidth() / 3, img.getIconHeight() / 2, 210, 230);
        rokcet.setIcon(ing);
        shipRec = new Rectangle(rokcet.getX(),rokcet.getY(),shipWidth,shipHeight);
        frame.addKeyListener(this);

        label.setSize(img.getIconWidth(), img.getIconHeight());
        label = new JLabel(img);
        label.add(button);
        label.add(endGame);
        label.add(score);
        label.add(enemy2);
        label.add(enemy);
        label.add(shoot);
        label.add(rokcet);
        label.add(bar);

        frame.add(label);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        bar.setValue(100);
        bar.setBounds(400, 20, 200, 30);
        bar.setStringPainted(true);
        bar.setFont(new Font("MV Boil", Font.BOLD, 20));
        bar.setForeground(Color.red);
        bar.setBackground(Color.black);
        mainGameLoop();
       // posiiton1();
        // fill();

    }


    boolean pose = true;
    boolean startShoot = false;
    int XmoveEnemy1 =moreSpeed;
    int YmoveEnemy1 = enemy.getY();
    int XmoveEnemy2 =moreSpeed;
    int YmoveEnemy2 = enemy.getY();


    public void posiiton1() {
        pose = true;

        while (pose == true) {
            shoot.setLocation(rokcet.getX(), rokcet.getY());
            shotRect.setLocation(shoot.getX(), shoot.getY());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    public void fill() {
        int Conter = 100;

        while (Conter >= 0) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Conter -= 10;
        }
        bar.setString("dead XX");

    }

    private void mainGameLoop() {
        new Thread(() ->
        {
            while (true) {
                try {


                    if (startShoot == true){
                        shoot.setLocation(shoot.getX(),shoot.getY()-10);
                        shotRect.setLocation(shoot.getX(), shoot.getY());
                    }

                    if(shoot.getY()<=-150){
                        shoot.setLocation(rokcet.getX(),rokcet.getY());
                        shotRect.setLocation(shoot.getX(), shoot.getY());
                        startShoot = false;
                        pose = true;
                        shoot.setVisible(false);

                    }

                    if (enemy.getX() < 0 || enemy.getX() > 500) {
                        XmoveEnemy1 *= -1;
                        YmoveEnemy1 += 50;
                    }
                    enemy.setLocation(enemy.getX() + XmoveEnemy1, YmoveEnemy1);
                    enemyRec.setLocation(enemy.getX(), enemy.getY());

                    if (enemy2.getX() < 0 || enemy2.getX() > 500) {
                        XmoveEnemy2 *= -1;
                        YmoveEnemy2 += 50;
                    }
                    enemy2.setLocation(enemy2.getX() + XmoveEnemy2, YmoveEnemy2);
                    enemy2Rec.setLocation(enemy2.getX(), enemy2.getY());

                    if(shotRect.intersects(enemyRec)&&startShoot==true) {
                        enemy.setLocation(0,YmoveEnemy1=0);
                        enemyRec.setLocation(enemy.getX(), enemy.getY());
                        shoot.setLocation(rokcet.getX(),rokcet.getY());
                        shotRect.setLocation(shoot.getX(), shoot.getY());
                        startShoot = false;
                        pose = true;
                        shoot.setVisible(false);
                        score1 +=50;
                        score.setText("score: "+score1);


                    }
                    //System.out.printf("%d,%d %d,%d%n", shotRect.x, shotRect.y, enemy2Rec.x, enemy2Rec.y);
                    if(shotRect.intersects(enemy2Rec)&&startShoot==true) {
                        enemy2.setLocation(500,YmoveEnemy2=0);
                        enemy2Rec.setLocation(enemy2.getX(), enemy2.getY());
                        shoot.setLocation(rokcet.getX(),rokcet.getY());
                        shotRect.setLocation(shoot.getX(), shoot.getY());
                        startShoot = false;
                        pose = true;
                        shoot.setVisible(false);
                        score1 +=50;
                        score.setText("score: "+score1);


                    }

                    if(enemy2Rec.intersects(shipRec)){
                        enemy.setVisible(false);
                        enemy2.setVisible(false);
                        shoot.setVisible(false);
                        rokcet.setVisible(false);
                        bar.setForeground(Color.GRAY);
                        bar.setString("dead XX");
                        endGame.setVisible(true);
                        button.setVisible(true);
                        button.setEnabled(true);

                    }
                    if(enemyRec.intersects(shipRec)){
                        enemy.setVisible(false);
                        enemy2.setVisible(false);
                        shoot.setVisible(false);
                        rokcet.setVisible(false);
                        bar.setForeground(Color.GRAY);
                        bar.setString("dead XX");
                        endGame.setVisible(true);
                        button.setVisible(true);
                        button.setEnabled(true);



                    }
                    if(enemy.getY()>=400||enemy2.getY()>=400){
                        enemy.setVisible(false);
                        enemy2.setVisible(false);
                        shoot.setVisible(false);
                        rokcet.setVisible(false);
                        endGame.setVisible(true);
                    }
                    // System.out.println(enemy2.getY());

                    frame.repaint();
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

        switch (e.getKeyChar()){
            case 'a':
                rokcet.setLocation(rokcet.getX()-10,rokcet.getY());
                shipRec.setLocation(rokcet.getX(),rokcet.getY());
                if (rokcet.getX()<-207){

                    rokcet.setLocation(550,rokcet.getY());
                    shipRec.setLocation(rokcet.getX(),rokcet.getY());


                }
                break;
            case 'd':
                rokcet.setLocation(rokcet.getX()+10,rokcet.getY());
                shipRec.setLocation(rokcet.getX(),rokcet.getY());

                if (rokcet.getX()>550){
                    rokcet.setLocation(-207,rokcet.getY());
                    shipRec.setLocation(rokcet.getX(),rokcet.getY());


                }
                break;
            case 's':
                rokcet.setLocation(rokcet.getX(),rokcet.getY()+10);
                shipRec.setLocation(rokcet.getX(),rokcet.getY());


                break;
            case 'w':
                rokcet.setLocation(rokcet.getX(),rokcet.getY()-10);
                shipRec.setLocation(rokcet.getX(),rokcet.getY());


                break;
            case ' ':shoot.setVisible(true);
                if (pose == true) {
                    shoot.setLocation(rokcet.getX(),rokcet.getY());
                    shotRect.setLocation(shoot.getX(), shoot.getY());
                }
                startShoot = true;
                pose = false;
                // shoot.setLocation(shoot.getX(),shoot.getY()-10);
                break;
        }
    }



    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}