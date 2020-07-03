package name.qd.game.test.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MaterialCreator {

    public static TextButton createTextButton(Texture btnOff, Texture btnOn, String text) {
        TextureAtlas atlas = new TextureAtlas();
        atlas.addRegion("off", btnOff, 0, 0, btnOff.getWidth(), btnOff.getHeight());
        atlas.addRegion("on", btnOn, 0, 0, btnOn.getWidth(), btnOn.getHeight());

        Skin skin = new Skin(atlas);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("off");
        style.down = skin.getDrawable("on");
        style.checked = skin.getDrawable("on");
        style.font = new BitmapFont();

        return new TextButton(text, style);
    }

    public static TextButton createTextButton(Texture texture, String text) {
        return createTextButton(texture, texture, text);
    }

    public static ImageButton createImageButton(Texture texture) {
        return createImageButton(texture, texture);
    }

    public static ImageButton createImageButton(Texture btnOff, Texture btnOn) {
        TextureAtlas atlas = new TextureAtlas();
        atlas.addRegion("off", btnOff, 0, 0, btnOff.getWidth(), btnOff.getHeight());
        atlas.addRegion("on", btnOn, 0, 0, btnOn.getWidth(), btnOn.getHeight());

        Skin skin = new Skin(atlas);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("off");
        style.down = skin.getDrawable("on");
        style.checked = skin.getDrawable("on");

        return new ImageButton(style);
    }

    public static Label.LabelStyle getDefaultLabelStyle(Color color, float scale) {
        BitmapFont font = getDefaultFont(scale);
        return new Label.LabelStyle(font, color);
    }

    public static BitmapFont getDefaultFont(float scale) {
        BitmapFont font = new BitmapFont();

        for(BitmapFont.Glyph[] glyphs : font.getData().glyphs) {
            if(glyphs != null) {
                for(BitmapFont.Glyph glyph : glyphs) {
                    if(glyph != null) {
                        Gdx.app.log(glyph.toString(), String.valueOf(glyph.width));

                        switch (glyph.toString()) {
                            case "O":
                            case "b":
                            case "e":
                            case "g":
                            case "n":
                                glyph.xadvance = 10;
                                break;
                            case "U":
                                glyph.xadvance = 12;
                                break;
                            case "i":
                                glyph.xadvance = 5;
                                break;
                            default:
                                glyph.xadvance = glyph.width;
                        }
                    }
                }
            }
        }

        font.getData().setScale(scale);

        return font;
    }
}
