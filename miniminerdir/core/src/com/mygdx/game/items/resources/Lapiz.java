package com.mygdx.game.items.resources;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;

/**
 * Created by walling on 5/15/2017.
 */

public class Lapiz implements IResource {
    private Resource parent;

    public Lapiz(World world, TiledMap tiledMap, Rectangle constrains) {
        parent = new Resource(world, tiledMap, constrains, this, Constants.LAPIZ_BIT);

    }

    @Override
    public void onDrillHit() {
        parent.onDrillHit();
    }

}
