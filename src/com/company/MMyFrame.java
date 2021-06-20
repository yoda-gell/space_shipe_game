package com.company;

import com.company.entities.Enemy;
import com.company.entities.Shot;
import com.company.entities.SpaceShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MMyFrame implements KeyListener {

    private boolean startShoot;
    private boolean pose;
    private int XmoveEnemy1;
    private int YmoveEnemy1;
    private int XmoveEnemy2;
    private int YmoveEnemy2;
    private int score;
    private int speed;
    private final JLabel scoreDisplay;
    private final JLabel shotLabel;
    private final JFrame frame;
    private final JLabel container;
    private final JLabel gameOverDisplay;
    private final JProgressBar healthBar;
    private final SpaceShip spaceShip;
    private final Shot shot;
    private final Enemy enemy1;
    private final Enemy enemy2;

    public MMyFrame() {
        score = 0;
        speed = 5;
        pose = false;
        startShoot = false;
        ImageIcon backgroundImage = new ImageIcon("spa.jpg");

        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        frame.setVisible(true);
        frame.addKeyListener(this);

        scoreDisplay = new JLabel("Score: " + score);
        scoreDisplay.setBounds(20,-20,400,100);
        scoreDisplay.setFont(new Font("MV Boil", Font.BOLD, 20));
        scoreDisplay.setForeground(Color.black);

        gameOverDisplay = new JLabel(" GAME OVER! ");
        gameOverDisplay.setBounds(backgroundImage.getIconHeight() / 4,backgroundImage.getIconWidth() / 5,500,300);
        gameOverDisplay.setFont(new Font("MV Boil", Font.BOLD, 50));
        gameOverDisplay.setForeground(Color.red);
        gameOverDisplay.setVisible(false);

        healthBar = new JProgressBar(0, 100);
        healthBar.setValue(100);
        healthBar.setBounds(400, 20, 200, 30);
        healthBar.setStringPainted(true);
        healthBar.setFont(new Font("MV Boil", Font.BOLD, 20));
        healthBar.setForeground(Color.red);
        healthBar.setBackground(Color.black);

        spaceShip = new SpaceShip(new Point(backgroundImage.getIconWidth() / 3, backgroundImage.getIconHeight() / 2));
        shot = new Shot(new Point(210, 230));
        shotLabel = shot.getImageLabel();
        shotLabel.setVisible(false);
        enemy1 = new Enemy("enemy space.png", new Point(0, 0));
        enemy2 = new Enemy("enemy space 2.png", new Point(500, 0));

        XmoveEnemy1 = speed;
        YmoveEnemy1 = enemy1.getBounds().y;
        XmoveEnemy2 = speed;
        YmoveEnemy2 = enemy2.getBounds().y;

        container = new JLabel(backgroundImage);
        container.setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        container.add(gameOverDisplay);
        container.add(scoreDisplay);

        container.add(enemy2.getImageLabel());
        container.add(enemy1.getImageLabel());
        container.add(shotLabel);
        container.add(spaceShip.getImageLabel());

        container.add(healthBar);

        frame.add(container);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        runGameLoop();
    }

    private void runGameLoop() {
        new Thread(() ->
        {
            while (true) {
                try {
                    if (startShoot) {
                        shot.setLocation(shot.getBounds().x, shot.getBounds().y - 10);
                    }

                    if(shot.getBounds().y <= -150){
                        shot.setLocation(spaceShip.getBounds().x, spaceShip.getBounds().y);
                        startShoot = false;
                        pose = true;
                        shotLabel.setVisible(false);
                    }

                    if (enemy1.getBounds().x < 0 || enemy1.getBounds().x > 500) {
                        XmoveEnemy1 *= -1;
                        YmoveEnemy1 += 50;
                    }
                    enemy1.setLocation(enemy1.getBounds().x + XmoveEnemy1, YmoveEnemy1);

                    if (enemy2.getBounds().x < 0 || enemy2.getBounds().x > 500) {
                        XmoveEnemy2 *= -1;
                        YmoveEnemy2 += 50;
                    }
                    enemy2.setLocation(enemy2.getBounds().x + XmoveEnemy2, YmoveEnemy2);
                    handleShot(enemy1);
                    handleShot(enemy2);
                    if(enemy1.intersects(spaceShip) || enemy2.intersects(spaceShip)){
                        gameOver();
                    }

                    if(enemy1.getBounds().y >= 400 || enemy2.getBounds().y >= 400){
                        gameOver();
                    }

                    frame.repaint();
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void handleShot(Enemy enemy) {
        if(!(enemy.intersects(shot) && startShoot)) return;
            if(enemy.isGreen()) {
                enemy1.setLocation(0, 0);
            } else {
                enemy2.setLocation(500, YmoveEnemy2=0);
            }
            shot.setLocation(spaceShip.getPosition().x, spaceShip.getPosition().y);
            startShoot = false;
            pose = true;
            shotLabel.setVisible(false);
            score +=50;
            scoreDisplay.setText("score: " + score);
    }

    private void gameOver() {
        container.remove(enemy1.getImageLabel());
        container.remove(enemy2.getImageLabel());
//      container.remove(shot.getImage());
        container.remove(spaceShip.getImageLabel());
        gameOverDisplay.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a' -> {
                spaceShip.setLocation(spaceShip.getBounds().x - 10, spaceShip.getBounds().y);
                if (spaceShip.getBounds().x < -207) {
                    spaceShip.setLocation(550, spaceShip.getBounds().y);
                }
            }
            case 'd' -> {
                spaceShip.setLocation(spaceShip.getBounds().x + 10, spaceShip.getBounds().y);
                if (spaceShip.getBounds().x > 550) {
                    spaceShip.setLocation(-207, spaceShip.getBounds().y);
                }
            }
            case 's' -> spaceShip.setLocation(spaceShip.getBounds().x, spaceShip.getBounds().y + 10);
            case 'w' -> spaceShip.setLocation(spaceShip.getBounds().x, spaceShip.getBounds().y - 10);
            case ' ' -> {
                shotLabel.setVisible(true);
                if (pose) {
                    shot.setLocation(spaceShip.getPosition().x, spaceShip.getPosition().y);
                }
                startShoot = true;
                pose = false;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
