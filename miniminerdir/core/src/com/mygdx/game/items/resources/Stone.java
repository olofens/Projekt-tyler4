package com.mygdx.game.items.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.items.IResource;
import com.mygdx.game.items.TileTemplate;

/**
 * Created by walling on 5/16/2017.
 */

public class Stone extends TileTemplate implements IResource {
    public Stone(World world, TiledMap tiledMap, Rectangle constrains) {
        //Se Dirt-class for commenting
        super(world, tiledMap, constrains);
        fixture.setUserData(this);
        setCategoryFilter(Constants.STONE_BIT);
    }

    @Override
    public void onDrillHit() {
        Gdx.app.log("Stone", "Collision");
    }
}