package name.qd.game.test.queue;

import com.badlogic.gdx.Gdx;
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

        put(3f, getSmallPencilDef(GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
        put(0.3f, getSmallPencilDef(2 * GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
        put(0.3f, getSmallPencilDef(3 * GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
        put(0.3f, getSmallPencilDef(4 * GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
        put(0.3f, getSmallPencilDef(5 * GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
        put(0.3f, getSmallPencilDef(6 * GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
    }

    public void dispose() {
        AnimationCacheManager.getInstance().clear();
    }

    private void initAnimation() {
        AnimationCacheManager.getInstance().put(PENCIL, MaterialCreator.createAnimation(assetManager.get("pic/sprite/pencil.png", Texture.class), 54, 104, 3, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
        AnimationCacheManager.getInstance().put(PENCIL_DEAD, MaterialCreator.createAnimation(assetManager.get("pic/sprite/pencil.png", Texture.class), 162, 0, 54, 104, 1, 0.2f));
    }

    private EnemyDef getSmallPencilDef(float x, float y) {
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
        EnemyActionQueue enemyActionQueue = new EnemyActionQueue();
        EnemyAction enemyAction = new EnemyAction(0, -200 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, 0.5f, Interpolation.linear);
        enemyActionQueue.put(0f, enemyAction);
        pencil.setEnemyActionQueue(enemyActionQueue);
        return pencil;
    }
}
