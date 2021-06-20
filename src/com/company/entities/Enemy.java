package com.company.entities;

import java.awt.*;

public class Enemy extends Entity {

    private final boolean isGreen;

    public Enemy(String imageUri, Point position) {
        super(imageUri, position, new Rectangle(position.x, position.y, 30, 30));
        isGreen = imageUri.equals("enemy space.png");
    }

    public boolean isGreen() {
        return isGreen;
    }
}
