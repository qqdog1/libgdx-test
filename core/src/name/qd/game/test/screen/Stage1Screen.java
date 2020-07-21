package name.qd.game.test.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import name.qd.game.test.constant.CollisionType;
import name.qd.game.test.hud.FightingHud;
import name.qd.game.test.hud.SettlementHud;
import name.qd.game.test.queue.Stage1EnemyQueue;
import name.qd.game.test.sprite.enemy.Enemy;
import name.qd.game.test.sprite.bullet.Bullet;
import name.qd.game.test.sprite.bullet.BulletDef;
import name.qd.game.test.sprite.enemy.EnemyDef;
import name.qd.game.test.utils.ResourceInstance;
import name.qd.game.test.constant.Constants;
import name.qd.game.test.constant.ScreenType;
import name.qd.game.test.listener.WorldContactFilter;
import name.qd.game.test.listener.WorldContactListener;
import name.qd.game.test.sprite.Bullock;

public class Stage1Screen extends GameScreen {
    private AssetManager assetManager = ResourceInstance.getInstance().getAssetManager();
    private Texture background;
    private float background_y;

    private Box2DDebugRenderer box2DDebugRenderer;

    private World world;
    private Bullock bullock;
    private float lastX;
    private float lastY;

    private List<Bullet> lstBullockBullet = new ArrayList<>();
    private List<Bullet> lstEnemyBullet = new ArrayList<>();
    private List<Enemy> lstEnemy = new ArrayList<>();

    private Stage1EnemyQueue stage1EnemyQueue = new Stage1EnemyQueue();
    private FightingHud fightingHud;
    private SettlementHud settlementHud;

    public Stage1Screen() {
        background = assetManager.get("pic/stagebackground.png", Texture.class);

        world = new World(new Vector2(0, 0), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
//        box2DDebugRenderer.setDrawBodies(false);

        bullock = new Bullock(world);
        fightingHud = new FightingHud(bullock.getHp());

        Gdx.input.setInputProcessor(stage);
        world.setContactListener(new WorldContactListener());

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
                lastX = x;
                lastY = y;

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

        world.setContactFilter(new WorldContactFilter());


        bullock.update(delta);

        if(!bullock.isDestroyed()) {
            if(bullock.isShouldFire()) {
                BulletDef bulletDef = new BulletDef();
                bulletDef.setStartPosition(bullock.getX() + (bullock.getWidth() / 2), bullock.getY() + bullock.getHeight());
                bulletDef.setTexture(assetManager.get("pic/sprite/bulletblue.png", Texture.class));
                bulletDef.setVelocity(0, 40);
                bulletDef.setCategoryBits(CollisionType.BULLOCK_BULLET);
                bulletDef.setMaskBits(CollisionType.ENEMY);
                Bullet bullockBullet = new Bullet(world, bulletDef);
                lstBullockBullet.add(bullockBullet);
            }
        }

        if(lstEnemy.size() > 0) {
            // 要改到clear enemy 之後
            for(Enemy enemy : lstEnemy) {
                if(enemy.isShouldFire()) {
                    BulletDef bulletDef = new BulletDef();
                    bulletDef.setStartPosition(enemy.getX() + (enemy.getWidth() / 2), enemy.getY());
                    bulletDef.setVelocity(0, -20);
                    bulletDef.setTexture(assetManager.get("pic/sprite/ebullet.png", Texture.class));
                    bulletDef.setCategoryBits(CollisionType.ENEMY_BULLET);
                    bulletDef.setMaskBits(CollisionType.BULLOCK);
                    Bullet enemyBullet = new Bullet(world, bulletDef);
                    lstEnemyBullet.add(enemyBullet);
                }
            }
        }

        EnemyDef enemyDef = stage1EnemyQueue.getNext(delta);
        if(enemyDef != null) {
            Enemy enemy = new Enemy(world, enemyDef);
            lstEnemy.add(enemy);
        }

        if(background_y < SCREEN_HEIGHT - (background.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER)) {
            background_y = 0;
        }

        spriteBatch.begin();
        spriteBatch.draw(background, 0, background_y, background.getWidth() * SCALE_RATE / Constants.PIXEL_PER_METER, background.getHeight() * SCALE_RATE / Constants.PIXEL_PER_METER);
        background_y -= 1 / Constants.PIXEL_PER_METER;
        bullock.draw(spriteBatch);
        for(Bullet bullockBullet : lstBullockBullet) {
            bullockBullet.update(delta);
            bullockBullet.draw(spriteBatch);
        }
        for(Bullet bullet : lstEnemyBullet) {
            bullet.update(delta);
            bullet.draw(spriteBatch);
        }
        for(Enemy enemy : lstEnemy) {
            enemy.update(delta);
            enemy.draw(spriteBatch);
        }
        spriteBatch.end();

        box2DDebugRenderer.render(world, camera.combined);

        clearSprite();

        spriteBatch.setProjectionMatrix(fightingHud.getStage().getCamera().combined);
        fightingHud.setHp(bullock.getHp());
        fightingHud.getStage().draw();

        if(stage1EnemyQueue.isFinished() && lstEnemy.size() == 0) {
            // 過關
            if(settlementHud == null) {
                settlementHud = new SettlementHud(999);
            }

            settlementHud.getStage().draw();
            settlementHud.getStage().act(delta);

            if(settlementHud.isClickFinish()) {
                toNextScreen(ScreenType.MENU);
            }
        }

        stage.draw();

        world.step(Constants.SYSTEM_TIMESTAMP, Constants.SYSTEM_VELOCIFY_ITERATIONS, Constants.SYSTEM_POSITION_ITERATIONS);
    }

    private void clearSprite() {
        clearBullet();
        clearEnemyBullet();
        clearEnemy();
    }

    private void clearBullet() {
        List<Bullet> lst = new ArrayList<>();
        for(Bullet bullockBullet : lstBullockBullet) {
            if(bullockBullet.getY() > SCREEN_HEIGHT) {
                lst.add(bullockBullet);
            } else if(bullockBullet.isDestroyed()) {
                lst.add(bullockBullet);
            }
        }

        for(Bullet bullockBullet : lst) {
            bullockBullet.destroy();
            lstBullockBullet.remove(bullockBullet);
        }
    }

    private void clearEnemyBullet() {
        List<Bullet> lst = new ArrayList<>();
        for(Bullet bullet : lstEnemyBullet) {
            if(bullet.getY() <= 0) {
                lst.add(bullet);
            } else if(bullet.isDestroyed()) {
                lst.add(bullet);
            }
        }

        for(Bullet bullet : lst) {
            bullet.destroy();
            lstEnemyBullet.remove(bullet);
        }
    }

    private void clearEnemy() {
        List<Enemy> lst = new ArrayList<>();
        for(Enemy enemy : lstEnemy) {
            if(enemy.isDestroyed()) {
                lst.add(enemy);
            }
        }

        for(Enemy enemy : lst) {
            enemy.destroy();
            lstEnemy.remove(enemy);
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
        box2DDebugRenderer.dispose();
        fightingHud.dispose();
    }
}
