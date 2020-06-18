package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;
import name.qd.game.test.utils.MaterialCreator;
import name.qd.game.test.utils.PreferencesUtils;

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
    private Label lblMusic;
    private TextButton btnSound;
    private Label lblSound;
    private BitmapFont font;

    private Table tableSettings;
    private boolean isShowSettings;
    private Table tableLevelSelect;

    private int background_y = 0;
    private int lastY;
    private int backgroundScaleHeight;
    private int backgroundScaleWidth;

    public MenuScreen() {
        stage = new Stage(viewport, spriteBatch);
        assetManager = ResourceInstance.getInstance().getAssetManager();
        background = assetManager.get("pic/stage.png", Texture.class);
        settingsBackground = assetManager.get("pic/board.png", Texture.class);
        selected = assetManager.get("pic/btn/selected.png", Texture.class);
        unselected = assetManager.get("pic/btn/unselected.png", Texture.class);
        backgroundScaleHeight = (int)(background.getHeight() * LibTest.SCALE_RATE);
        backgroundScaleWidth = (int)(background.getWidth() * LibTest.SCALE_RATE);

        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        font.getData().setScale(4 * LibTest.SCALE_RATE);

        initButton();
        initSettingsTable();
        initLevelSelectTable();

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
        initMusic();
        initSound();
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

    private void initMusic() {
        btnMusic = MaterialCreator.createButton(unselected, selected);
        Boolean isMusicOn = PreferencesUtils.getBooleanValue(PreferencesUtils.PreferencesEnum.MUSIC);
        btnMusic.setChecked(isMusicOn);

        btnMusic.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Boolean isMusicOn = PreferencesUtils.getBooleanValue(PreferencesUtils.PreferencesEnum.MUSIC);
                if(isMusicOn) {
                    isMusicOn = false;
                } else {
                    isMusicOn = true;
                }
                btnMusic.setChecked(isMusicOn);
                Gdx.app.log("Music button", String.valueOf(isMusicOn));
                PreferencesUtils.set(PreferencesUtils.PreferencesEnum.MUSIC, isMusicOn);
            }
        });

        lblMusic = new Label("MUSIC", new Label.LabelStyle(font, Color.RED));
    }

    private void initSound() {
        btnSound = MaterialCreator.createButton(unselected, selected);
        Boolean isSoundOn = PreferencesUtils.getBooleanValue(PreferencesUtils.PreferencesEnum.SOUND);
        btnSound.setChecked(isSoundOn);

        btnSound.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Boolean isSoundOn = PreferencesUtils.getBooleanValue(PreferencesUtils.PreferencesEnum.SOUND);
                if(isSoundOn) {
                    isSoundOn = false;
                } else {
                    isSoundOn = true;
                }
                btnSound.setChecked(isSoundOn);
                Gdx.app.log("Sound button", String.valueOf(isSoundOn));
                PreferencesUtils.set(PreferencesUtils.PreferencesEnum.SOUND, isSoundOn);
            }
        });

        lblSound = new Label("SOUND", new Label.LabelStyle(font, Color.RED));
    }

    private void initSettingsTable() {
        tableSettings = new Table();
        tableSettings.top();
        tableSettings.left();
        tableSettings.setDebug(true);

        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(settingsBackground);
        tableSettings.setX(LibTest.WIDTH);
        tableSettings.setY(btnSettings.getHeight() * LibTest.SCALE_RATE);
        tableSettings.setSize(settingsBackground.getWidth() * LibTest.SCALE_RATE, settingsBackground.getHeight() * LibTest.SCALE_RATE);
        tableSettings.setBackground(textureRegionDrawable);

        Table closeBtnTable = new Table();

        closeBtnTable.add(btnCloseSettings)
                .width(btnCloseSettings.getWidth() * LibTest.SCALE_RATE)
                .height(btnCloseSettings.getHeight() * LibTest.SCALE_RATE)
                .padTop(btnCloseSettings.getWidth() * LibTest.SCALE_RATE)
                .padLeft(tableSettings.getWidth() - 2 * (btnCloseSettings.getWidth() * LibTest.SCALE_RATE));

        tableSettings.add(closeBtnTable);
        tableSettings.row();

        Table selectBtnTable = new Table();

        selectBtnTable.add(lblMusic).expandX()
                .padTop(100 * LibTest.SCALE_RATE);

        selectBtnTable.add(btnMusic)
                .width(btnMusic.getWidth() * LibTest.SCALE_RATE)
                .height(btnMusic.getHeight() * LibTest.SCALE_RATE)
                .padTop(80 * LibTest.SCALE_RATE);

        selectBtnTable.row().top().left();

        selectBtnTable.add(btnSound)
                .width(btnSound.getWidth() * LibTest.SCALE_RATE)
                .height(btnSound.getHeight() * LibTest.SCALE_RATE)
                .padTop(btnSound.getWidth() * LibTest.SCALE_RATE)
                .padLeft(settingsBackground.getWidth() * LibTest.SCALE_RATE / 2);

        tableSettings.add(selectBtnTable);
        stage.addActor(tableSettings);
    }

    private void initLevelSelectTable() {

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
        spriteBatch.draw(background, 0, background_y, backgroundScaleWidth, backgroundScaleHeight);
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
