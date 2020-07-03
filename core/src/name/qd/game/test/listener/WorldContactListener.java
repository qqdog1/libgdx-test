package name.qd.game.test.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import name.qd.game.test.constant.CollisionType;
import name.qd.game.test.sprites.Bullet;
import name.qd.game.test.sprites.Enemy;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int collisionByte = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (collisionByte) {
            case CollisionType.BULLOCK_BULLET | CollisionType.ENEMY:
                if(fixtureA.getFilterData().categoryBits == CollisionType.BULLOCK_BULLET) {
                    ((Bullet)fixtureA.getUserData()).hit();
                    ((Enemy)fixtureB.getUserData()).onHit();
                } else {
                    ((Bullet)fixtureB.getUserData()).hit();
                    ((Enemy)fixtureA.getUserData()).onHit();
                }
                break;
            case CollisionType.ENEMY_BULLET | CollisionType.BULLOCK:
                break;
            case CollisionType.BULLOCK | CollisionType.ENEMY:
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
