package name.qd.game.test.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MaterialCreator {

    public static TextButton createButton(Texture btnOff, Texture btnOn) {
        TextureAtlas atlas = new TextureAtlas();
        atlas.addRegion("off", btnOff, 0, 0, btnOff.getWidth(), btnOff.getHeight());
        atlas.addRegion("on", btnOn, 0, 0, btnOn.getWidth(), btnOn.getHeight());

        Skin skin = new Skin(atlas);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("off");
        style.down = skin.getDrawable("on");
        style.font = new BitmapFont();

        return new TextButton("", style);
    }

    public static TextButton createButton(Texture texture) {
        return createButton(texture, texture);
    }
}
