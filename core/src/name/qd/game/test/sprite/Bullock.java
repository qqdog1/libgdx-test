package name.qd.game.test.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.constant.CollisionType;
import name.qd.game.test.constant.Constants;
import name.qd.game.test.constant.HPLevel;
import name.qd.game.test.constant.PreferencesEnum;
import name.qd.game.test.constant.WeaponLevel;
import name.qd.game.test.screen.GameScreen;
import name.qd.game.test.utils.PreferencesUtils;

public class Bullock extends GameSprite {
    private Texture texture;
    private Texture dead;
    private boolean isDestroyed;
    private int weaponLevel;
    private int damage;
    private int hpLevel;
    private int hp;

    private float stateTime;
    private float lastFireTime;
    private float lastOnHitTime;
    private float fireRate;
    private boolean shouldFire;

    public Bullock(World world) {
        super(world);

        texture = assetManager.get("pic/sprite/cnormal.png", Texture.class);
        dead = assetManager.get("pic/sprite/dead.png", Texture.class);

        // TODO can upgrade
        fireRate = 0.5f;
        shouldFire = false;
        weaponLevel = 0;
        if(PreferencesUtils.isLabelExist(PreferencesEnum.WEAPON)) {
            weaponLevel = PreferencesUtils.getIntValue(PreferencesEnum.WEAPON);
        }
        damage = WeaponLevel.getDamage(weaponLevel);
        hpLevel = 0;
        if(PreferencesUtils.isLabelExist(PreferencesEnum.HP)) {
            hpLevel = PreferencesUtils.getIntValue(PreferencesEnum.HP);
        }
        hp = HPLevel.getHp(hpLevel);

        createBullockBody();
    }

    public void move(float x, float y) {
        if(hp == 0) return;
        float lastX;
        float lastY;
        if(body.getPosition().x + x <= 0) {
            lastX = 0;
        } else if(body.getPosition().x + x >= GameScreen.SCREEN_WIDTH) {
            lastX = GameScreen.SCREEN_WIDTH;
        } else {
            lastX = body.getPosition().x + x;
        }

        if(body.getPosition().y + y <= 0) {
            lastY = 0;
        } else if(body.getPosition().y + y >= GameScreen.SCREEN_HEIGHT) {
            lastY = GameScreen.SCREEN_HEIGHT;
        } else {
            lastY = body.getPosition().y + y;
        }

        body.setTransform(lastX, lastY, body.getAngle());
    }

    public void update(float delta) {
        stateTime += delta;
        setPosition(body.getPosition().x - (getWidth() / 2), body.getPosition().y - (getHeight() / 2));
        body.setLinearVelocity(0, 0);
        if(hp == 0) {
            isDestroyed = true;
            shouldFire = false;
            setRegion(dead);
        } else {
            if(stateTime >= lastFireTime + fireRate) {
                shouldFire = true;
                lastFireTime = stateTime;
            }
        }
    }

    public boolean isShouldFire() {
        if(shouldFire) {
            shouldFire = false;
            return true;
        }
        return false;
    }

    private void createBullockBody() {
        float radius = 15f;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(GameScreen.SCREEN_WIDTH / 2, (radius * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER) + (texture.getWidth() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER / 2));
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
        setPosition(body.getPosition().x, body.getPosition().y);
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void onHit() {
        if(hp == 0) return;
        lastOnHitTime = stateTime;
        hp--;
    }

    public boolean isOnHit() {
        if(stateTime > 0.2f && stateTime - lastOnHitTime < 0.2f) {
            return true;
        }
        return false;
    }

    public int getHp() {
        return hp;
    }

    public void powerUp() {
        hp++;
    }
}
