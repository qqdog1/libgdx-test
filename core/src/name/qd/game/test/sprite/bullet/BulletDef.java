package name.qd.game.test.sprite.bullet;

import com.badlogic.gdx.graphics.Texture;

public class BulletDef {
    private float startX;
    private float startY;
    private float velocityX;
    private float velocityY;
    private Texture texture;
    private short categoryBits;
    private short maskBits;

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

    public void setVelocity(float x, float y) {
        this.velocityX = x;
        this.velocityY = y;
    }

    public float getVelocityX() {
        return velocityX;
    }
    public float getVelocityY() {
        return velocityY;
    }

    public Texture getTexture() {
        return texture;
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
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
}
