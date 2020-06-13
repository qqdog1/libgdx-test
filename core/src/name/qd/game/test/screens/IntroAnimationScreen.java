package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;
import name.qd.game.test.utils.PreferencesUtils;

public class IntroAnimationScreen extends GameScreen {
    private Texture texture;

    public IntroAnimationScreen() {
        // TODO 還不知道怎麼呈現
        //  可以用gif或是一堆圖像漫畫or ...

        texture = new Texture("pic/intro/01.png");
    }

    @Override
    public void show() {

    }

    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            PreferencesUtils.set(PreferencesUtils.PreferencesEnum.STATE, 1);
            ResourceInstance.getInstance().setScreen(new MenuScreen());
            dispose();
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(texture, 0, 0, texture.getWidth() * LibTest.SCALE_RATE, texture.getHeight() * LibTest.SCALE_RATE);
        spriteBatch.end();
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
        texture.dispose();
    }
}
