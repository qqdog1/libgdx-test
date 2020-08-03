package name.qd.game.test.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import name.qd.game.test.utils.ResourceInstance;

public abstract class Hud implements Disposable {
    protected SpriteBatch spriteBatch = ResourceInstance.getInstance().getSpriteBatch();

    @Override
    public abstract void dispose();
    public abstract void draw();
}
