package com.company.entities;

import javax.swing.*;
import java.awt.*;

public abstract class Entity {

    private final JLabel image;
    private final Point position;
    private final Rectangle bounds;

    public boolean intersects(Entity entity) {
        return this.bounds.intersects(entity.bounds);
    }

    protected Entity(String imageUri, Point position, Rectangle bounds) {
        this.image = new JLabel();
        this.image.setIcon(new ImageIcon(imageUri));
        this.position = position;
        this.image.setLocation(position.x, position.y);
        this.bounds = bounds;
    }

    public void setLocation(int x, int y) {
        image.setLocation(x, y);
        position.x = x;
        position.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public JLabel getImageLabel() {
        return image;
    }

    public Point getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
