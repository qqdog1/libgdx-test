package name.qd.game.test.listener;

import com.badlogic.gdx.math.Intersector;

import java.util.LinkedList;
import java.util.List;

import name.qd.game.test.sprites.GameSprite;

public class CollisionDetector {
    private List<GameSprite> lstEnemySide = new LinkedList<>();
    private List<GameSprite> lstBullockSide = new LinkedList<>();
    private GameContactListener listener;
    private int i = 0;

    public CollisionDetector(GameContactListener listener) {
        this.listener = listener;
    }

    public void addEnemySide(GameSprite sprite) {
        lstEnemySide.add(sprite);
    }

    public void addBullockSide(GameSprite sprite) {
        lstBullockSide.add(sprite);
    }

    public void removeEnemySide(GameSprite sprite) {
        lstEnemySide.remove(sprite);
    }

    public void removeBullockSide(GameSprite sprite) {
        lstBullockSide.remove(sprite);
    }

    public void update() {
        for(GameSprite enemy : lstEnemySide) {
            for(GameSprite bullock : lstBullockSide) {
                if(Intersector.overlaps(enemy.getBoundingRectangle(), bullock.getBoundingRectangle())) {
                    listener.collide(bullock, enemy);
                }
            }
        }
    }
}
