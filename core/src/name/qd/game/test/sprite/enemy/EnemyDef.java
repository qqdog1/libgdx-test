package name.qd.game.test.sprite.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;

import name.qd.game.test.queue.EnemyActionQueue;

public class EnemyDef {
    private float startX;
    private float startY;
    private Animation animation;
    private Animation dead;
    private short categoryBits;
    private short maskBits;
    private float fireRate;
    private float radius;
    private EnemyActionQueue enemyActionQueue;

    public void setStartPosition(float x, float y) {
        this.startX = x;
        this.startY = y;
    }
    public float getStartX() {
        return startX;
    }
    public float getStartY() {
        return startY;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Animation getDead() {
        return dead;
    }

    public void setDead(Animation dead) {
        this.dead = dead;
    }

    public short getCategoryBits() {
        return categoryBits;
    }

    public void setCategoryBits(short categoryBits) {
        this.categoryBits = categoryBits;
    }

    public short getMaskBits() {
        return maskBits;
    }

    public void setMaskBits(short maskBits) {
        this.maskBits = maskBits;
    }

    public float getFireRate() {
        return fireRate;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public EnemyActionQueue getEnemyActionQueue() {
        return enemyActionQueue;
    }

    public void setEnemyActionQueue(EnemyActionQueue enemyActionQueue) {
        this.enemyActionQueue = enemyActionQueue;
    }
}
