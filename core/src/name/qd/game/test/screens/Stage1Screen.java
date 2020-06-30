package name.qd.game.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import name.qd.game.test.utils.ResourceInstance;
import name.qd.game.test.constant.BulletType;
import name.qd.game.test.constant.Constants;
import name.qd.game.test.constant.ScreenType;
import name.qd.game.test.listener.WorldContactFilter;
import name.qd.game.test.listener.WorldContactListener;
import name.qd.game.test.sprites.Bullet;
import name.qd.game.test.sprites.Bullock;

public class Stage1Screen extends GameScreen {
    private AssetManager assetManager;
    private Texture background;
    private int background_y;

    private Box2DDebugRenderer box2DDebugRenderer;

    private World world;
    private Bullock bullock;
    private float lastX;
    private float lastY;

    private Stage stage;

    private float stateTime = 0;
    private float fireRate;

    private List<Bullet> lstBullet = new ArrayList<>();

    public Stage1Screen() {
        assetManager = ResourceInstance.getInstance().getAssetManager();
        background = assetManager.get("pic/stagebackground.png", Texture.class);
        stage = new Stage(viewport, spriteBatch);

        world = new World(new Vector2(0, 0), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        bullock = new Bullock(world);

        Gdx.input.setInputProcessor(stage);
        world.setContactListener(new WorldContactListener());

        fireRate = 0.5f;

        stage.addListener(new ClickListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                float diffX = 0;
                float diffY = 0;
                if (lastY != 0) {
                    diffY = y - lastY;
                }
                if(lastX != 0) {
                    diffX = x - lastX;
                }
                lastX = (int) x;
                lastY = (int) y;

                bullock.move(diffX, diffY);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                lastX = 0;
                lastY = 0;
            }
        });
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        world.step(Constants.SYSTEM_TIMESTAMP, Constants.SYSTEM_VELOCIFY_ITERATIONS, Constants.SYSTEM_POSITION_ITERATIONS);
        stateTime += delta;
        world.setContactFilter(new WorldContactFilter());

        bullock.update(delta);

        if(stateTime > fireRate) {
            stateTime -= fireRate;
            Bullet bullet = new Bullet(world, BulletType.BULLOCK_BLUE, bullock.getX() + (bullock.getWidth() / 2), bullock.getY() + bullock.getHeight());
            lstBullet.add(bullet);
        }

        if(background_y < HEIGHT - (background.getHeight() * SCALE_RATE)) {
            background_y = 0;
        }

        spriteBatch.begin();
        spriteBatch.draw(background, 0, background_y--, background.getWidth() * SCALE_RATE, background.getHeight() * SCALE_RATE);
        bullock.draw(spriteBatch);
        for(Bullet bullet : lstBullet) {
            bullet.update(delta);
            bullet.draw(spriteBatch);
        }
        spriteBatch.end();

        box2DDebugRenderer.render(world, camera.combined);

        clearBullet();
    }

    private void clearBullet() {
        List<Bullet> lst = new ArrayList<>();
        for(Bullet bullet : lstBullet) {
            if(bullet.getY() > HEIGHT) {
                lst.add(bullet);
            }
        }

        for(Bullet bullet : lst) {
            bullet.destroy();
            lstBullet.remove(bullet);
        }
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
