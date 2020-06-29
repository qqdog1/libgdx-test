package name.qd.game.test.listener;

import name.qd.game.test.sprites.GameSprite;

public interface GameContactListener {
    public void collide(GameSprite sprite1, GameSprite sprite2);
}
