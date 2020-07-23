package name.qd.game.test.sprite.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.Random;

import name.qd.game.test.constant.Constants;
import name.qd.game.test.queue.EnemyActionQueue;
import name.qd.game.test.screen.GameScreen;

public class Enemy extends Sprite {
    private World world;
    private Body body;
    private Animation animation;
    private Animation dead;
    private Sprite currentFrame;

    private float scaleWidth;
    private float scaleHeight;

    private int hp;
    private float stateTime;
    private float lastFireTime;
    private float fireRate;
    private boolean shouldFire;
    private boolean isDestroyed;

    private Actor actor;
    private EnemyActionQueue enemyActionQueue;
    private EnemyDef enemyDef;

    public Enemy(World world, EnemyDef enemyDef) {
        this.world = world;
        this.enemyDef = enemyDef;
        actor = new Actor();
        actor.setPosition(enemyDef.getStartX(), enemyDef.getStartY());
        animation = enemyDef.getAnimation();
        dead = enemyDef.getDead();
        TextureRegion textureRegion = (TextureRegion) animation.getKeyFrame(0);
        scaleWidth = textureRegion.getRegionWidth() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER * enemyDef.getScale();
        scaleHeight = textureRegion.getRegionHeight() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER * enemyDef.getScale();
        fireRate = enemyDef.getFireRate();
        Random random = new Random();
        lastFireTime = (float)random.nextInt((int)fireRate * 10) / 10;
        enemyActionQueue = enemyDef.getEnemyActionQueue();
        hp = enemyDef.getHp();
        createBody();
    }

    private void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(enemyDef.getStartX(), enemyDef.getStartY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(enemyDef.getRadius() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = enemyDef.getCategoryBits();
        fixtureDef.filter.maskBits = enemyDef.getMaskBits();
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

        if(hp <= 0) {
            body.setActive(false);
            currentFrame = new Sprite((TextureRegion) dead.getKeyFrame(stateTime));
            if(stateTime >= 0.5f) {
                isDestroyed = true;
            }
        } else {
            currentFrame = (Sprite) animation.getKeyFrame(stateTime);
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

        if(body.getPosition().y < -scaleHeight) {
            isDestroyed = true;
        }
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(currentFrame, getX(), getY(), 0, 0, scaleWidth, scaleHeight, 1, 1, currentFrame.getRotation());
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
        actor.clear();
    }
}
