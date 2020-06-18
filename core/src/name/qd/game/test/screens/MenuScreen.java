package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;
import name.qd.game.test.utils.MaterialCreator;

public class MenuScreen extends GameScreen {
    private Stage stage;
    private AssetManager assetManager;
    private Texture background;
    private Texture settingsBackground;
    private Texture selected;
    private Texture unselected;
    private TextButton btnUpgrade;
    private TextButton btnSettings;
    private TextButton btnStageSelect;
    private TextButton btnCloseSettings;
    private TextButton btnMusic;
    private TextButton btnSound;

    private Table tableSettings;
    private boolean isShowSettings;

    private int background_y = 0;
    private int lastY;
    private int backgroundScaleHeight;

    public MenuScreen() {
        stage = new Stage(viewport, spriteBatch);
        assetManager = ResourceInstance.getInstance().getAssetManager();
        background = assetManager.get("pic/stage.png", Texture.class);
        settingsBackground = assetManager.get("pic/settingsbackground.png", Texture.class);
        selected = assetManager.get("pic/selected.png", Texture.class);
        unselected = assetManager.get("pic/unselected.png", Texture.class);
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
        initSettingsButton();
        initUpgradeButton();
        initCloseSettingsButton();
        initMusicButton();
        initSoundButton();
    }

    private void initSettingsButton() {
        btnSettings = MaterialCreator.createButton(assetManager.get("pic/btn/settings.png", Texture.class));

        btnSettings.setTransform(true);
        btnSettings.setX(10 * LibTest.SCALE_RATE);
        btnSettings.setY(10 * LibTest.SCALE_RATE);
        btnSettings.setScale(LibTest.SCALE_RATE);

        btnSettings.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log("Btn", "Settings");
                if(isShowSettings) {
                    isShowSettings = false;
                } else {
                    isShowSettings = true;
                }
            }
        });

        stage.addActor(btnSettings);
    }

    private void initUpgradeButton() {
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

    private void initCloseSettingsButton() {
        btnCloseSettings = MaterialCreator.createButton(assetManager.get("pic/btn/cancel.png", Texture.class));
        btnCloseSettings.setTransform(true);

        btnCloseSettings.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log("Btn", "Close settings");
                isShowSettings = false;
            }
        });
    }

    private void initMusicButton() {
        btnMusic = MaterialCreator.createButton(unselected, selected);
    }

    private void initSoundButton() {
        btnSound = MaterialCreator.createButton(unselected, selected);
    }

    private void initSettingsTable() {
        tableSettings = new Table();
        tableSettings.top();
        tableSettings.left();

        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(settingsBackground);
        tableSettings.setX(LibTest.WIDTH);
        tableSettings.setY(btnSettings.getHeight() * LibTest.SCALE_RATE);
        tableSettings.setSize(settingsBackground.getWidth() * LibTest.SCALE_RATE, settingsBackground.getHeight() * LibTest.SCALE_RATE);
        tableSettings.setBackground(textureRegionDrawable);

        tableSettings.add(btnCloseSettings)
                .width(btnCloseSettings.getWidth() * LibTest.SCALE_RATE)
                .height(btnCloseSettings.getHeight() * LibTest.SCALE_RATE)
                .padTop(btnCloseSettings.getWidth() * LibTest.SCALE_RATE)
                .padLeft((settingsBackground.getWidth() * LibTest.SCALE_RATE) - 2 * (btnCloseSettings.getWidth() * LibTest.SCALE_RATE));

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
        updateSettingsTableAction();
        tableSettings.act(delta);
    }

    private void updateSettingsTableAction() {
        if(isShowSettings) {
            tableSettings.addAction(Actions.moveTo(0, tableSettings.getY(), 0.6f));
        } else {
            tableSettings.addAction(Actions.moveTo(LibTest.WIDTH, tableSettings.getY(), 0.6f));
        }
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
