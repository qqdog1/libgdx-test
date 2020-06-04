package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import name.qd.game.test.ResourceInstance;
import name.qd.game.test.LibTest;

public class LogoScreen implements Screen {
    private final SpriteBatch spriteBatch;
    private final AssetManager assetManager;
    private final Texture logo;
//    private final Sound sound;

    private double stateTime;

    public LogoScreen() {
        this.spriteBatch = ResourceInstance.getInstance().getSpriteBatch();
        this.assetManager = ResourceInstance.getInstance().getAssetManager();

        logo = new Texture("pic/logo.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        stateTime += delta;

        handleInput();
        if(stateTime >= 5) {
            ResourceInstance.getInstance().setScreen(new StartScreen());
            dispose();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(logo, (LibTest.WIDTH - logo.getWidth()) / 2,  LibTest.HEIGHT / 2);
        spriteBatch.end();
    }

    private void handleInput() {
        if(Gdx.input.justTouched() && stateTime >= 2) {
            ResourceInstance.getInstance().setScreen(new StartScreen());
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
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
        logo.dispose();
//        sound.dispose();
    }
}
