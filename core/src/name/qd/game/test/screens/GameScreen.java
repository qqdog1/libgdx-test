package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
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

        camera.position.set(viewport.getWorldWidth(), viewport.getWorldHeight(), 0);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta) {
        handleInput();

        camera.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected abstract void handleInput();
}
