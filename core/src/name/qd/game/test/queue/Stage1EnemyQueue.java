package name.qd.game.test.queue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import name.qd.game.test.constant.Constants;
import name.qd.game.test.screen.GameScreen;
import name.qd.game.test.sprite.enemy.EnemyDef;
import name.qd.game.test.utils.MaterialCreator;

public class Stage1EnemyQueue extends EnemyDefQueue {
    @Override
    protected void initData() {
        EnemyDef pencil = new EnemyDef();
        pencil.setStartPosition((GameScreen.SCREEN_WIDTH - (54 / Constants.PIXEL_PER_METER)) / 2, GameScreen.SCREEN_HEIGHT);
        pencil.setVelocity(0, -20);
        pencil.setMoveRange(80);
        pencil.setAnimation(MaterialCreator.createAnimation(assetManager.get("pic/sprite/pencil.png", Texture.class), 54, 104, 3, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
        pencil.setDead(MaterialCreator.createAnimation(assetManager.get("pic/sprite/pencil.png", Texture.class), 162, 0, 54, 104, 1, 0.2f));

        put(2, pencil);
    }
}
