package name.qd.game.test.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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

    private int currentWeapon;
    private int currentHp;

    public UpgradeScreen() {
        stage = new Stage(viewport, spriteBatch);

        if(PreferencesUtils.isLabelExist(PreferencesEnum.WEAPON)) {
            currentWeapon = PreferencesUtils.getIntValue(PreferencesEnum.WEAPON);
        } else {
            currentWeapon = Constants.DEFAULT_WEAPON;
        }
        if(PreferencesUtils.isLabelExist(PreferencesEnum.HP)) {
            currentHp = PreferencesUtils.getIntValue(PreferencesEnum.HP);
        } else {
            currentHp = Constants.DEFAULT_HP;
        }

        upgradeBar = assetManager.get("pic/upgradebar.png", Texture.class);
        upgradePoint = assetManager.get("pic/upgradepoint.png", Texture.class);

        btnWeaponPlus = MaterialCreator.createImageButton(assetManager.get("pic/btn/plus.png", Texture.class));
        btnWeaponPlus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        btnHpPlus = MaterialCreator.createImageButton(assetManager.get("pic/btn/plus.png", Texture.class));
        btnHpPlus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        btnWeaponMinus = MaterialCreator.createImageButton(assetManager.get("pic/btn/minus.png", Texture.class));
        btnWeaponMinus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        btnHpMinus = MaterialCreator.createImageButton(assetManager.get("pic/btn/minus.png", Texture.class));
        btnHpMinus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });


        btnReturn = MaterialCreator.createImageButton(assetManager.get("pic/btn/return.png", Texture.class));
        btnReturn.setTransform(true);
        btnReturn.setScale(SCALE_RATE / Constants.PIXEL_PER_METER);
        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("btn", "return");
                toNextScreen(ScreenType.MENU);
            }
        });

        btnReturn.setPosition(SCREEN_WIDTH - (btnReturn.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER), SCREEN_HEIGHT - (btnReturn.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER));
        stage.addActor(btnReturn);
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

//        spriteBatch.draw()

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
