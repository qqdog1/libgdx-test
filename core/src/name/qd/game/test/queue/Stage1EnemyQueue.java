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
        try {
            put(1, getSmallPencilDef(GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
            put(1, getSmallPencilDef(2 * GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
            put(1, getSmallPencilDef(3 * GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
            put(1, getSmallPencilDef(4 * GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
            put(1, getSmallPencilDef(5 * GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
            put(1, getSmallPencilDef(6 * GameScreen.SCREEN_WIDTH / 7, GameScreen.SCREEN_HEIGHT + (52 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)));
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
