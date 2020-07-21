package name.qd.game.test.queue;

import name.qd.game.test.sprite.enemy.EnemyAction;

public class EnemyActionQueue extends TimingQueue<EnemyAction> {

    public EnemyActionQueue() {
        super();
    }

    public EnemyActionQueue(boolean isRecursive) {
        super(isRecursive);
    }

    @Override
    public void put(float time, EnemyAction enemyAction) {
        super.put(time, enemyAction);
    }
}
