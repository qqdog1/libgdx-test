package name.qd.game.test.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;

import name.qd.game.test.constant.CollisionType;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int collisionByte = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (collisionByte) {
            case CollisionType.BULLOCK | CollisionType.BULLOCK_BULLET:
                Gdx.app.log("BB", "BB");
                if(fixtureA.getFilterData().categoryBits == CollisionType.BULLOCK_BULLET) {
                } else {

                }
                break;

        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
