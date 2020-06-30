package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;

import name.qd.game.test.constant.ScreenType;
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

    private GlyphLayout glyphLayout;
    private BitmapFont bitmapFont;

    private Music music;
    private int background_y = 0;
    private int saveState;

    public StartScreen() {
        background = assetManager.get("pic/background.png", Texture.class);
        title = new Texture("pic/title.png");
        titleScaleWidth = title.getWidth() * SCALE_RATE;
        titleScaleHeight = title.getHeight() * SCALE_RATE;

        c1 = new Texture("pic/c1.png");
        c1ScaleWidth = c1.getWidth() * SCALE_RATE;
        c1ScaleHeight = c1.getHeight() * SCALE_RATE;
        c1From = new Vector2(720 * SCALE_RATE, 800 * SCALE_RATE);
        c1To = new Vector2(0, 400 * SCALE_RATE);

        c2 = new Texture("pic/c2.png");
        c2ScaleWidth = c2.getWidth() * SCALE_RATE;
        c2ScaleHeight = c2.getHeight() * SCALE_RATE;
        c2From = new Vector2(-c2.getWidth(), 800 * SCALE_RATE);
        c2To = new Vector2(WIDTH - c2ScaleWidth, 400 * SCALE_RATE);

        c3 = new Texture("pic/c3.png");
        c3ScaleWidth = c3.getWidth() * SCALE_RATE;
        c3ScaleHeight = c3.getHeight() * SCALE_RATE;
        c3From = new Vector2(720 * SCALE_RATE, 800 * SCALE_RATE);
        c3To = new Vector2((WIDTH - c3ScaleWidth) / 2, 300 * SCALE_RATE);

        finishMoveInSecond = 0.2f;
        startFlashRate = 0.3f;

        glyphLayout = new GlyphLayout();
        bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(3 * SCALE_RATE);
        bitmapFont.setColor(Color.RED);
        glyphLayout.setText(bitmapFont, "TOUCH TO START");

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

        if(background_y < HEIGHT - (background.getHeight() * SCALE_RATE)) {
            background_y = 0;
        }

        spriteBatch.begin();

        spriteBatch.draw(background, 0, background_y--, background.getWidth() * SCALE_RATE, background.getHeight() * SCALE_RATE);
        spriteBatch.draw(title, (WIDTH - titleScaleWidth) / 2, 1000 * SCALE_RATE, titleScaleWidth, titleScaleHeight);

        if((int)(stateTime / startFlashRate) % 2 > 0) {
            bitmapFont.draw(spriteBatch, glyphLayout, (WIDTH - glyphLayout.width) / 2, 200 * SCALE_RATE);
        }

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