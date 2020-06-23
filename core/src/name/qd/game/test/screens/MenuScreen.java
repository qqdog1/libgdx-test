package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.List;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;
import name.qd.game.test.constant.Constants;
import name.qd.game.test.constant.Level;
import name.qd.game.test.constant.ScreenType;
import name.qd.game.test.utils.MaterialCreator;
import name.qd.game.test.utils.PreferencesUtils;

public class MenuScreen extends GameScreen {
    private Stage stage;
    private AssetManager assetManager;
    private Texture background;
    private Texture settingsBackground;
    private Texture selected;
    private Texture unselected;
    private TextureRegionDrawable star;
    private TextureRegionDrawable starFilled;
    private List<TextButton> lstBtnStageSelect;
    private TextButton btnUpgrade;
    private TextButton btnSettings;
    private TextButton btnMusic;
    private Label lblMusic;
    private TextButton btnSound;
    private Label lblSound;
    private BitmapFont font;

    private Table tableLevelSelect;
    private Table tableSettings;
    private boolean isSettingsShow;
    private Table tableStageInfo;
    private boolean isStageInfoShow;
    private Label lblLevel;
    private Label lblName;
    private Image star1;
    private Image star2;
    private Image star3;

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
        star = new TextureRegionDrawable(assetManager.get("pic/star.png", Texture.class));
        starFilled = new TextureRegionDrawable(assetManager.get("pic/starfilled.png", Texture.class));
        backgroundScaleHeight = (int) (background.getHeight() * LibTest.SCALE_RATE);
        backgroundScaleWidth = (int) (background.getWidth() * LibTest.SCALE_RATE);

        font = new BitmapFont();
        font.getData().setScale(4 * LibTest.SCALE_RATE);

        initButtons();
        initLevelSelectTable();
        initSettingsButton();
        initStageInfoTable();
        initSettingsTable();
        initUpgradeButton();

