package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;
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
        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("btn", "return");
                ResourceInstance.getInstance().setScreen(new MenuScreen());
                dispose();
            }
        });

        Table table = new Table();
        table.setDebug(true);
        table.top().left();

        table.add(btnReturn)
                .width(btnReturn.getWidth() * LibTest.SCALE_RATE)
                .height(btnReturn.getHeight() * LibTest.SCALE_RATE)
                .padLeft(500 * LibTest.SCALE_RATE);

        stage.addActor(btnReturn);
    }

    @Override
    public void show() {

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
        stage.dispose();
    }
}
