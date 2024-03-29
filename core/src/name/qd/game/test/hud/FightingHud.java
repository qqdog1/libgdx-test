package name.qd.game.test.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import name.qd.game.test.screen.GameScreen;

public class FightingHud extends Hud {
    private Stage stage;
    private Viewport viewport;
    private int money;
    private int hp;
    private Texture live;
    private Table liveTable;
    private Label lblMoney;

    public FightingHud(int hp) {
        this.hp = hp;

        viewport = new FitViewport(GameScreen.WIDTH, GameScreen.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        live = new Texture("pic/live.png");

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        table.bottom();
        table.right();

        liveTable = new Table();
        table.add(liveTable);
        liveTable.left();
        addLives();

        lblMoney = new Label(String.valueOf(money), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(lblMoney).padRight(0);

        stage.addActor(table);
    }

    @Override
    public void draw() {
        stage.draw();
    }

    public void setHp(int hp) {
        this.hp = hp;
        addLives();
    }

    private void addLives() {
        liveTable.clear();
        for(int i = 0 ; i < hp ; i++) {
            liveTable.add(new Image(live));
        }
    }

    public void addMoney(int money) {
        this.money += money;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
