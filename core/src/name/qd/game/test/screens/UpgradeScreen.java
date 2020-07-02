package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import name.qd.game.test.constant.Constants;
import name.qd.game.test.constant.ScreenType;
import name.qd.game.test.utils.MaterialCreator;

public class UpgradeScreen extends GameScreen {
    private TextButton btnReturn;
    private Stage stage;

    public UpgradeScreen() {
        stage = new Stage(viewport, spriteBatch);

        Texture texture = assetManager.get("pic/btn/return.png", Texture.class);
        btnReturn = MaterialCreator.createButton(texture);
        btnReturn.setTransform(true);
        btnReturn.setScale(SCALE_RATE / Constants.PIXEL_PER_METER);
        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("btn", "return");
                toNextScreen(ScreenType.MENU);
            }
        });

        btnReturn.setPosition(SCREEN_WIDTH - (btnReturn.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER), SCREEN_HEIGHT - (btnReturn.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER));

        stage.addActor(btnReturn);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {
    }

    @Override
    protected ScreenType currentScreen() {
        return ScreenType.UPGRADE;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        stage.draw();
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
