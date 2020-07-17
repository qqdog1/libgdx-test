package name.qd.game.test.sprite.enemy;

import com.badlogic.gdx.math.Vector2;

import name.qd.game.test.constant.EnemyActionType;

public class EnemyAction {
    private EnemyActionType enemyActionType;

    private float distance;
    private float velocityX;
    private float radius;
    private float angle;

    public EnemyAction() {
    }

    public void setLineMove(float velocityX, float velocityY, float distance) {
        enemyActionType = EnemyActionType.LINE;
        this.distance = distance;
    }

    public void setClockwiseMove(float radius, float angle, float duration) {
        enemyActionType = EnemyActionType.CLOCKWISE;
        this.radius = radius;
        this.angle = angle;
    }

    public void setCounterClosewiseMove(float radius, float angle, float duration) {
        enemyActionType = EnemyActionType.COUNTERCLOCKWISE;
        this.radius = radius;
        this.angle = angle;
    }

    public EnemyActionType getEnemyActionType() {
        return enemyActionType;
    }

    public void setEnemyActionType(EnemyActionType enemyActionType) {
        this.enemyActionType = enemyActionType;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
