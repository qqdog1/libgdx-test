package name.qd.game.test.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import name.qd.game.test.constant.Constants;
import name.qd.game.test.screen.GameScreen;
import name.qd.game.test.utils.ResourceInstance;

public class OnHitHud extends Hud {
    private final SpriteBatch spriteBatch;
    private Texture texture;
    private float FIX_HEIGHT = 30;

    public OnHitHud() {
        spriteBatch = ResourceInstance.getInstance().getSpriteBatch();
        texture = new Texture("pic/onhit.png");
    }

    public void draw() {
        spriteBatch.draw(texture,
                0, 0, // x, y
                0, 0, // originX, originY
                FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                0, // rotation
                0, 0, // srcX, srcY
                18, 18, // srcWidth, scrHeight
                false, false // flipX, flipY
        );

        spriteBatch.draw(texture,
                0, GameScreen.SCREEN_HEIGHT - FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // x, y
                0, 0, // originX, originY
                FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                0, // rotation
                0, 0, // srcX, srcY
                18, 18, // srcWidth, scrHeight
                false, true // flipX, flipY
        );

        spriteBatch.draw(texture,
                GameScreen.SCREEN_WIDTH - FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, 0, // x, y
                0, 0, // originX, originY
                FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                0, // rotation
                0, 0, // srcX, srcY
                18, 18, // srcWidth, scrHeight
                true, false // flipX, flipY
        );

        spriteBatch.draw(texture,
                GameScreen.SCREEN_WIDTH - FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, GameScreen.SCREEN_HEIGHT - FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // x, y
                0, 0, // originX, originY
                FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                0, // rotation
                0, 0, // srcX, srcY
                18, 18, // srcWidth, scrHeight
                true, true // flipX, flipY
        );

        spriteBatch.draw(texture,
                FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, 0, // x, y
                0, 0, // originX, originY
                GameScreen.SCREEN_WIDTH - 2 * FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                0, // rotation
                18, 0, // srcX, srcY
                9, 18, // srcWidth, scrHeight
                false, false // flipX, flipY
        );

        spriteBatch.draw(texture,
                FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, GameScreen.SCREEN_HEIGHT - FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // x, y
                0, 0, // originX, originY
                GameScreen.SCREEN_WIDTH - 2 * FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                0, // rotation
                18, 0, // srcX, srcY
                9, 18, // srcWidth, scrHeight
                false, true // flipX, flipY
        );

        spriteBatch.draw(texture,
                FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // x, y
                0, 0, // originX, originY
                GameScreen.SCREEN_HEIGHT - 2 * FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                90, // rotation
                18, 0, // srcX, srcY
                9, 18, // srcWidth, scrHeight
                false, true // flipX, flipY
        );

        spriteBatch.draw(texture,
                GameScreen.SCREEN_WIDTH, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // x, y
                0, 0, // originX, originY
                GameScreen.SCREEN_HEIGHT - 2 * FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                90, // rotation
                18, 0, // srcX, srcY
                9, 18, // srcWidth, scrHeight
                false, false // flipX, flipY
        );
    }

    @Override
    public void dispose() {
    }
}
