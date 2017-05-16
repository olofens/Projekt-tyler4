package com.mygdx.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;

/**
 * Created by walling on 5/16/2017.
 */

public class Gold extends TileTemplate {
    public Gold(World world, TiledMap tiledMap, Rectangle constrains) {
        super(world, tiledMap, constrains);
        fixture.setUserData(this);
        setCategoryFilter(Constants.GOLD_BIT);
    }

    @Override
    public void onDrillHit() {
        Gdx.app.log("Gold","Collision");
        setCategoryFilter(Constants.DESTROYED_BIT);
        System.out.println("Gold added to inventory");
        getCell().setTile(null);
    }


    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("Diggable");
        return layer.getCell((int)((body.getPosition().x)* Constants.PPM/32),
                (int)((body.getPosition().y)*Constants.PPM/32));
    }
}
