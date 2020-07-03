package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.List;

import name.qd.game.test.utils.ResourceInstance;
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
    private ImageButton btnUpgrade;
    private ImageButton btnSettings;
    private ImageButton btnMusic;
    private Label lblMusic;
    private ImageButton btnSound;
    private Label lblSound;

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

    private Label.LabelStyle labelStyleRed;
    private Label.LabelStyle labelStyleWhite;

    private float background_y = 0;
    private float lastY;
    private float backgroundScaleHeight;
    private float backgroundScaleWidth;

    MenuScreen() {
        stage = new Stage(viewport, spriteBatch);
        assetManager = ResourceInstance.getInstance().getAssetManager();
        background = assetManager.get("pic/stage.png", Texture.class);
        settingsBackground = assetManager.get("pic/board.png", Texture.class);
        selected = assetManager.get("pic/btn/selected.png", Texture.class);
        unselected = assetManager.get("pic/btn/unselected.png", Texture.class);
        star = new TextureRegionDrawable(assetManager.get("pic/star.png", Texture.class));
        starFilled = new TextureRegionDrawable(assetManager.get("pic/starfilled.png", Texture.class));
        backgroundScaleHeight = background.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER;
        backgroundScaleWidth = background.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER;

        labelStyleRed = MaterialCreator.getDefaultLabelStyle(Color.RED, 2f * SCALE_RATE / Constants.PIXEL_PER_METER);
        labelStyleWhite = MaterialCreator.getDefaultLabelStyle(Color.WHITE, 1f * SCALE_RATE / Constants.PIXEL_PER_METER);

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
                    float diff = y - lastY;
                    background_y += diff;
                    if (background_y > 0) {
                        background_y = 0;
                    }
                    if (background_y < -backgroundScaleHeight + SCREEN_HEIGHT) {
                        background_y = -backgroundScaleHeight + SCREEN_HEIGHT;
                    }

                    if(tableLevelSelect.getY() + diff > 0) {
                        tableLevelSelect.setY(0);
                    } else if(tableLevelSelect.getY() + diff < -backgroundScaleHeight + SCREEN_HEIGHT) {
                        tableLevelSelect.setY(-backgroundScaleHeight + SCREEN_HEIGHT);
                    }else {
                        tableLevelSelect.moveBy(0, diff);
                    }
                }
                lastY = y;
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
        btnSettings = MaterialCreator.createImageButton(assetManager.get("pic/btn/settings.png", Texture.class));

        btnSettings.setTransform(true);
        btnSettings.setX(0);
        btnSettings.setY(0);
        btnSettings.setScale(SCALE_RATE / Constants.PIXEL_PER_METER);

        btnSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Btn", "Settings");
                isSettingsShow = !isSettingsShow;
            }
        });

        stage.addActor(btnSettings);
    }

    private void initUpgradeButton() {
        btnUpgrade = MaterialCreator.createImageButton(assetManager.get("pic/btn/upgrade.png", Texture.class));

        btnUpgrade.setTransform(true);
        btnUpgrade.setX(SCREEN_WIDTH - (btnUpgrade.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER));
        btnUpgrade.setY(0);
        btnUpgrade.setScale(SCALE_RATE / Constants.PIXEL_PER_METER);

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
        btnMusic = MaterialCreator.createImageButton(unselected, selected);
        boolean isMusicOn = PreferencesUtils.getBooleanValue(PreferencesUtils.PreferencesEnum.MUSIC);
        btnMusic.setChecked(isMusicOn);

        btnMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean isMusicOn = !PreferencesUtils.getBooleanValue(PreferencesUtils.PreferencesEnum.MUSIC);
                btnMusic.setChecked(isMusicOn);
                Gdx.app.log("Music button", String.valueOf(isMusicOn));
                PreferencesUtils.set(PreferencesUtils.PreferencesEnum.MUSIC, isMusicOn);
            }
        });

        lblMusic = new Label("MUSIC", labelStyleRed);
    }

    private void initSound() {
        btnSound = MaterialCreator.createImageButton(unselected, selected);
        boolean isSoundOn = PreferencesUtils.getBooleanValue(PreferencesUtils.PreferencesEnum.SOUND);
        btnSound.setChecked(isSoundOn);

        btnSound.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean isSoundOn = !PreferencesUtils.getBooleanValue(PreferencesUtils.PreferencesEnum.SOUND);
                btnSound.setChecked(isSoundOn);
                Gdx.app.log("Sound button", String.valueOf(isSoundOn));
                PreferencesUtils.set(PreferencesUtils.PreferencesEnum.SOUND, isSoundOn);
            }
        });

        lblSound = new Label("SOUND", labelStyleRed);
    }

    private void initSettingsTable() {
        tableSettings = new Table();
        tableSettings.setTouchable(Touchable.enabled);
        tableSettings.top();

        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(settingsBackground);
        tableSettings.setX(SCREEN_WIDTH);
        tableSettings.setY(btnSettings.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);
        tableSettings.setSize(settingsBackground.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER, settingsBackground.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);
        tableSettings.setBackground(textureRegionDrawable);

        ImageButton btnCloseSettings = MaterialCreator.createImageButton(assetManager.get("pic/btn/cancel.png", Texture.class));

        btnCloseSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Btn", "Close settings");
                isSettingsShow = false;
            }
        });

        float btnScaleWidth = btnCloseSettings.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER;
        float btnScaleHeight = btnCloseSettings.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER;

        Table closeBtnTable = new Table();
        closeBtnTable.add(btnCloseSettings).width(btnScaleWidth).height(btnScaleHeight)
                .padTop(btnScaleHeight)
                .padLeft(tableSettings.getWidth() - 2 * btnScaleWidth);

        tableSettings.add(closeBtnTable);
        tableSettings.row();

        Table selectBtnTable = new Table();
        selectBtnTable.top().left();

        selectBtnTable.add(lblMusic);

        selectBtnTable.add(btnMusic)
                .width(btnMusic.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(btnMusic.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);

        selectBtnTable.row().top().left();

        selectBtnTable.add(lblSound);

        selectBtnTable.add(btnSound)
                .width(btnSound.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(btnSound.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);

        tableSettings.add(selectBtnTable);
        stage.addActor(tableSettings);
    }

    private void initLevelSelectTable() {
        int userLastLevel = PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.LEVEL);

        lstBtnStageSelect = new ArrayList<>();
        Texture btnEnable = assetManager.get("pic/btn/stageselect.png", Texture.class);
        Texture btnDisable = assetManager.get("pic/btn/stagedisable.png", Texture.class);
        for (int i = 0; i < Constants.TOTAL_STAGES ; i++) {
            Level level = Level.getLevel(i);
            TextButton btnStageSelect = MaterialCreator.createTextButton(btnDisable, btnEnable, level.getDisplayName());
            btnStageSelect.setName(String.valueOf(level.getLevel()));
            btnStageSelect.setUserObject(level);
            btnStageSelect.getLabel().setStyle(labelStyleWhite);

            if(i >= userLastLevel) {
                btnStageSelect.setTouchable(Touchable.disabled);
            } else {
                btnStageSelect.setChecked(true);
            }

            btnStageSelect.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    TextButton btn = (TextButton) event.getListenerActor();
                    btn.setChecked(true);
                    openStageInfoBoard((Level)btn.getUserObject());
                }
            });

            lstBtnStageSelect.add(btnStageSelect);
        }

        tableLevelSelect = new Table();
        tableLevelSelect.top().left();
        tableLevelSelect.setWidth(backgroundScaleWidth);
        tableLevelSelect.setHeight(backgroundScaleHeight);

        tableLevelSelect.add(lstBtnStageSelect.get(9))
                .width(lstBtnStageSelect.get(9).getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(lstBtnStageSelect.get(9).getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(170 * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padTop(86 * SCALE_RATE / Constants.PIXEL_PER_METER);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(7))
                .width(lstBtnStageSelect.get(7).getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(lstBtnStageSelect.get(7).getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(70 * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padTop(140 * SCALE_RATE / Constants.PIXEL_PER_METER);
        tableLevelSelect.add(lstBtnStageSelect.get(8))
                .width(lstBtnStageSelect.get(8).getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(lstBtnStageSelect.get(8).getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(40 * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padTop(85 * SCALE_RATE / Constants.PIXEL_PER_METER);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(5))
                .width(lstBtnStageSelect.get(5).getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(lstBtnStageSelect.get(5).getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(140 * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padTop(120 * SCALE_RATE / Constants.PIXEL_PER_METER);
        tableLevelSelect.add(lstBtnStageSelect.get(6))
                .width(lstBtnStageSelect.get(6).getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(lstBtnStageSelect.get(6).getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(80 * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padTop(80 * SCALE_RATE / Constants.PIXEL_PER_METER);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(4))
                .width(lstBtnStageSelect.get(4).getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(lstBtnStageSelect.get(4).getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(60 * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padTop(90 * SCALE_RATE / Constants.PIXEL_PER_METER);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(3)).colspan(2)
                .width(lstBtnStageSelect.get(3).getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(lstBtnStageSelect.get(3).getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(280 * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padTop(90 * SCALE_RATE / Constants.PIXEL_PER_METER);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(2))
                .width(lstBtnStageSelect.get(2).getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(lstBtnStageSelect.get(2).getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(110 * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padTop(20 * SCALE_RATE / Constants.PIXEL_PER_METER);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(1)).colspan(2)
                .width(lstBtnStageSelect.get(1).getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(lstBtnStageSelect.get(1).getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(230 * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padTop(90 * SCALE_RATE / Constants.PIXEL_PER_METER);

        tableLevelSelect.row().left();

        tableLevelSelect.add(lstBtnStageSelect.get(0))
                .width(lstBtnStageSelect.get(0).getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(lstBtnStageSelect.get(0).getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(71 * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padTop(32 * SCALE_RATE / Constants.PIXEL_PER_METER);

        stage.addActor(tableLevelSelect);
    }

    private void initStageInfoTable() {
        tableStageInfo = new Table();
        tableStageInfo.setTouchable(Touchable.enabled);
        tableStageInfo.top();

        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(settingsBackground);
        tableStageInfo.setX(SCREEN_WIDTH);
        tableStageInfo.setY(btnSettings.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);
        tableStageInfo.setSize(settingsBackground.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER, settingsBackground.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);
        tableStageInfo.setBackground(textureRegionDrawable);

        Label lblStage = new Label("Stage", labelStyleRed);
        lblLevel = new Label("0", labelStyleRed);
        lblName = new Label("The XXX XXX", labelStyleRed);
        ImageButton btnClose = MaterialCreator.createImageButton(assetManager.get("pic/btn/cancel.png", Texture.class));

        btnClose.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isStageInfoShow = false;
            }
        });

        Table closeBtnTable = new Table();

        float scaleWidth = btnClose.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER;
        float scaleHeight = btnClose.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER;

        closeBtnTable.add(btnClose)
                .width(scaleWidth)
                .height(scaleHeight)
                .padTop(scaleWidth)
                .padLeft(tableStageInfo.getWidth() - 2 * scaleHeight);

        tableStageInfo.add(closeBtnTable).colspan(2);
        tableStageInfo.row().left();

        tableStageInfo.add(lblStage)
                .width(lblStage.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(100 * SCALE_RATE / Constants.PIXEL_PER_METER);
        tableStageInfo.add(lblLevel)
                .padRight(60 * SCALE_RATE / Constants.PIXEL_PER_METER);

        tableStageInfo.row();

        tableStageInfo.add(lblName).colspan(2);

        tableStageInfo.row();

        star1 = new Image(star);
        star2 = new Image(star);
        star3 = new Image(star);

        Table starsTable = new Table();

        starsTable.add(star1)
                .width(star1.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(star1.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);
        starsTable.add(star2)
                .width(star2.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(star2.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);
        starsTable.add(star3)
                .width(star3.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(star3.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);

        tableStageInfo.add(starsTable).colspan(2);

        tableStageInfo.row();

        ImageButton btnGo = MaterialCreator.createImageButton(assetManager.get("pic/btn/go.png", Texture.class));

        btnGo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("btn", "GO");
                toStageScreen((Level)lblLevel.getUserObject());
            }
         });

        tableStageInfo.add(btnGo).colspan(2)
                .width(btnGo.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(btnGo.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);

        stage.addActor(tableStageInfo);
    }

    private void toStageScreen(Level level) {
        switch (level.getLevel()) {
            case 1:
                toNextScreen(ScreenType.L1);
                break;
            case 2:
                toNextScreen(ScreenType.L2);
                break;
            case 3:
                toNextScreen(ScreenType.L3);
                break;
            case 4:
                toNextScreen(ScreenType.L4);
                break;
            case 5:
                toNextScreen(ScreenType.L5);
                break;
            case 6:
                toNextScreen(ScreenType.L6);
                break;
            case 7:
                toNextScreen(ScreenType.L7);
                break;
            case 8:
                toNextScreen(ScreenType.L8);
                break;
            case 9:
                toNextScreen(ScreenType.L9);
                break;
            case 10:
                toNextScreen(ScreenType.L10);
                break;
        }
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
            tableSettings.addAction(Actions.moveTo(0, tableSettings.getY(), 0.2f, Interpolation.linear));
        } else {
            tableSettings.addAction(Actions.moveTo(SCREEN_WIDTH, tableSettings.getY(), 0.2f, Interpolation.linear));
        }
    }

    private void updateStageInfoTableAction() {
        if(isStageInfoShow) {
            tableStageInfo.addAction(Actions.moveTo(0, tableSettings.getY(), 0.3f));
        } else {
            tableStageInfo.addAction(Actions.moveTo(SCREEN_WIDTH, tableSettings.getY(), 0.3f));
        }
    }

    private void openStageInfoBoard(Level level) {
        Gdx.app.log("stage", level.getDisplayName());
        isStageInfoShow = true;

        lblLevel.setText(level.getLevel());
        lblLevel.setUserObject(level);
        lblName.setText(level.getDisplayName());

        int stars = getStarsByStage(level.getLevel());

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

    private int getStarsByStage(int stage) {
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
