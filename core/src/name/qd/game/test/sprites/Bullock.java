package name.qd.game.test.sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;
import name.qd.game.test.constant.CollisionType;

public class Bullock extends GameSprite {
    private World world;
    private Body body;
    private AssetManager assetManager;
    private Texture texture;

    public Bullock(World world) {
        assetManager = ResourceInstance.getInstance().getAssetManager();
        this.world = world;

        texture = assetManager.get("pic/sprite/cnormal.png", Texture.class);

        createBullockBody();
    }

    public void move(float x, float y) {
        body.setTransform(body.getPosition().x + x, body.getPosition().y + y, body.getAngle());
    }

    public void update(float delta) {
        setPosition(body.getPosition().x - (getWidth() / 2), body.getPosition().y - (getHeight() / 2));
    }

    private void createBullockBody() {
        int radius = 30;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(LibTest.WIDTH / 2, (radius * LibTest.SCALE_RATE) + (texture.getWidth() * LibTest.SCALE_RATE / 2));
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius * LibTest.SCALE_RATE);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = CollisionType.BULLOCK;
        fixtureDef.filter.maskBits = CollisionType.BULLOCK_BULLET;
        body.createFixture(fixtureDef).setUserData(this);

        setBounds(0, 0, texture.getWidth() * LibTest.SCALE_RATE, texture.getHeight() * LibTest.SCALE_RATE);
        setRegion(texture);
        setPosition((LibTest.WIDTH - texture.getWidth() * LibTest.SCALE_RATE) / 2, 30 * LibTest.SCALE_RATE);
    }
}
