package com.mygdx.game.items;


import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.event.HudUpdater;

/**
 * Created by Omaroueidat on 11/05/17.
 */

/**
 * The class where the logic and calculations of the Miner will occur.
 */
public class MinerModel {

    private FuelTank ft;
    private Miner miner;
    private Hull hull;



    /**
     * Constructor which takes in world in order to create our Miner.
     * @param world
     */
    public MinerModel(World world){

        ft = new FuelTank();
        hull = new Hull();
        miner = new Miner(world);

    }

    /**
     * Getter for our miner
     * @return tnis.miner
     */

    public void update(){

        ft.adjustFuel((int) miner.b2body.getLinearVelocity().x, (int) miner.b2body.getLinearVelocity().y);
        HudUpdater.FUEL.updateHud(getFuelTank().getFuel());

    }
    public Miner getMiner(){
        return this.miner;
    }

    public FuelTank getFuelTank(){
        return ft;
    }

    public Hull getHull(){
        return hull;
    }
}
