package name.qd.game.test.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;

public abstract class GameScreen implements Screen {
    protected final SpriteBatch spriteBatch;
    protected final AssetManager assetManager;

    protected OrthographicCamera camera;
    protected Viewport viewport;

    public GameScreen() {
        spriteBatch = ResourceInstance.getInstance().getSpriteBatch();
        assetManager = ResourceInstance.getInstance().getAssetManager();

        camera = new OrthographicCamera();
        viewport = new FitViewport(LibTest.WIDTH, LibTest.HEIGHT, camera);
    }
}
