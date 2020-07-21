package name.qd.game.test.utils;

import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.HashMap;
import java.util.Map;

public class AnimationCacheManager {
    private static AnimationCacheManager instance = new AnimationCacheManager();

    private Map<String, Animation> map = new HashMap<>();

    public AnimationCacheManager getInstance() {
        return instance;
    }

    private AnimationCacheManager() {
    }

    public void put(String key, Animation animation) {
        map.put(key, animation);
    }

    public Animation getAnimation(String key) {
        return map.get(key);
    }

    public void clear() {
        map.clear();
    }
}
