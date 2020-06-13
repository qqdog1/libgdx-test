package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;
import name.qd.game.test.utils.MaterialCreator;

public class MenuScreen extends GameScreen implements InputProcessor {
    private AssetManager assetManager;
    private Texture background;
    private TextButton btnUpgrade;
    private TextButton btnSettings;
    private TextButton btnStageSelect;

    private int background_y = 0;
    private int lastY;
    private int backgroundScaleHeight;

    public MenuScreen() {
        assetManager = ResourceInstance.getInstance().getAssetManager();
        background = assetManager.get("pic/stage.png", Texture.class);
        backgroundScaleHeight = (int)(background.getHeight() * LibTest.SCALE_RATE);

        btnUpgrade = MaterialCreator.createButton(assetManager.get("pic/btn/upgrade.png", Texture.class));
        btnSettings = MaterialCreator.createButton(assetManager.get("pic/btn/settings.png", Texture.class));
        btnStageSelect = MaterialCreator.createButton(assetManager.get("pic/btn/stageselect.png", Texture.class));

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void show() {

    }

    protected void handleInput() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(background, 0, background_y, background.getWidth() * LibTest.SCALE_RATE, background.getHeight() * LibTest.SCALE_RATE);
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

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        lastY = 0;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(lastY != 0) {
            int diff = screenY - lastY;
            background_y -= diff;
            if(background_y > 0) {
                background_y = 0;
            }
            if(background_y < -backgroundScaleHeight + LibTest.HEIGHT) {
                background_y = -backgroundScaleHeight + LibTest.HEIGHT;
            }
        }
        lastY = screenY;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
