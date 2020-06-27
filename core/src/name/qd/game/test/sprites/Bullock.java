package name.qd.game.test.sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.LibTest;
import name.qd.game.test.ResourceInstance;

public class Bullock extends Sprite {
    public enum State {STAND, RIGHT, LEFT};
    private State currentState;

    private World world;
    private Body body;
    private AssetManager assetManager;
    private Texture tStand;
    private Texture tLeft;
    private Texture tRight;

    public Bullock(World world) {
        assetManager = ResourceInstance.getInstance().getAssetManager();
        this.world = world;

        tStand = assetManager.get("pic/sprite/cnormal.png", Texture.class);
        tLeft = assetManager.get("pic/sprite/cleft.png", Texture.class);
        tRight = assetManager.get("pic/sprite/cright.png", Texture.class);

        currentState = State.STAND;

        createBullockBody();
    }

    public void move(float x, float y) {
        if(x > 0) {
            setState(State.RIGHT);
        } else if(x < 0) {
            setState(State.LEFT);
        } else {
            setState(State.STAND);
        }

    }

    public void setState(State state) {
        currentState = state;
        switch (state) {
            case STAND:
                setRegion(tStand);
                break;
            case RIGHT:
                setRegion(tRight);
                break;
            case LEFT:
                setRegion(tLeft);
                break;
        }
    }

    private void createBullockBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((LibTest.WIDTH - tStand.getWidth() * LibTest.SCALE_RATE) / 2, 30 * LibTest.SCALE_RATE);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(30 * LibTest.SCALE_RATE);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData(this);

        setBounds(0, 0, tStand.getWidth() * LibTest.SCALE_RATE, tStand.getHeight() * LibTest.SCALE_RATE);
        setRegion(tStand);
        setPosition((LibTest.WIDTH - tStand.getWidth() * LibTest.SCALE_RATE) / 2, 30 * LibTest.SCALE_RATE);
    }
}
