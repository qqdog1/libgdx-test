package name.qd.game.test.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.constant.CollisionType;
import name.qd.game.test.constant.Constants;
import name.qd.game.test.screen.GameScreen;

public class PowerUp extends GameSprite {
    private Texture texture;
    private boolean isDestroyed;
    private float startX;
    private float startY;

    public PowerUp(World world, float startX, float startY) {
        super(world);
        this.startX = startX;
        this.startY = startY;

        texture = assetManager.get("pic/sprite/powerup.png", Texture.class);
        createBody();
    }

    private void createBody() {
        float radius = 40f;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(startX, startY);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = CollisionType.POWER_UP;
        fixtureDef.filter.maskBits = CollisionType.BULLOCK;
        body.createFixture(fixtureDef).setUserData(this);

        setBounds(0, 0, texture.getWidth() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, texture.getHeight() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER);
        setRegion(texture);
        setPosition(startX, startY);
    }

    public void eat() {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void destroy() {
        world.destroyBody(body);
    }
}
