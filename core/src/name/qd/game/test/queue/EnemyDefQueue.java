package name.qd.game.test.queue;

import com.badlogic.gdx.assets.AssetManager;

import java.util.LinkedList;
import java.util.Queue;

import name.qd.game.test.sprite.enemy.EnemyDef;
import name.qd.game.test.utils.ResourceInstance;

public abstract class EnemyDefQueue {
    protected AssetManager assetManager = ResourceInstance.getInstance().getAssetManager();
    private Queue<Float> timeQueue = new LinkedList<>();
    private Queue<EnemyDef> enemyDefQueue = new LinkedList<>();
    private float waitingTime;

    public EnemyDefQueue() {
        initData();
    }

    protected abstract void initData();

    protected void put(float time, EnemyDef enemyDef) {
        timeQueue.add(time);
        enemyDefQueue.add(enemyDef);
    }

    public boolean isFinished() {
        return enemyDefQueue.size() == 0;
    }

    public EnemyDef getNextEnemyDef(float delta) {
        waitingTime += delta;

        Float spawnTime = timeQueue.peek();
        if(spawnTime != null) {
            if(waitingTime >= spawnTime) {
                waitingTime -= spawnTime;
                timeQueue.remove();
                return enemyDefQueue.poll();
            }
        }
        return null;
    }
}
