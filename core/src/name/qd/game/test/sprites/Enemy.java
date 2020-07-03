package name.qd.game.test.sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.constant.CollisionType;
import name.qd.game.test.constant.Constants;
import name.qd.game.test.screens.GameScreen;
import name.qd.game.test.utils.ResourceInstance;

public class Enemy extends Sprite {
    private World world;
    private Body body;
    private AssetManager assetManager;
    private Texture texture;

    private float x;
    private float y;

    private int hp = 3;

    public Enemy(World world, float x, float y) {
        assetManager = ResourceInstance.getInstance().getAssetManager();
        this.world = world;
        this.x = x;
        this.y = y;

        texture = assetManager.get("pic/sprite/enemy1.png", Texture.class);

        createBody();

        body.setLinearVelocity(0, -20);
    }

    private void createBody() {
        int radius = 25;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = CollisionType.ENEMY;
        fixtureDef.filter.maskBits = CollisionType.BULLOCK_BULLET;
        body.createFixture(fixtureDef).setUserData(this);

        setBounds(0, 0, texture.getWidth() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, texture.getHeight() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER);
        setRegion(texture);
        setPosition(body.getPosition().x, body.getPosition().y);
    }

    public void update(float delta) {
        setPosition(body.getPosition().x - (getWidth() / 2), body.getPosition().y - (getHeight() / 2));
    }

    public void onHit() {
        hp--;
        if(hp == 0) {

        }
    }

    public void destroy() {
        world.destroyBody(body);
    }
}
