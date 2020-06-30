package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import name.qd.game.test.ResourceInstance;
import name.qd.game.test.constant.ScreenType;

public abstract class GameScreen implements Screen {
    public static final float SCALE_RATE = 1f;
    public static final int WIDTH = (int)(360 * SCALE_RATE);
    public static final int HEIGHT = (int)(640 * SCALE_RATE);

    final SpriteBatch spriteBatch;
    final AssetManager assetManager;

    OrthographicCamera camera;
    Viewport viewport;

    GameScreen() {
        spriteBatch = ResourceInstance.getInstance().getSpriteBatch();
        assetManager = ResourceInstance.getInstance().getAssetManager();

        camera = new OrthographicCamera();
        viewport = new FitViewport(WIDTH, HEIGHT, camera);

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

    abstract void handleInput();

    abstract ScreenType currentScreen();

    void toNextScreen(ScreenType screenType) {
        ResourceInstance.getInstance().setScreen(ScreenManager.getInstance().getScreen(screenType));
        ScreenManager.getInstance().closeScreen(currentScreen());
    }
}
