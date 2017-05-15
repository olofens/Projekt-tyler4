package com.mygdx.game.event;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omaroueidat on 15/05/17.
 */
public enum HudUpdater {

    FUEL;

    private List<IHudUpdater> hudUpdaters = new ArrayList<IHudUpdater>();

    public void addListener(IHudUpdater newListener) {
        hudUpdaters.add(newListener);
    }

    public void updateHud(int fuel) {

        for (IHudUpdater hudUpdater : hudUpdaters) {
            hudUpdater.update(fuel);
        }
    }

}
