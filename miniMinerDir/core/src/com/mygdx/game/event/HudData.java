package com.mygdx.game.event;

import java.awt.*;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Olof Enström on 2017-05-16.
 */

public class HudData {
    private int fuel;
    private int hull;
    private Color color;

    public HudData(int fuel, int hull, Color color) {
        this.fuel = fuel;
        this.hull = hull;
        this.color = color;
    }

    public int getFuel() {
        return fuel;
    }

    public int getHull() {
        return hull;
    }

    public Color getColor(){
        return color;
    }
}
