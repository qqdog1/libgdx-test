package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;
import name.qd.game.test.utils.MaterialCreator;

public class MenuScreen extends GameScreen {
    private Stage stage;
    private AssetManager assetManager;
    private Texture background;
    private TextButton btnUpgrade;
    private TextButton btnSettings;
    private TextButton btnStageSelect;

    private Table tableSettings;
    private boolean isShowSettings;

    private int background_y = 0;
    private int lastY;
    private int backgroundScaleHeight;

    public MenuScreen() {
        stage = new Stage(viewport, spriteBatch);
        assetManager = ResourceInstance.getInstance().getAssetManager();
        background = assetManager.get("pic/stage.png", Texture.class);
        backgroundScaleHeight = (int)(background.getHeight() * LibTest.SCALE_RATE);

        btnStageSelect = MaterialCreator.createButton(assetManager.get("pic/btn/stageselect.png", Texture.class));

        Gdx.input.setInputProcessor(stage);

        initButton();
        initSettingsTable();

        stage.addListener(new ClickListener() {
           @Override
           public void touchDragged (InputEvent event, float x, float y, int pointer) {
               super.touchDragged(event, x, y, pointer);
               if(lastY != 0) {
                   int diff = (int)y - lastY;
                   background_y += diff;
                   if(background_y > 0) {
                       background_y = 0;
                   }
                   if(background_y < -backgroundScaleHeight + LibTest.HEIGHT) {
                       background_y = -backgroundScaleHeight + LibTest.HEIGHT;
                   }
               }
               lastY = (int)y;
           }

           @Override
           public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
               super.touchUp(event, x, y, pointer, button);
               lastY = 0;
           }
        });
    }

    private void initButton() {
        btnSettings = MaterialCreator.createButton(assetManager.get("pic/btn/settings.png", Texture.class));

        btnSettings.setTransform(true);
        btnSettings.setX(10 * LibTest.SCALE_RATE);
        btnSettings.setY(10 * LibTest.SCALE_RATE);
        btnSettings.setScale(LibTest.SCALE_RATE);

        btnSettings.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log("Btn", "Settings");
            }
        });

        stage.addActor(btnSettings);

        btnUpgrade = MaterialCreator.createButton(assetManager.get("pic/btn/upgrade.png", Texture.class));

        btnUpgrade.setTransform(true);
        btnUpgrade.setX(LibTest.WIDTH - (10 * LibTest.SCALE_RATE) - (btnUpgrade.getWidth() * LibTest.SCALE_RATE));
        btnUpgrade.setY(10 * LibTest.SCALE_RATE);
        btnUpgrade.setScale(LibTest.SCALE_RATE);

        btnUpgrade.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log("Btn", "Upgrade");
            }
        });

        stage.addActor(btnUpgrade);

    }

    private void initSettingsTable() {
        tableSettings = new Table();




        stage.addActor(tableSettings);
    }

    @Override
    public void show() {
    }

    protected void handleInput() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(background, 0, background_y, background.getWidth() * LibTest.SCALE_RATE, background.getHeight() * LibTest.SCALE_RATE);
        spriteBatch.end();

        stage.draw();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

    }
}
