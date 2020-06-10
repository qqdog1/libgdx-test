package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;

public class StartScreen extends GameScreen {
    private enum Status {NONE,C1,C2,C3};
    private Status status;
    private Texture background;

    private Texture title;
    private float titleScaleWidth;
    private float titleScaleHeight;

    private float stateTime;

    private Texture c1;
    private float c1ScaleWidth;
    private float c1ScaleHeight;
    private Vector2 c1From;
    private Vector2 c1To;

    private Texture c2;
    private float c2ScaleWidth;
    private float c2ScaleHeight;
    private Vector2 c2From;
    private Vector2 c2To;

    private Texture c3;
    private float c3ScaleWidth;
    private float c3ScaleHeight;
    private Vector2 c3From;
    private Vector2 c3To;

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
        c1From = new Vector2(720, 800);
        c1To = new Vector2(72, 400);

        c2 = new Texture("pic/c2.png");
        c2ScaleWidth = c2.getWidth() * LibTest.SCALE_RATE;
        c2ScaleHeight = c2.getHeight() * LibTest.SCALE_RATE;
        c2From = new Vector2(-c2.getWidth(), 800);
        c2To = new Vector2(400, 400);

        c3 = new Texture("pic/c3.png");
        c3ScaleWidth = c3.getWidth() * LibTest.SCALE_RATE;
        c3ScaleHeight = c3.getHeight() * LibTest.SCALE_RATE;
        c3From = new Vector2(720, 800);
        c3To = new Vector2(300, 300);

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

        stateTime += delta;

        if(y < LibTest.HEIGHT - (background.getHeight() * LibTest.SCALE_RATE)) {
            y = 0;
        }

        spriteBatch.begin();

        spriteBatch.draw(background, 0, y--, background.getWidth() * LibTest.SCALE_RATE, background.getHeight() * LibTest.SCALE_RATE);
        spriteBatch.draw(title, (LibTest.WIDTH - titleScaleWidth) / 2, 1000 * LibTest.SCALE_RATE, titleScaleWidth, titleScaleHeight);

        switch(status) {
            case C3:
                drawOnLastPoint(c1, c1To, c1ScaleWidth, c1ScaleHeight);
                drawOnLastPoint(c2, c2To, c2ScaleWidth, c2ScaleHeight);
                drawOnLastPoint(c3, c3To, c3ScaleWidth, c3ScaleHeight);
                break;
            case C2:
                if(stateTime > 3) {
                    status = Status.C3;
                }

                drawOnLastPoint(c1, c1To, c1ScaleWidth, c1ScaleHeight);
                drawOnLastPoint(c2, c2To, c2ScaleWidth, c2ScaleHeight);
                drawMovingTexture(c3, c3From, c3To, c3ScaleWidth, c3ScaleHeight, stateTime-2);
                break;
            case C1:
                if(stateTime > 2) {
                    status = Status.C2;
                }

                drawOnLastPoint(c1, c1To, c1ScaleWidth, c1ScaleHeight);
                drawMovingTexture(c2, c2From, c2To, c2ScaleWidth, c2ScaleHeight, stateTime-1);
                break;
            case NONE:
                if(stateTime > 1) {
                    status = Status.C1;
                }
                drawMovingTexture(c1, c1From, c1To, c1ScaleWidth, c1ScaleHeight, stateTime);
                break;
        }

        spriteBatch.end();
    }

    private void drawOnLastPoint(Texture texture, Vector2 position, float width, float height) {
        spriteBatch.draw(texture, position.x * LibTest.SCALE_RATE, position.y * LibTest.SCALE_RATE, width, height);
    }

    private void drawMovingTexture(Texture texture, Vector2 from, Vector2 to, float width, float height, float stateTime) {

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
