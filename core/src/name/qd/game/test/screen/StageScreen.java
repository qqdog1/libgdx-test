package name.qd.game.test.screen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.constant.ScreenType;
import name.qd.game.test.listener.WorldContactFilter;
import name.qd.game.test.sprite.Bullock;

public class StageScreen extends GameScreen {
    private World world;
    private Bullock bullock;
    private Box2DDebugRenderer box2DDebugRenderer;

    public StageScreen() {
        world = new World(new Vector2(0, 0), true);
        world.setContactFilter(new WorldContactFilter());
        box2DDebugRenderer = new Box2DDebugRenderer();
    }

    @Override
    void handleInput() {

    }

    @Override
    ScreenType currentScreen() {
        return null;
    }

    @Override
    public void show() {

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
