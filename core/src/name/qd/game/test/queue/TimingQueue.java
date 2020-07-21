package name.qd.game.test.queue;

import java.util.LinkedList;

public class TimingQueue<T> {
    private LinkedList<Float> timeQueue = new LinkedList<>();
    private LinkedList<T> timingQueue = new LinkedList<>();
    private float waitingTime;
    private boolean isRecursive = false;
    private int index = 0;

    public TimingQueue() {
    }

    public TimingQueue(boolean isRecursive) {
        this.isRecursive = isRecursive;
    }

    protected void put(float time, T t) {
        timeQueue.add(time);
        timingQueue.add(t);
    }

    public boolean isFinished() {
        return timingQueue.size() == 0;
    }

    public T getNext(float delta) {
        waitingTime += delta;

        if(isRecursive) {
            return getNextWithRecursive();
        } else {
            return getNext();
        }
    }

    private T getNext() {
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

    private T getNextWithRecursive() {
        Float spawnTime = timeQueue.get(index);
        if(waitingTime >= spawnTime) {
            waitingTime -= spawnTime;
            T t = timingQueue.get(index);
            if(index == timingQueue.size() - 1) {
                index = 0;
            } else {
                index++;
            }
            return t;
        }
        return null;
    }
}
