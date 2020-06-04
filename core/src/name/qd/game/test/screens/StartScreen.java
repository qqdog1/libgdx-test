package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import name.qd.game.test.LibTest;

public class StartScreen extends GameScreen {
    private Texture background;
    private int y = 0;

    public StartScreen() {
        background = assetManager.get("pic/background.png", Texture.class);

    }

    @Override
    public void show() {
    }

    private void handleInput() {
    }

    @Override
    public void render(float delta) {
        handleInput();

        if(y < LibTest.HEIGHT - (background.getHeight() * LibTest.SCALE_RATE)) {
            y = 0;
        }

        spriteBatch.begin();
        spriteBatch.draw(background, 0, y--, (int)(background.getWidth() * LibTest.SCALE_RATE), (int)(background.getHeight() * LibTest.SCALE_RATE));
        spriteBatch.end();
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

    }
}
