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
import name.qd.game.test.utils.MaterialCreator;

public class Stage1EnemyQueue extends EnemyDefQueue {
    @Override
    protected void initData() {
        EnemyDef pencil = getSmallPencilDef(0, GameScreen.SCREEN_HEIGHT - 20 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER);

        try {
            put(1, pencil);
        } catch (Exception e) {
            Gdx.app.log("Exception", String.format("Put to EnemyDefQueue failed. %s", e.getMessage()));
        }
    }

    private EnemyDef getSmallPencilDef(float x, float y) {
        EnemyDef pencil = new EnemyDef();
        pencil.setStartPosition(x, y);
        pencil.setAnimation(MaterialCreator.createAnimation(assetManager.get("pic/sprite/pencil.png", Texture.class), 54, 104, 3, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
        pencil.setDead(MaterialCreator.createAnimation(assetManager.get("pic/sprite/pencil.png", Texture.class), 162, 0, 54, 104, 1, 0.2f));
        pencil.setFireRate(3f);
        pencil.setRadius(25f);
        pencil.setHp(3);
        pencil.setScale(0.5f);
        pencil.setCategoryBits(CollisionType.ENEMY);
        pencil.setMaskBits(CollisionType.BULLOCK_BULLET);
        EnemyActionQueue enemyActionQueue = new EnemyActionQueue(true);
//        EnemyAction enemyAction = new EnemyAction(0, -200 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, 0.5f, Interpolation.linear);
        EnemyAction enemyAction2 = new EnemyAction(100 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, 0, 0.5f, Interpolation.linear);
        EnemyAction enemyAction4 = new EnemyAction(-100 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, 0, 0.5f, Interpolation.linear);
//        EnemyAction enemyAction3 = new EnemyAction(100 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, 200 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, 0.5f, Interpolation.exp10In);
        try {
//            enemyActionQueue.put(1f, enemyAction);
            enemyActionQueue.put(1f, enemyAction2);
            enemyActionQueue.put(1f, enemyAction4);
        } catch (Exception e) {
            Gdx.app.log("Exception", String.format("Put to EnemyActionQueue failed. %s", e.getMessage()));
        }
        pencil.setEnemyActionQueue(enemyActionQueue);
        return pencil;
    }
}
