package name.qd.game.test.queue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Interpolation;

import name.qd.game.test.constant.CollisionType;
import name.qd.game.test.constant.Constants;
import name.qd.game.test.screen.GameScreen;
import name.qd.game.test.sprite.enemy.EnemyAction;
import name.qd.game.test.sprite.enemy.EnemyDef;
import name.qd.game.test.utils.AnimationCacheManager;
import name.qd.game.test.utils.MaterialCreator;

public class Stage1EnemyQueue extends EnemyDefQueue {
    private static final String PENCIL = "pencil";
    private static final String PENCIL_DEAD = "pencild";

    @Override
    protected void initData() {
        initAnimation();

        // 1
        put(2f, getSmallPencil(GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER), getSimpleAction(-200)));

        // 2
        put(3f, getSmallPencil(GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER), getSimpleAction(-150)));
        put(0, getSmallPencil(GameScreen.SCREEN_WIDTH / 4, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER), getSimpleAction(-150)));
        put(0, getSmallPencil(3 * GameScreen.SCREEN_WIDTH / 4, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER), getSimpleAction(-150)));

        // 3

    }

    public void dispose() {
        AnimationCacheManager.getInstance().clear();
    }

    private void initAnimation() {
        AnimationCacheManager.getInstance().put(PENCIL, MaterialCreator.createAnimation(assetManager.get("pic/sprite/pencil.png", Texture.class), 54, 104, 3, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
        AnimationCacheManager.getInstance().put(PENCIL_DEAD, MaterialCreator.createAnimation(assetManager.get("pic/sprite/pencil.png", Texture.class), 162, 0, 54, 104, 1, 0.2f));
    }

    private EnemyDef getSmallPencil(float x, float y, EnemyActionQueue enemyActionQueue) {
        EnemyDef pencil = new EnemyDef();
        pencil.setStartPosition(x, y);
        pencil.setAnimation(AnimationCacheManager.getInstance().getAnimation(PENCIL));
        pencil.setDead(AnimationCacheManager.getInstance().getAnimation(PENCIL_DEAD));
        pencil.setFireRate(3f);
        pencil.setRadius(15f);
        pencil.setHp(3);
        pencil.setScale(0.5f);
        pencil.setCategoryBits(CollisionType.ENEMY);
        pencil.setMaskBits(CollisionType.BULLOCK_BULLET);
        pencil.setEnemyActionQueue(enemyActionQueue);
        return pencil;
    }

    private EnemyActionQueue getSimpleAction(float y) {
        EnemyActionQueue enemyActionQueue = new EnemyActionQueue();
        EnemyAction enemyAction = new EnemyAction(0, y * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, 0.5f, Interpolation.linear);
        EnemyAction enemyAction2 = new EnemyAction(0, -650 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, 1.625f, Interpolation.linear);
        enemyActionQueue.put(0.5f, enemyAction, false);
        enemyActionQueue.put(6f, enemyAction2, false);
        return enemyActionQueue;
    }
}
