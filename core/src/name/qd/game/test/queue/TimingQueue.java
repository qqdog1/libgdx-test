package name.qd.game.test.queue;

import java.util.LinkedList;
import java.util.Queue;

public class TimingQueue<T> {
    private Queue<Float> timeQueue = new LinkedList<>();
    private Queue<T> timingQueue = new LinkedList<>();
    private float waitingTime;

    protected void put(float time, T t) {
        timeQueue.add(time);
        timingQueue.add(t);
    }

    public boolean isFinished() {
        return timingQueue.size() == 0;
    }

    public T getNextEnemyDef(float delta) {
        waitingTime += delta;

        Float spawnTime = timeQueue.peek();
        if(spawnTime != null) {
            if(waitingTime >= spawnTime) {
                waitingTime -= spawnTime;
                timeQueue.remove();
                return timingQueue.poll();
            }
        }
        return null;
    }
}