        stage.addListener(new ClickListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                if (lastY != 0) {
                    int diff = (int) y - lastY;
                    background_y += diff;
                    if (background_y > 0) {
                        background_y = 0;
                    }
                    if (background_y < -backgroundScaleHeight + LibTest.HEIGHT) {
                        background_y = -backgroundScaleHeight + LibTest.HEIGHT;
                    }

                    if(tableLevelSelect.getY() + diff > 0) {
                        tableLevelSelect.setY(0);
                    } else if(tableLevelSelect.getY() + diff < -backgroundScaleHeight + LibTest.HEIGHT) {
                        tableLevelSelect.setY(-backgroundScaleHeight + LibTest.HEIGHT);
                    }else {
                        tableLevelSelect.moveBy(0, diff);
                    }
                }
                lastY = (int) y;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                lastY = 0;
            }
        });
    }

    private void initButtons() {
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
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Btn", "Settings");
                if (isSettingsShow) {
                    isSettingsShow = false;
                } else {
                    isSettingsShow = true;
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
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Btn", "Upgrade");
                toNextScreen(ScreenType.UPGRADE);
            }
        });

        stage.addActor(btnUpgrade);
    }

    private void initMusic() {
        btnMusic = MaterialCreator.createButton(unselected, selected);
        Boolean isMusicOn = PreferencesUtils.getBooleanValue(PreferencesUtils.PreferencesEnum.MUSIC);
        btnMusic.setChecked(isMusicOn);

        btnMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Boolean isMusicOn = PreferencesUtils.getBooleanValue(PreferencesUtils.PreferencesEnum.MUSIC);
                if (isMusicOn) {
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
            public void clicked(InputEvent event, float x, float y) {
                Boolean isSoundOn = PreferencesUtils.getBooleanValue(PreferencesUtils.PreferencesEnum.SOUND);
                if (isSoundOn) {
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
        tableSettings.setTouchable(Touchable.enabled);
        tableSettings.top();
        tableSettings.left();

        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(settingsBackground);
        tableSettings.setX(LibTest.WIDTH);
        tableSettings.setY(btnSettings.getHeight() * LibTest.SCALE_RATE);
        tableSettings.setSize(settingsBackground.getWidth() * LibTest.SCALE_RATE, settingsBackground.getHeight() * LibTest.SCALE_RATE);
        tableSettings.setBackground(textureRegionDrawable);

        TextButton btnCloseSettings = MaterialCreator.createButton(assetManager.get("pic/btn/cancel.png", Texture.class));
        btnCloseSettings.setTransform(true);

        btnCloseSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Btn", "Close settings");
                isSettingsShow = false;
            }
        });

        Table closeBtnTable = new Table();

        closeBtnTable.add(btnCloseSettings)
                .width(btnCloseSettings.getWidth() * LibTest.SCALE_RATE)
                .height(btnCloseSettings.getHeight() * LibTest.SCALE_RATE)
                .padTop(btnCloseSettings.getWidth() * LibTest.SCALE_RATE)
                .padLeft(tableSettings.getWidth() - 2 * (btnCloseSettings.getWidth() * LibTest.SCALE_RATE));

        tableSettings.add(closeBtnTable);
        tableSettings.row();

        Table selectBtnTable = new Table();
        selectBtnTable.top().left();

        selectBtnTable.add(lblMusic).padTop(90 * LibTest.SCALE_RATE);

        selectBtnTable.add(btnMusic)
                .width(btnMusic.getWidth() * LibTest.SCALE_RATE)
                .height(btnMusic.getHeight() * LibTest.SCALE_RATE)
                .padLeft(30 * LibTest.SCALE_RATE)
                .padTop(80 * LibTest.SCALE_RATE);

        selectBtnTable.row().top().left();

        selectBtnTable.add(lblSound).padTop(50 * LibTest.SCALE_RATE);

        selectBtnTable.add(btnSound)
                .width(btnSound.getWidth() * LibTest.SCALE_RATE)
                .height(btnSound.getHeight() * LibTest.SCALE_RATE)
                .padLeft(30 * LibTest.SCALE_RATE)
                .padTop(40 * LibTest.SCALE_RATE);

        tableSettings.add(selectBtnTable);
        stage.addActor(tableSettings);
    }

    private void initLevelSelectTable() {
        int level = PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.LEVEL);

        lstBtnStageSelect = new ArrayList<>();
        Texture btnEnable = assetManager.get("pic/btn/stageselect.png", Texture.class);
        Texture btnDisable = assetManager.get("pic/btn/stagedisable.png", Texture.class);
        for (int i = 0; i < Constants.TOTAL_STAGES ; i++) {
            TextButton btnStageSelect = MaterialCreator.createButton(btnDisable, btnEnable);
            btnStageSelect.setName(String.valueOf(Level.getLevel(i).getLevel()));
            btnStageSelect.setText(Level.getLevel(i).getDisplayName());

            if(i >= level) {
                btnStageSelect.setTouchable(Touchable.disabled);
            } else {
                btnStageSelect.setChecked(true);
            }

            btnStageSelect.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    TextButton btn = (TextButton) event.getListenerActor();
                    btn.setChecked(true);
                    openStageInfoBoard(btn.getName());
                }
            });

            lstBtnStageSelect.add(btnStageSelect);
        }

        tableLevelSelect = new Table();
        tableLevelSelect.top().left();
        tableLevelSelect.setWidth(backgroundScaleWidth);
        tableLevelSelect.setHeight(backgroundScaleHeight);

        tableLevelSelect.add(lstBtnStageSelect.get(9))
                .width(lstBtnStageSelect.get(9).getWidth() * LibTest.SCALE_RATE)
                .height(lstBtnStageSelect.get(9).getHeight() * LibTest.SCALE_RATE)
                .padLeft(340 * LibTest.SCALE_RATE)
                .padTop(170 * LibTest.SCALE_RATE);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(7))
                .width(lstBtnStageSelect.get(7).getWidth() * LibTest.SCALE_RATE)
                .height(lstBtnStageSelect.get(7).getHeight() * LibTest.SCALE_RATE)
                .padLeft(138 * LibTest.SCALE_RATE)
                .padTop(280 * LibTest.SCALE_RATE);
        tableLevelSelect.add(lstBtnStageSelect.get(8))
                .width(lstBtnStageSelect.get(8).getWidth() * LibTest.SCALE_RATE)
                .height(lstBtnStageSelect.get(8).getHeight() * LibTest.SCALE_RATE)
                .padLeft(65 * LibTest.SCALE_RATE)
                .padTop(176 * LibTest.SCALE_RATE);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(5))
                .width(lstBtnStageSelect.get(5).getWidth() * LibTest.SCALE_RATE)
                .height(lstBtnStageSelect.get(5).getHeight() * LibTest.SCALE_RATE)
                .padLeft(280 * LibTest.SCALE_RATE)
                .padTop(240 * LibTest.SCALE_RATE);
        tableLevelSelect.add(lstBtnStageSelect.get(6))
                .width(lstBtnStageSelect.get(6).getWidth() * LibTest.SCALE_RATE)
                .height(lstBtnStageSelect.get(6).getHeight() * LibTest.SCALE_RATE)
                .padLeft(168 * LibTest.SCALE_RATE)
                .padTop(180 * LibTest.SCALE_RATE);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(4))
                .width(lstBtnStageSelect.get(4).getWidth() * LibTest.SCALE_RATE)
                .height(lstBtnStageSelect.get(4).getHeight() * LibTest.SCALE_RATE)
                .padLeft(110 * LibTest.SCALE_RATE)
                .padTop(180 * LibTest.SCALE_RATE);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(3)).colspan(2)
                .width(lstBtnStageSelect.get(3).getWidth() * LibTest.SCALE_RATE)
                .height(lstBtnStageSelect.get(3).getHeight() * LibTest.SCALE_RATE)
                .padLeft(550 * LibTest.SCALE_RATE)
                .padTop(190 * LibTest.SCALE_RATE);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(2))
                .width(lstBtnStageSelect.get(2).getWidth() * LibTest.SCALE_RATE)
                .height(lstBtnStageSelect.get(2).getHeight() * LibTest.SCALE_RATE)
                .padLeft(215 * LibTest.SCALE_RATE)
                .padTop(30 * LibTest.SCALE_RATE);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(1)).colspan(2)
                .width(lstBtnStageSelect.get(1).getWidth() * LibTest.SCALE_RATE)
                .height(lstBtnStageSelect.get(1).getHeight() * LibTest.SCALE_RATE)
                .padLeft(460 * LibTest.SCALE_RATE)
                .padTop(180 * LibTest.SCALE_RATE);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(0))
                .width(lstBtnStageSelect.get(0).getWidth() * LibTest.SCALE_RATE)
                .height(lstBtnStageSelect.get(0).getHeight() * LibTest.SCALE_RATE)
                .padLeft(142 * LibTest.SCALE_RATE)
                .padTop(70 * LibTest.SCALE_RATE);

        stage.addActor(tableLevelSelect);
    }

    private void initStageInfoTable() {
        tableStageInfo = new Table();
        tableStageInfo.setTouchable(Touchable.enabled);
        tableStageInfo.top();

        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(settingsBackground);
        tableStageInfo.setX(LibTest.WIDTH);
        tableStageInfo.setY(btnSettings.getHeight() * LibTest.SCALE_RATE);
        tableStageInfo.setSize(settingsBackground.getWidth() * LibTest.SCALE_RATE, settingsBackground.getHeight() * LibTest.SCALE_RATE);
        tableStageInfo.setBackground(textureRegionDrawable);

        Label lblStage = new Label("Stage", new Label.LabelStyle(font, Color.RED));
        lblLevel = new Label("0", new Label.LabelStyle(font, Color.RED));
        lblName = new Label("The XXX XXX", new Label.LabelStyle(font, Color.RED));

        TextButton btnClose = MaterialCreator.createButton(assetManager.get("pic/btn/cancel.png", Texture.class));
        btnClose.setTransform(true);

        btnClose.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isStageInfoShow = false;
            }
        });

        Table closeBtnTable = new Table();

        closeBtnTable.add(btnClose)
                .width(btnClose.getWidth() * LibTest.SCALE_RATE)
                .height(btnClose.getHeight() * LibTest.SCALE_RATE)
                .padTop(btnClose.getWidth() * LibTest.SCALE_RATE)
                .padLeft(tableStageInfo.getWidth() - 2 * (btnClose.getWidth() * LibTest.SCALE_RATE));

        tableStageInfo.add(closeBtnTable).colspan(2);
        tableStageInfo.row().left();

        tableStageInfo.add(lblStage)
                .width(lblStage.getWidth() * LibTest.SCALE_RATE)
                .padLeft(200 * LibTest.SCALE_RATE);
        tableStageInfo.add(lblLevel)
                .padRight(120 * LibTest.SCALE_RATE);

        tableStageInfo.row();

        tableStageInfo.add(lblName).colspan(2);

        tableStageInfo.row();

        star1 = new Image(star);
        star2 = new Image(star);
        star3 = new Image(star);

        Table starsTable = new Table();

        starsTable.add(star1)
                .width(star1.getWidth() * LibTest.SCALE_RATE)
                .height(star1.getHeight() * LibTest.SCALE_RATE);
        starsTable.add(star2)
                .width(star2.getWidth() * LibTest.SCALE_RATE)
                .height(star2.getHeight() * LibTest.SCALE_RATE);
        starsTable.add(star3)
                .width(star3.getWidth() * LibTest.SCALE_RATE)
                .height(star3.getHeight() * LibTest.SCALE_RATE);

        tableStageInfo.add(starsTable).colspan(2);

        tableStageInfo.row();

        TextButton btnGo = MaterialCreator.createButton(assetManager.get("pic/btn/go.png", Texture.class));

        btnGo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Btn", "Go");
            }
         });

        tableStageInfo.add(btnGo).colspan(2)
                .width(btnGo.getWidth() * LibTest.SCALE_RATE)
                .height(btnGo.getHeight() * LibTest.SCALE_RATE);

        stage.addActor(tableStageInfo);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    protected void handleInput() {
    }

    @Override
    protected ScreenType currentScreen() {
        return ScreenType.MENU;
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
        updateStageInfoTableAction();
        tableStageInfo.act(delta);
    }

    private void updateSettingsTableAction() {
        if (isSettingsShow) {
            tableSettings.addAction(Actions.moveTo(0, tableSettings.getY(), 0.6f));
        } else {
            tableSettings.addAction(Actions.moveTo(LibTest.WIDTH, tableSettings.getY(), 0.6f));
        }
    }

    private void updateStageInfoTableAction() {
        if(isStageInfoShow) {
            tableStageInfo.addAction(Actions.moveTo(0, tableSettings.getY(), 0.6f));
        } else {
            tableStageInfo.addAction(Actions.moveTo(LibTest.WIDTH, tableSettings.getY(), 0.6f));
        }
    }

    private void openStageInfoBoard(String stageName) {
        Gdx.app.log("stage", stageName);
        isStageInfoShow = true;

        lblLevel.setText(stageName);
        int index = Integer.parseInt(stageName) - 1;
        lblName.setText(Level.getLevel(index).getDisplayName());

        int stars = getStarsByStage(stageName);

        star1.setDrawable(star);
        star2.setDrawable(star);
        star3.setDrawable(star);

        if(stars >= 4) {
            stars -= 4;
            star3.setDrawable(starFilled);
        }
        if(stars >= 2) {
            stars -= 2;
            star2.setDrawable(starFilled);
        }
        if(stars == 1) {
            star1.setDrawable(starFilled);
        }
    }

    private int getStarsByStage(String stageName) {
        int stage = Integer.parseInt(stageName);

        switch (stage) {
            case 1:
                return PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.L1);
            case 2:
                return PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.L2);
            case 3:
                return PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.L3);
            case 4:
                return PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.L4);
            case 5:
                return PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.L5);
            case 6:
                return PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.L6);
            case 7:
                return PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.L7);
            case 8:
                return PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.L8);
            case 9:
                return PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.L9);
            case 10:
                return PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.L10);
        }
        return 0;
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
