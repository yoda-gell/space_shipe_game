package com.company.entities;

import java.awt.*;

public class Shot extends Entity {

    public Shot(Point position) {
        super("Shooting1.png", position, new Rectangle(position.x, position.y, 30, 10));
    }

}
