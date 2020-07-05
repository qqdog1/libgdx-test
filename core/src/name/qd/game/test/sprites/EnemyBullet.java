package name.qd.game.test.sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.constant.CollisionType;
import name.qd.game.test.constant.Constants;
import name.qd.game.test.screens.GameScreen;
import name.qd.game.test.utils.ResourceInstance;

public class EnemyBullet extends Sprite {
    private Body body;
    private World world;
    private Texture texture;
    private AssetManager assetManager;

    private float scaleWidth;
    private float scaleHeight;
    private float speed = -20;

    private boolean isDestroyed = false;

    public EnemyBullet(World world, float x, float y) {
        this.world = world;
        assetManager = ResourceInstance.getInstance().getAssetManager();
        texture = assetManager.get("pic/sprite/ebullet.png", Texture.class);

        scaleWidth = texture.getWidth() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER;
        scaleHeight = texture.getHeight() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER;

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
        fixtureDef.filter.categoryBits = CollisionType.ENEMY_BULLET;
        fixtureDef.filter.maskBits = CollisionType.BULLOCK;
        body.createFixture(fixtureDef).setUserData(this);

        body.setLinearVelocity(0, speed);

        setBounds(0, 0, scaleWidth, scaleHeight);
        setRegion(texture);
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