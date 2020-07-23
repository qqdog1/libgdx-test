package name.qd.game.test.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import name.qd.game.test.sprite.BullockDef;

public class ResourceInstance {
    private static ResourceInstance instance = new ResourceInstance();
    private Game game;
    private SpriteBatch spriteBatch;
    private AssetManager assetManager;

    public static ResourceInstance getInstance() {
        return instance;
    }

    private ResourceInstance() {
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setScreen(Screen screen) {
        game.setScreen(screen);
    }

    public BullockDef getBullockDef() {
        int weapon = 0;
        if(PreferencesUtils.isLabelExist(PreferencesUtils.PreferencesEnum.WEAPON)) {
            weapon = PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.WEAPON);
        }
        int hp = 3;
        if(PreferencesUtils.isLabelExist(PreferencesUtils.PreferencesEnum.HP)) {
            hp = PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.HP);
        }

        BullockDef bullockDef = new BullockDef();
        bullockDef.setFireRate(0.5f);
        bullockDef.setWeapon(weapon);
        bullockDef.setHp(hp);
        bullockDef.setRadius(15f);
        return bullockDef;
    }
}
