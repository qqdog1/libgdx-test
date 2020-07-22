package name.qd.game.test.queue;

import com.badlogic.gdx.Gdx;

import java.util.LinkedList;

public abstract class TimingQueue<T> {
    private LinkedList<Float> timeQueue = new LinkedList<>();
    private LinkedList<T> timingQueue = new LinkedList<>();
    private LinkedList<Boolean> isLoopingQueue = new LinkedList<>();
    private float waitingTime;
    private int index = 0;

    public TimingQueue() {
    }

    protected void put(float time, T t) {
        put(time, t, false);
    }

    protected void put(float time, T t, boolean looping) {
        timeQueue.add(time);
        timingQueue.add(t);
        isLoopingQueue.add(looping);
    }

    public boolean isFinished() {
        return timingQueue.size() == 0;
    }

    public T getNext(float delta) {
        waitingTime += delta;
        Float spawnTime;
        try {
            spawnTime = timeQueue.get(index);
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
        if(spawnTime != null) {
            if(waitingTime >= spawnTime) {
                waitingTime -= spawnTime;
                boolean isLooping = isLoopingQueue.get(index);
                Gdx.app.log("Triggered", String.format("Index:%d, isLooping: %b", index, isLooping));
                if(isLooping) {
                    T t = timingQueue.get(index);
                    if(index == timingQueue.size() - 1) {
                        index = 0;
                    } else {
                        index++;
                    }
                    Gdx.app.log("After process", String.format("Index:%d, isLooping: %b", index, isLooping));
                    return t;
                } else {
                    timeQueue.remove(index);
                    isLoopingQueue.remove(index);
                    if(index == timingQueue.size() - 1) {
                        index = 0;
                    }
                    Gdx.app.log("After process", String.format("Index:%d, isLooping: %b", index, isLooping));
                    return timingQueue.remove(index);
                }
            }
        }
        return null;
    }
}
