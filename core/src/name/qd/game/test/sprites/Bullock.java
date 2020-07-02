package name.qd.game.test.sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.constant.Constants;
import name.qd.game.test.utils.ResourceInstance;
import name.qd.game.test.constant.CollisionType;
import name.qd.game.test.screens.GameScreen;

public class Bullock extends Sprite {
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
        int radius = 15;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(GameScreen.WIDTH / 2, (radius * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER) + (texture.getWidth() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER / 2));
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = CollisionType.BULLOCK;
        fixtureDef.filter.maskBits = CollisionType.BULLOCK_BULLET;
        body.createFixture(fixtureDef).setUserData(this);

        setBounds(0, 0, texture.getWidth() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, texture.getHeight() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER);
        setRegion(texture);
        setPosition((GameScreen.WIDTH - texture.getWidth() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER) / 2, 30 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER);
    }
}
