package name.qd.game.test.sprite;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import name.qd.game.test.utils.ResourceInstance;

public class GameSprite extends Sprite {
    protected World world;
    protected Body body;
    protected AssetManager assetManager;

    public GameSprite(World world) {
        this.world = world;
        assetManager = ResourceInstance.getInstance().getAssetManager();
    }
}
