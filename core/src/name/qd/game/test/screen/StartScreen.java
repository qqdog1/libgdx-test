package name.qd.game.test.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import name.qd.game.test.constant.Constants;
import name.qd.game.test.constant.ScreenType;
import name.qd.game.test.utils.MaterialCreator;
import name.qd.game.test.utils.PreferencesUtils;

public class StartScreen extends GameScreen {
    private enum Status {NONE,C1,C2,C3}
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

    private float finishMoveInSecond;
    private float startFlashRate;

    private BitmapFont bitmapFont;

    private Music music;
    private float background_y = 0;
    private int saveState;

    public StartScreen() {
        background = assetManager.get("pic/background.png", Texture.class);
        title = new Texture("pic/title.png");
        titleScaleWidth = title.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER;
        titleScaleHeight = title.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER;

        c1 = new Texture("pic/c1.png");
        c1ScaleWidth = c1.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER;
        c1ScaleHeight = c1.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER;
        c1From = new Vector2(SCREEN_WIDTH, SCREEN_HEIGHT);
        c1To = new Vector2(0, (SCREEN_HEIGHT - c1ScaleHeight) / 2);

        c2 = new Texture("pic/c2.png");
        c2ScaleWidth = c2.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER;
        c2ScaleHeight = c2.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER;
        c2From = new Vector2(-c2.getWidth() / Constants.PIXEL_PER_METER, SCREEN_HEIGHT);
        c2To = new Vector2(SCREEN_WIDTH - c2ScaleWidth, (SCREEN_HEIGHT - c2ScaleHeight) / 2);

        c3 = new Texture("pic/c3.png");
        c3ScaleWidth = c3.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER;
        c3ScaleHeight = c3.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER;
        c3From = new Vector2(SCREEN_WIDTH, SCREEN_HEIGHT);
        c3To = new Vector2((SCREEN_WIDTH - c3ScaleWidth) / 2, (SCREEN_HEIGHT - c3ScaleHeight * 1.5f) / 2);

        finishMoveInSecond = 0.2f;
        startFlashRate = 0.3f;

        bitmapFont = MaterialCreator.getDefaultFont(2 * SCALE_RATE / Constants.PIXEL_PER_METER);

        status = Status.NONE;

        loadSaveState();
    }

    @Override
    public void show() {
    }

    protected void handleInput() {
        if(Gdx.input.justTouched() && status == Status.C3) {
            if(saveState == 0) {
                toNextScreen(ScreenType.INTRO);
            } else {
                toNextScreen(ScreenType.MENU);
            }
        }
    }

    @Override
    protected ScreenType currentScreen() {
        return ScreenType.START;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        stateTime += delta;

        if(background_y < SCREEN_HEIGHT - (background.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)) {
            background_y = 0;
        }

        spriteBatch.begin();
        background_y -= 1 * SCALE_RATE / Constants.PIXEL_PER_METER;
        spriteBatch.draw(background, 0, background_y, background.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER, background.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);
        spriteBatch.draw(title, (SCREEN_WIDTH - titleScaleWidth) / 2, SCREEN_HEIGHT - titleScaleHeight * 1.5f, titleScaleWidth, titleScaleHeight);

//        if((int)(stateTime / startFlashRate) % 2 > 0) {
        bitmapFont.draw(spriteBatch, "TOUCH TO START", SCREEN_WIDTH / 8, SCREEN_HEIGHT / 6);
//        }

        switch(status) {
            case C3:
                drawOnLastPoint(c1, c1To, c1ScaleWidth, c1ScaleHeight);
                drawOnLastPoint(c2, c2To, c2ScaleWidth, c2ScaleHeight);
                drawOnLastPoint(c3, c3To, c3ScaleWidth, c3ScaleHeight);
                break;
            case C2:
                if(stateTime > finishMoveInSecond * 3) {
                    status = Status.C3;
                }

                drawOnLastPoint(c1, c1To, c1ScaleWidth, c1ScaleHeight);
                drawOnLastPoint(c2, c2To, c2ScaleWidth, c2ScaleHeight);
                drawMovingTexture(c3, c3From, c3To, c3ScaleWidth, c3ScaleHeight, (stateTime - (finishMoveInSecond * 2)) / finishMoveInSecond);
                break;
            case C1:
                if(stateTime > finishMoveInSecond * 2) {
                    status = Status.C2;
                }

                drawOnLastPoint(c1, c1To, c1ScaleWidth, c1ScaleHeight);
                drawMovingTexture(c2, c2From, c2To, c2ScaleWidth, c2ScaleHeight, (stateTime - finishMoveInSecond) / finishMoveInSecond);
                break;
            case NONE:
                if(stateTime > finishMoveInSecond) {
                    status = Status.C1;
                }
                drawMovingTexture(c1, c1From, c1To, c1ScaleWidth, c1ScaleHeight, stateTime / finishMoveInSecond);
                break;
        }

        spriteBatch.end();

        stage.draw();
    }

    private void drawOnLastPoint(Texture texture, Vector2 position, float width, float height) {
        spriteBatch.draw(texture, position.x, position.y, width, height);
    }

    private void drawMovingTexture(Texture texture, Vector2 from, Vector2 to, float width, float height, float movedRate) {
        float x = (to.x - from.x) * movedRate + from.x;
        float y = (to.y - from.y) * movedRate + from.y;
        spriteBatch.draw(texture, x, y, width, height);
    }

    private void loadSaveState() {
        saveState = PreferencesUtils.getIntValue(PreferencesUtils.PreferencesEnum.LEVEL);
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