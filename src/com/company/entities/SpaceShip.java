package com.company.entities;

import java.awt.*;

public class SpaceShip extends Entity {

    public SpaceShip(Point position) {
        super("spaceship1.png", position, new Rectangle(position.x, position.y, 50, 120));
    }

}
