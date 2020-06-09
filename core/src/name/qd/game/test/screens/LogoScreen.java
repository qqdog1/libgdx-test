package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import name.qd.game.test.ResourceInstance;
import name.qd.game.test.LibTest;

public class LogoScreen extends GameScreen {
    private final Texture logo;
//    private final Sound sound;

    private double stateTime;

    private float scaleWidth;
    private float scaleHeight;
    private float x;
    private float y;

    public LogoScreen() {
        logo = new Texture("pic/logo.png");

        scaleWidth = logo.getWidth() * LibTest.SCALE_RATE;
        scaleHeight = logo.getHeight() * LibTest.SCALE_RATE;
        x = (LibTest.WIDTH - scaleWidth) / 2;
        y = LibTest.HEIGHT / 2;
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
        spriteBatch.draw(logo, x, y, scaleWidth, scaleHeight);
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
