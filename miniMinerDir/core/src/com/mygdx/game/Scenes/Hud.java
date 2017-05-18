package com.mygdx.game.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Tools.PauseScreenHandler;
import com.mygdx.game.Tools.StoreHandler;
import com.mygdx.game.Tools.TouchpadHandler;
import com.mygdx.game.Tools.DrillButtonHandler;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.event.*;
import com.mygdx.game.items.FuelTank;
import com.mygdx.game.items.Hull;

/**
 * Created by erikstrid on 2017-04-02.
 */

public class Hud implements Disposable, IListener, IHudUpdater {


    public Stage stage;
    public Stage stage2;
    public Table table2;

    private Viewport viewport;


    public Integer score;

    private Label scoreLabel;
    private Label fuelLabel;
    private Label hullLabel;

    private ImageButton pauseBtn;

    //TODO fix public
    public TouchpadHandler tpHandler;
    private StoreHandler storeHandler;
    private DrillButtonHandler dbHandler;
    private PauseScreenHandler psHandler;





    /**

     * Creates the HUD for the framework of the game
     *
     * @param spriteBatch
     */

    public Hud(SpriteBatch spriteBatch) {

        Texture myTexture = new Texture(("pause-26.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        pauseBtn = new ImageButton(myTexRegionDrawable);
        pauseBtn.scaleBy(0.1f);
        pauseBtn.addListener(new ClickListener() {


            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.print("CLICK.");
                psHandler.setPaused(true);
                return true;
            }

        })
        ;

        tpHandler = new TouchpadHandler();
        storeHandler = new StoreHandler();
        dbHandler = new DrillButtonHandler();
        psHandler = new PauseScreenHandler();

        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        stage2 = new Stage(viewport, spriteBatch);


        Skin storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"),
                new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));




        //Create a Stage and add TouchPad

        pauseBtn.setTransform(true);


        stage = new Stage(viewport, spriteBatch);
        stage.addActor(dbHandler.getdrillButton());
        stage.addActor(storeHandler.getStorePopup());
        stage.addActor(tpHandler.getTouchpad());
        stage.addActor(pauseBtn);
        Gdx.input.setInputProcessor(stage);

        score = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);


        fuelLabel = new Label("100%", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        hullLabel = new Label("100", new Label.LabelStyle(new BitmapFont(), Color.RED));

        table.add(fuelLabel).expandX().padTop(5);
        table.add(scoreLabel).expandX().padTop(5);
        table.add(hullLabel).expandX().padTop(5);
        table.add(pauseBtn).expandX().padTop(10);


        stage.addActor(table);
        Listener.BUS.addListener(this);
        HudUpdater.FUEL.addListener(this);

        //table.setFillParent(true);

        table2 = new Table(storeSkin);
        table2.center();
        table2.setBounds(10, 90, 190, 210);
        //table.add(miniMinerLabel);
        //table.row();
        table2.add(psHandler.getResumeButton());
        table2.row();
        table2.add(psHandler.getMenuButton());
        stage2.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
        stage2.addActor(table2);
        //table2.setVisible(false);


    }



    public boolean isPaused(){
        return psHandler.isPaused();
    }

    public boolean isNewScreen(){
        return psHandler.isNewScreen();
    }

    public void setIsNewScreen(boolean val){
        psHandler.setNewScreen(val);
    }



    private void adjustFuelLabel(Integer fuel, Color color, String fuelString) {
        fuelLabel.setColor(color);
        fuelLabel.setText(fuelString);
    }


    public void toggleStoreVisibility() {
        storeHandler.getStorePopup().setVisible(!storeHandler.getStorePopup().isVisible());
    }

    @Override
    public void update(Shout shout) {
        if (shout.getTag() == Shout.Tag.STORE) {
            toggleStoreVisibility();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void update(HudData data) {
        adjustFuelLabel(data.getFuel(), data.getColor(), data.getString());
    }
}
