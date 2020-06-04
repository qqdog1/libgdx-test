package name.qd.game.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import name.qd.game.test.screens.LogoScreen;

public class LibTest extends Game {
	private static final double ZOOM_RATE = 0.5;
	public static final int WIDTH = (int)(720 * ZOOM_RATE);
	public static final int HEIGHT = (int)(1280 * ZOOM_RATE);

	private SpriteBatch batch;
	private AssetManager assetManager;

	private ResourceInstance resourceInstance = ResourceInstance.getInstance();

	@Override
	public void create () {
		batch = new SpriteBatch();

		initGame();

		resourceInstance.setScreen(new LogoScreen());
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

		assetManager.finishLoading();
	}
}
