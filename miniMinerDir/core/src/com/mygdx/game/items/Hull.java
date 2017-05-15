package com.mygdx.game.items;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.awt.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Omaroueidat on 03/05/17.
 */

/**
 * The class that dictates the health of the miner
 */
public class Hull implements IGear{

    /**
     * Variable for the ammount of health
     */
    private Integer hull;

    /**
     * Constructor which gives our default value for the health and creates a label;
     */
    public Hull(){
        hull = 100;

    }


    /**
     *
     * @return hull
     */
    public Integer getHull() {
        return hull;
    }
}
