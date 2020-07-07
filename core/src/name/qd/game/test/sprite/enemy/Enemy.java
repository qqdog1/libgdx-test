package name.qd.game.test.sprite.enemy;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.constant.CollisionType;
import name.qd.game.test.constant.Constants;
import name.qd.game.test.screen.GameScreen;
import name.qd.game.test.utils.ResourceInstance;

public class Enemy extends Sprite {
    private World world;
    private Body body;
    private Animation animation;
    private Animation dead;

    private float x;
    private float y;
    private float scaleWidth;
    private float scaleHeight;

    private float moveRange = 80 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER;

    private int hp = 3;
    private float stateTime;
    private float lastFireTime;
    private float fireRate;
    private boolean shouldFire;
    private boolean isDestroyed;

    public Enemy(World world, EnemyDef enemyDef) {
        this.world = world;
        this.x = enemyDef.getStartX();
        this.y = enemyDef.getStartY();
        animation = enemyDef.getAnimation();
        dead = enemyDef.getDead();
        scaleWidth = 54 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER;
        scaleHeight = 104 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER;

        fireRate = 1f;

        createBody(enemyDef);
    }

    private void createBody(EnemyDef enemyDef) {
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

        MassData massData = new MassData();
        massData.mass = 99999;
        body.setMassData(massData);

        setBounds(0, 0, scaleWidth, scaleHeight);
        setRegion((TextureRegion) animation.getKeyFrame(0));
        setPosition(body.getPosition().x, body.getPosition().y);

        body.setLinearVelocity(enemyDef.getVelocityX(), enemyDef.getVelocityY());
    }

    public boolean isShouldFire() {
        if(shouldFire) {
            shouldFire = false;
            return true;
        }
        return false;
    }

    public void update(float delta) {
        stateTime += delta;
        setPosition(body.getPosition().x - (getWidth() / 2), body.getPosition().y - (getHeight() / 2));

        if(y - body.getPosition().y >= moveRange) {
            body.setLinearVelocity(0, 0);
        }
        setRegion((TextureRegion) animation.getKeyFrame(stateTime));

        if(hp <= 0) {
            body.setActive(false);
            setRegion((TextureRegion) dead.getKeyFrame(stateTime));
            if(stateTime >= 0.5f) {
                isDestroyed = true;
            }
        } else {
            if(stateTime >= lastFireTime + fireRate) {
                shouldFire = true;
                lastFireTime = stateTime;
            }
        }
    }

    public void onHit() {
        hp--;

        if(hp == 0) {
            stateTime = 0;
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void destroy() {
        world.destroyBody(body);
    }
}
