package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;
import name.qd.game.test.constant.ScreenType;
import name.qd.game.test.sprites.Bullock;

public class Stage1Screen extends GameScreen {
    private AssetManager assetManager;
    private Texture background;
    private int background_y;

    private Box2DDebugRenderer box2DDebugRenderer;

    private World world;
    private Bullock bullock;

    public Stage1Screen() {
        assetManager = ResourceInstance.getInstance().getAssetManager();
        background = assetManager.get("pic/stagebackground.png", Texture.class);

        world = new World(new Vector2(0, 0), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        bullock = new Bullock(world);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(background_y < LibTest.HEIGHT - (background.getHeight() * LibTest.SCALE_RATE)) {
            background_y = 0;
        }

        spriteBatch.begin();
        spriteBatch.draw(background, 0, background_y--, background.getWidth() * LibTest.SCALE_RATE, background.getHeight() * LibTest.SCALE_RATE);
        bullock.draw(spriteBatch);

        spriteBatch.end();

        box2DDebugRenderer.render(world, camera.combined);

    }

    @Override
    protected void handleInput() {
    }

    @Override
    protected ScreenType currentScreen() {
        return ScreenType.L1;
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
