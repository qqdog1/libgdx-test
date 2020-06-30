package name.qd.game.test.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
}
