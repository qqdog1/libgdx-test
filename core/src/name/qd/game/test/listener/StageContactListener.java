package name.qd.game.test.listener;

import com.badlogic.gdx.Gdx;

import name.qd.game.test.sprites.GameSprite;

public class StageContactListener implements GameContactListener {
    @Override
    public void collide(GameSprite sprite1, GameSprite sprite2) {
        Gdx.app.log(sprite1.toString(), sprite2.toString());
    }
}
