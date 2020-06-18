package name.qd.game.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import name.qd.game.test.screens.LogoScreen;
import name.qd.game.test.screens.MenuScreen;
import name.qd.game.test.screens.StartScreen;

public class LibTest extends Game {
	public static final float SCALE_RATE = 0.4f;
	public static final int WIDTH = (int)(720 * SCALE_RATE);
	public static final int HEIGHT = (int)(1280 * SCALE_RATE);

	private SpriteBatch batch;
	private AssetManager assetManager;

	private ResourceInstance resourceInstance = ResourceInstance.getInstance();

	@Override
	public void create () {
		batch = new SpriteBatch();

		initGame();

//		resourceInstance.setScreen(new LogoScreen());
//		resourceInstance.setScreen(new StartScreen());
		resourceInstance.setScreen(new MenuScreen());

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		assetManager.dispose();
	}

	private void initGame() {
		initAsset();

		resourceInstance.setGame(this);
		resourceInstance.setAssetManager(assetManager);
		resourceInstance.setSpriteBatch(batch);
	}

	private void initAsset() {
		assetManager = new AssetManager();

		assetManager.load("pic/background.png", Texture.class);
		assetManager.load("pic/stage.png", Texture.class);

		assetManager.load("pic/settingsbackground.png", Texture.class);

		assetManager.load("pic/btn/upgrade.png", Texture.class);
		assetManager.load("pic/btn/settings.png", Texture.class);
		assetManager.load("pic/btn/stageselect.png", Texture.class);
		assetManager.load("pic/btn/cancel.png", Texture.class);

		assetManager.finishLoading();
	}
}
