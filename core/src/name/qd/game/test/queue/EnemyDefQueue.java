package name.qd.game.test.queue;

import com.badlogic.gdx.assets.AssetManager;

import name.qd.game.test.sprite.enemy.EnemyDef;
import name.qd.game.test.utils.ResourceInstance;

public abstract class EnemyDefQueue extends TimingQueue<EnemyDef> {
    protected AssetManager assetManager = ResourceInstance.getInstance().getAssetManager();

    public EnemyDefQueue() {
        initData();
    }

    protected abstract void initData();
}
