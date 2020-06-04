package name.qd.game.test;

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

    void setGame(Game game) {
        this.game = game;
    }

    void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    void setAssetManager(AssetManager assetManager) {
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
