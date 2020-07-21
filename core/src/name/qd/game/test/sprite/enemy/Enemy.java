package name.qd.game.test.sprite.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import name.qd.game.test.constant.CollisionType;
import name.qd.game.test.constant.Constants;
import name.qd.game.test.queue.EnemyActionQueue;
import name.qd.game.test.screen.GameScreen;

public class Enemy extends Sprite {
    private World world;
    private Body body;
    private Animation animation;
    private Animation dead;

    private float x;
    private float y;
    private float scaleWidth;
    private float scaleHeight;

    private int hp = 3;
    private float stateTime;
    private float lastFireTime;
    private float fireRate;
    private boolean shouldFire;
    private boolean isDestroyed;
    private float radius;

    private Actor actor;
    private EnemyActionQueue enemyActionQueue;

    public Enemy(World world, EnemyDef enemyDef) {
        this.world = world;
        this.x = enemyDef.getStartX();
        this.y = enemyDef.getStartY();
        actor = new Actor();
        actor.setPosition(x, y);
        animation = enemyDef.getAnimation();
        dead = enemyDef.getDead();
        scaleWidth = 54 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER;
        scaleHeight = 104 * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER;
        radius = enemyDef.getRadius();
        fireRate = enemyDef.getFireRate();
        enemyActionQueue = enemyDef.getEnemyActionQueue();
        createBody();
    }

    private void createBody() {
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
        TextureRegion currentFrame;

        if(hp <= 0) {
            body.setActive(false);
            currentFrame = (TextureRegion) dead.getKeyFrame(stateTime);
            if(stateTime >= 0.5f) {
                isDestroyed = true;
            }
        } else {
            currentFrame = (TextureRegion) animation.getKeyFrame(stateTime);
            if(stateTime >= lastFireTime + fireRate) {
                shouldFire = true;
                lastFireTime = stateTime;
            }

            EnemyAction enemyAction = enemyActionQueue.getNext(delta);
            if(enemyAction != null) {
                float moveToX = body.getPosition().x + enemyAction.getRangeX();
                float moveToY = body.getPosition().y + enemyAction.getRangeY();
                actor.addAction(Actions.moveTo(moveToX, moveToY, enemyAction.getDuration(), enemyAction.getInterpolation()));
            }
            actor.act(delta);
            body.setTransform(actor.getX(), actor.getY(), 0);
            setPosition(body.getPosition().x - (getWidth() / 2), body.getPosition().y - (getHeight() / 2));
        }
        setRegion(currentFrame);
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
