package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;

public class StartScreen extends GameScreen {
    private enum Status {NONE,C1,C2,C3};
    private Status status;
    private Texture background;
    private int y = 0;



    public StartScreen() {
        background = assetManager.get("pic/background.png", Texture.class);
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

        switch(status) {
            case C3:
            case C2:
            case C1:
            case NONE:
        }

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
