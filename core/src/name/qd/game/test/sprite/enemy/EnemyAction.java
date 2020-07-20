package name.qd.game.test.sprite.enemy;

import com.badlogic.gdx.math.Interpolation;

public class EnemyAction {
    private float rangeX;
    private float rangeY;
    private float duration;
    private Interpolation interpolation;

    public EnemyAction(float rangeX, float rangeY, float duration, Interpolation interpolation) {
        this.rangeX = rangeX;
        this.rangeY = rangeY;
        this.duration = duration;
        this.interpolation = interpolation;
    }

    public float getRangeX() {
        return rangeX;
    }

    public float getRangeY() {
        return rangeY;
    }

    public float getDuration() {
        return duration;
    }

    public Interpolation getInterpolation() {
        return interpolation;
    }
}
