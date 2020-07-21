package name.qd.game.test.sprite.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.constant.Constants;
import name.qd.game.test.screen.GameScreen;

public class Bullet extends Sprite {
    private Body body;
    private final World world;

    private final float scaleWidth;
    private final float scaleHeight;

    private boolean isDestroyed = false;

    public Bullet(World world, BulletDef bulletDef) {
        this.world = world;

        scaleWidth = bulletDef.getTexture().getWidth() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER;
        scaleHeight = bulletDef.getTexture().getHeight() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER;

        createBody(bulletDef);
    }

    private void createBody(BulletDef bulletDef) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(bulletDef.getStartX(), bulletDef.getStartY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.bullet = true;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(scaleWidth / 2, scaleHeight / 2);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = bulletDef.getCategoryBits();
        fixtureDef.filter.maskBits = bulletDef.getMaskBits();
        body.createFixture(fixtureDef).setUserData(this);

        body.setLinearVelocity(bulletDef.getVelocityX(), bulletDef.getVelocityY());

        setBounds(0, 0, scaleWidth, scaleHeight);
        setRegion(bulletDef.getTexture());
        setPosition(body.getPosition().x, body.getPosition().y);
    }

    public void update(float delta) {
        setPosition(body.getPosition().x - scaleWidth / 2, body.getPosition().y - scaleHeight / 2);
    }

    public void hit() {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void destroy() {
        world.destroyBody(body);
    }
}
