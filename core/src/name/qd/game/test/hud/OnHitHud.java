package name.qd.game.test.hud;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import name.qd.game.test.constant.Constants;
import name.qd.game.test.screen.GameScreen;
import name.qd.game.test.utils.ResourceInstance;

public class OnHitHud implements Disposable {
    private final Stage stage;
    private final Viewport viewport;
    private final SpriteBatch spriteBatch;
    private Texture texture;
    private float FIX_HEIGHT = 30;

    public OnHitHud() {
        spriteBatch = ResourceInstance.getInstance().getSpriteBatch();
        viewport = new FitViewport(GameScreen.WIDTH, GameScreen.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        texture = new Texture("pic/onhit.png");

        Image imageTop = new Image(texture);
        Image imageBottom = new Image(texture);
        Image imageLeft= new Image(texture);
        Image imageRight = new Image(texture);

        imageBottom.setScaleX(GameScreen.WIDTH / texture.getWidth());
        imageBottom.setScaleY(FIX_HEIGHT / texture.getHeight());

        imageTop.setScaleX(GameScreen.WIDTH / texture.getWidth());
        imageTop.setScaleY(FIX_HEIGHT / texture.getHeight());
        imageTop.setRotation(180);
        imageTop.setPosition(0, 50);

        imageLeft.setScaleX(FIX_HEIGHT / texture.getHeight());
        imageLeft.setScaleY(GameScreen.WIDTH / texture.getWidth());
        imageLeft.setRotation(90);

        imageRight.setScaleX(FIX_HEIGHT / texture.getHeight());
        imageRight.setScaleY(GameScreen.WIDTH / texture.getWidth());
        imageRight.setRotation(270);
        imageRight.setPosition(GameScreen.WIDTH - FIX_HEIGHT, 0);

        stage.addActor(imageBottom);
        stage.addActor(imageTop);
        stage.addActor(imageLeft);
        stage.addActor(imageRight);
    }

    public void draw() {
        spriteBatch.draw(texture,
                0, 0, // x, y
                0, 0, // originX, originY
                GameScreen.SCREEN_WIDTH, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                0, // rotation
                0, 0, // srcX, srcY
                texture.getWidth(), texture.getHeight(), // srcWidth, scrHeight
                false, false // flipX, flipY
        );

        spriteBatch.draw(texture,
                0, GameScreen.SCREEN_HEIGHT - FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // x, y
                0, 0, // originX, originY
                GameScreen.SCREEN_WIDTH, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                0, // rotation
                0, 0, // srcX, srcY
                texture.getWidth(), texture.getHeight(), // srcWidth, scrHeight
                false, true // flipX, flipY
        );

        spriteBatch.draw(texture,
                FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, 0, // x, y
                0, 0, // originX, originY
                GameScreen.SCREEN_HEIGHT, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                90, // rotation
                0, 0, // srcX, srcY
                texture.getWidth(), texture.getHeight(), // srcWidth, scrHeight
                false, true // flipX, flipY
        );

        spriteBatch.draw(texture,
                GameScreen.SCREEN_WIDTH, 0, // x, y
                0, 0, // originX, originY
                GameScreen.SCREEN_HEIGHT, FIX_HEIGHT * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, // width, height
                1, 1, // scaleX, scaleY
                90, // rotation
                0, 0, // srcX, srcY
                texture.getWidth(), texture.getHeight(), // srcWidth, scrHeight
                false, false // flipX, flipY
        );
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void dispose() {
    }
}
