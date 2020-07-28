package name.qd.game.test.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import name.qd.game.test.constant.Constants;
import name.qd.game.test.constant.PreferencesEnum;
import name.qd.game.test.constant.ScreenType;
import name.qd.game.test.utils.MaterialCreator;
import name.qd.game.test.utils.PreferencesUtils;

public class UpgradeScreen extends GameScreen {
    private ImageButton btnReturn;
    private Stage stage;

    private Texture upgradeBar;
    private Texture upgradePoint;
    private ImageButton btnWeaponPlus;
    private ImageButton btnWeaponMinus;
    private ImageButton btnHpPlus;
    private ImageButton btnHpMinus;

    private int currentWeaponLevel;
    private int currentHpLevel;

    private float scaleBarWidth;
    private float scaleBarHeight;
    private float scalePointWidth;
    private float scalePointHeight;

    public UpgradeScreen() {
        stage = new Stage(viewport, spriteBatch);

        if(PreferencesUtils.isLabelExist(PreferencesEnum.WEAPON)) {
            currentWeaponLevel = PreferencesUtils.getIntValue(PreferencesEnum.WEAPON);
        } else {
            currentWeaponLevel = 1;
        }
        if(PreferencesUtils.isLabelExist(PreferencesEnum.HP)) {
            currentHpLevel = PreferencesUtils.getIntValue(PreferencesEnum.HP);
        } else {
            currentHpLevel = 1;
        }

        upgradeBar = assetManager.get("pic/upgradebar.png", Texture.class);
        upgradePoint = assetManager.get("pic/upgradepoint.png", Texture.class);

        scaleBarWidth = upgradeBar.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER;
        scaleBarHeight = upgradeBar.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER;
        scalePointWidth = upgradePoint.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER;
        scalePointHeight = upgradePoint.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER;

        btnWeaponPlus = MaterialCreator.createImageButton(assetManager.get("pic/btn/plus.png", Texture.class));
        btnWeaponPlus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentWeaponLevel++;
                if(currentWeaponLevel > Constants.MAX_UPGRADE_LEVEL) {
                    currentWeaponLevel = Constants.MAX_UPGRADE_LEVEL;
                }
                PreferencesUtils.set(PreferencesEnum.WEAPON, currentWeaponLevel);
            }
        });

        btnHpPlus = MaterialCreator.createImageButton(assetManager.get("pic/btn/plus.png", Texture.class));
        btnHpPlus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentHpLevel++;
                if(currentHpLevel > Constants.MAX_UPGRADE_LEVEL) {
                    currentHpLevel = Constants.MAX_UPGRADE_LEVEL;
                }
                PreferencesUtils.set(PreferencesEnum.HP, currentHpLevel);
            }
        });

        btnWeaponMinus = MaterialCreator.createImageButton(assetManager.get("pic/btn/minus.png", Texture.class));
        btnWeaponMinus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentWeaponLevel--;
                if(currentWeaponLevel < 1) {
                    currentWeaponLevel = 1;
                }
                PreferencesUtils.set(PreferencesEnum.WEAPON, currentWeaponLevel);
            }
        });

        btnHpMinus = MaterialCreator.createImageButton(assetManager.get("pic/btn/minus.png", Texture.class));
        btnHpMinus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentHpLevel--;
                if(currentHpLevel < 1) {
                    currentHpLevel = 1;
                }
                PreferencesUtils.set(PreferencesEnum.HP, currentHpLevel);
            }
        });

        btnReturn = MaterialCreator.createImageButton(assetManager.get("pic/btn/return.png", Texture.class));
        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("btn", "return");
                toNextScreen(ScreenType.MENU);
            }
        });

        Table tableReturn = new Table();
        tableReturn.setPosition(SCREEN_WIDTH - (btnReturn.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER / 2), SCREEN_HEIGHT - (btnReturn.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER / 2));
        tableReturn.add(btnReturn)
                .width(btnReturn.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(btnReturn.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);

        Table table = new Table();
        table.setPosition(0, 2 * SCREEN_HEIGHT / 3 + btnWeaponMinus.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);
        table.top().left();
        table.add(btnWeaponMinus)
                .width(btnWeaponMinus.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(btnWeaponMinus.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(10f * SCALE_RATE / Constants.PIXEL_PER_METER);
        table.add(btnWeaponPlus)
                .width(btnWeaponPlus.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(btnWeaponPlus.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(10f * SCALE_RATE / Constants.PIXEL_PER_METER);

        Table table2 = new Table();
        table2.setPosition(0, SCREEN_HEIGHT / 2 + btnWeaponMinus.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);
        table2.top().left();
        table2.add(btnHpMinus)
                .width(btnHpMinus.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(btnHpMinus.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(10f * SCALE_RATE / Constants.PIXEL_PER_METER);
        table2.add(btnHpPlus)
                .width(btnHpPlus.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(btnHpPlus.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)
                .padLeft(10f * SCALE_RATE / Constants.PIXEL_PER_METER);

//        stage.setDebugAll(true);
        stage.addActor(tableReturn);
        stage.addActor(table);
        stage.addActor(table2);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {
    }

    @Override
    protected ScreenType currentScreen() {
        return ScreenType.UPGRADE;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(upgradeBar,
                SCREEN_WIDTH - scaleBarWidth, 2 * SCREEN_HEIGHT / 3, // x, y
                0, 0, // originX, originY
                scaleBarWidth, scaleBarHeight, // width, height
                1, 1, // scaleX, scaleY
                0, // rotation
                0, 0, // srcX, srcY
                upgradeBar.getWidth(), upgradeBar.getHeight(), // srcWidth, scrHeight
                false, false // flipX, flipY
        );
        spriteBatch.draw(upgradeBar,
                SCREEN_WIDTH - scaleBarWidth, SCREEN_HEIGHT / 2, // x, y
                0, 0, // originX, originY
                scaleBarWidth, scaleBarHeight, // width, height
                1, 1, // scaleX, scaleY
                0, // rotation
                0, 0, // srcX, srcY
                upgradeBar.getWidth(), upgradeBar.getHeight(), // srcWidth, scrHeight
                false, false // flipX, flipY
        );
        for(int i = 0; i < currentWeaponLevel; i++) {
            spriteBatch.draw(upgradePoint,
                    SCREEN_WIDTH - scaleBarWidth + scalePointWidth * (i+1), 2 * SCREEN_HEIGHT / 3, // x, y
                    0, 0, // originX, originY
                    scalePointWidth, scalePointHeight, // width, height
                    1, 1, // scaleX, scaleY
                    0, // rotation
                    0, 0, // srcX, srcY
                    upgradePoint.getWidth(), upgradePoint.getHeight(), // srcWidth, scrHeight
                    false, false // flipX, flipY
            );
        }
        for(int i = 0; i < currentHpLevel; i++) {
            spriteBatch.draw(upgradePoint,
                    SCREEN_WIDTH - scaleBarWidth + scalePointWidth * (i+1), SCREEN_HEIGHT / 2, // x, y
                    0, 0, // originX, originY
                    scalePointWidth, scalePointHeight, // width, height
                    1, 1, // scaleX, scaleY
                    0, // rotation
                    0, 0, // srcX, srcY
                    upgradePoint.getWidth(), upgradePoint.getHeight(), // srcWidth, scrHeight
                    false, false // flipX, flipY
            );
        }
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
