package name.qd.game.test.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;
import name.qd.game.test.constant.BulletType;
import name.qd.game.test.constant.CollisionType;

public class Bullet extends GameSprite {
    private Body body;
    private World world;
    private Texture texture;
    private AssetManager assetManager;

    private float scaleWidth;
    private float scaleHeight;
    private float speed = 100000;

    public Bullet(World world, BulletType bulletType, float x, float y) {
        this.world = world;
        assetManager = ResourceInstance.getInstance().getAssetManager();

        switch (bulletType) {
            case BULLOCK_RED:
                texture = assetManager.get("pic/sprite/bulletred.png", Texture.class);
                break;
            case BULLOCK_BLUE:
                texture = assetManager.get("pic/sprite/bulletblue.png", Texture.class);
                break;
            case BULLOCK_GREEN:
                texture = assetManager.get("pic/sprite/bulletgreen.png", Texture.class);
                break;
        }
        scaleWidth = texture.getWidth() * LibTest.SCALE_RATE;
        scaleHeight = texture.getHeight() * LibTest.SCALE_RATE;

        createBody(x, y);
    }

    private void createBody(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.bullet = true;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(scaleWidth / 2, scaleHeight / 2);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = CollisionType.BULLOCK_BULLET;
        fixtureDef.filter.maskBits = CollisionType.BULLOCK;
        body.createFixture(fixtureDef).setUserData(this);

        body.setLinearVelocity(0, speed * LibTest.SCALE_RATE);

        setBounds(0, 0, scaleWidth, scaleHeight);
        setRegion(texture);
        setPosition(body.getPosition().x, body.getPosition().y);
    }

    public void update(float delta) {
        setPosition(body.getPosition().x - scaleWidth / 2, body.getPosition().y - scaleHeight / 2);
    }

    public void destroy() {
        world.destroyBody(body);
    }
}
