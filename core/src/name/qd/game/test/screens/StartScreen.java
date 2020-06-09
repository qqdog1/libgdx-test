package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;

public class StartScreen extends GameScreen {
    private enum Status {NONE,C1,C2,C3};
    private Status status;
    private Texture background;

    private Texture title;
    private float titleScaleWidth;
    private float titleScaleHeight;

    private Texture c1;
    private float c1ScaleWidth;
    private float c1ScaleHeight;

    private Texture c2;
    private float c2ScaleWidth;
    private float c2ScaleHeight;

    private Texture c3;
    private float c3ScaleWidth;
    private float c3ScaleHeight;

    private Music music;
    private int y = 0;

    public StartScreen() {
        background = assetManager.get("pic/background.png", Texture.class);
        title = new Texture("pic/title.png");
        titleScaleWidth = title.getWidth() * LibTest.SCALE_RATE;
        titleScaleHeight = title.getHeight() * LibTest.SCALE_RATE;

        c1 = new Texture("pic/c1.png");
        c1ScaleWidth = c1.getWidth() * LibTest.SCALE_RATE;
        c1ScaleHeight = c1.getHeight() * LibTest.SCALE_RATE;

        c2 = new Texture("pic/c2.png");
        c2ScaleWidth = c2.getWidth() * LibTest.SCALE_RATE;
        c2ScaleHeight = c2.getHeight() * LibTest.SCALE_RATE;

        c3 = new Texture("pic/c3.png");
        c3ScaleWidth = c3.getWidth() * LibTest.SCALE_RATE;
        c3ScaleHeight = c3.getHeight() * LibTest.SCALE_RATE;

        // TODO: Title掉下來 大家飛過來 start跑出來
        //  自動 login google 讀紀錄 或 local紀錄
        //  先做local的就好了
        //  沒紀錄的 進IntroAnimationScreen
        //  有紀錄 進MenuScreen

        status = Status.NONE;
    }

    @Override
    public void show() {
    }

    private void handleInput() {
        if(Gdx.input.justTouched() && status == Status.C3) {
            ResourceInstance.getInstance().setScreen(new IntroAnimationScreen());
            dispose();
        }
    }

    @Override
    public void render(float delta) {
        handleInput();

        if(y < LibTest.HEIGHT - (background.getHeight() * LibTest.SCALE_RATE)) {
            y = 0;
        }

        spriteBatch.begin();

        spriteBatch.draw(background, 0, y--, background.getWidth() * LibTest.SCALE_RATE, background.getHeight() * LibTest.SCALE_RATE);
        spriteBatch.draw(title, (LibTest.WIDTH - titleScaleWidth) / 2, 1000 * LibTest.SCALE_RATE, titleScaleWidth, titleScaleHeight);

        switch(status) {
            case C3:
            case C2:
            case C1:
            case NONE:
        }

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
        title.dispose();
        c1.dispose();
        c2.dispose();
        c3.dispose();
    }
}
