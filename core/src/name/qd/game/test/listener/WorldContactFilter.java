package name.qd.game.test.listener;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;

import name.qd.game.test.constant.CollisionType;

public class WorldContactFilter implements ContactFilter {
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
        int collisionByte = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (collisionByte) {
            case CollisionType.BULLOCK_BULLET | CollisionType.ENEMY:
            case CollisionType.ENEMY_BULLET | CollisionType.BULLOCK:
            case CollisionType.BULLOCK | CollisionType.ENEMY:
                return true;
        }

        return false;
    }
}
