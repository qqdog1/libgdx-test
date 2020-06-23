package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import name.qd.game.test.LibTest;
import name.qd.game.test.constant.ScreenType;

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
        super.render(delta);

        stateTime += delta;

        if(stateTime >= 5) {
            toNextScreen(ScreenType.START);
        }

        spriteBatch.begin();
        spriteBatch.draw(logo, x, y, scaleWidth, scaleHeight);
        spriteBatch.end();
    }

    protected void handleInput() {
        if(Gdx.input.justTouched() && stateTime >= 2) {
            toNextScreen(ScreenType.START);
        }
    }

    @Override
    protected ScreenType currentScreen() {
        return ScreenType.LOGO;
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
        Gdx.app.log("Disposed", "LogoScreen");
    }
}
