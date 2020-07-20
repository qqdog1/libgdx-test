package name.qd.game.test.queue;

import name.qd.game.test.sprite.enemy.EnemyAction;

public class EnemyActionQueue extends TimingQueue<EnemyAction> {

    @Override
    public void put(float time, EnemyAction enemyAction) throws Exception {
        if(enemyAction.getDuration() > time) {
            throw new Exception("Action duration is bigger than delay time.");
        }
        super.put(time, enemyAction);
    }
}
