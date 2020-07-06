package name.qd.game.test.sprite.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;

public class EnemyDef {
    private float startX;
    private float startY;
    private float velocityX;
    private float velocityY;
    private Animation animation;
    private Animation dead;
    private short categoryBits;
    private short maskBits;
    private float moveRange;

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
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

    public float getMoveRange() {
        return moveRange;
    }

    public void setMoveRange(float moveRange) {
        this.moveRange = moveRange;
    }
}
