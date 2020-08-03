package name.qd.game.test.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import name.qd.game.test.constant.Constants;
import name.qd.game.test.screen.GameScreen;
import name.qd.game.test.utils.MaterialCreator;
import name.qd.game.test.utils.ResourceInstance;

public class SettlementHud extends Hud {
    private AssetManager assetManager;
    private Stage stage;
    private Viewport viewport;
    private Label lblMoney;
    private boolean isClickFinish;

    public SettlementHud(int money) {
        assetManager = ResourceInstance.getInstance().getAssetManager();

        viewport = new FitViewport(GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        lblMoney = new Label(String.format("%05d", money), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        ImageButton btnGo = MaterialCreator.createImageButton(assetManager.get("pic/btn/go.png", Texture.class));
        btnGo.setTouchable(Touchable.enabled);
        btnGo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isClickFinish = true;
                Gdx.app.log("Btn", "Go");
            }
        });

        Texture texture = assetManager.get("pic/board.png", Texture.class);
        Table table = new Table();
        table.setTouchable(Touchable.enabled);
        table.setDebug(true);
        table.setY((GameScreen.SCREEN_HEIGHT - (texture.getHeight() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)) / 2);
        table.setSize(texture.getWidth() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER, texture.getHeight() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER);
        table.setBackground(new TextureRegionDrawable(texture));
        table.top();
//        table.add(lblMoney).padTop(20);
        table.row();
        table.add(btnGo)
                .width(btnGo.getWidth() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER)
                .height(btnGo.getHeight() * GameScreen.SCALE_RATE / Constants.PIXEL_PER_METER);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);
    }

    @Override
    public void draw() {
        stage.draw();
    }

    public boolean isClickFinish() {
        return isClickFinish;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
