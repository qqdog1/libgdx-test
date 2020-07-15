package name.qd.game.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import name.qd.game.test.constant.ScreenType;
import name.qd.game.test.screen.ScreenManager;
import name.qd.game.test.utils.PreferencesUtils;
import name.qd.game.test.utils.ResourceInstance;

public class LibTest extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;

	private ResourceInstance resourceInstance = ResourceInstance.getInstance();

	@Override
	public void create () {
		batch = new SpriteBatch();

		initGame();

		ScreenManager screenManager = ScreenManager.getInstance();

		if(PreferencesUtils.isLabelExist(PreferencesUtils.PreferencesEnum.SCREEN)) {
			String screenName = PreferencesUtils.getStringValue(PreferencesUtils.PreferencesEnum.SCREEN);
			ScreenType screenType = ScreenType.valueOf(screenName);
			resourceInstance.setScreen(screenManager.getScreen(screenType));
		} else {
			resourceInstance.setScreen(screenManager.getScreen(ScreenType.LOGO));
		}
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
		assetManager.load("pic/stagebackground.png", Texture.class);
		assetManager.load("pic/stage.png", Texture.class);

		assetManager.load("pic/board.png", Texture.class);
		assetManager.load("pic/star.png", Texture.class);
		assetManager.load("pic/starfilled.png", Texture.class);

		assetManager.load("pic/btn/upgrade.png", Texture.class);
		assetManager.load("pic/btn/settings.png", Texture.class);
		assetManager.load("pic/btn/stageselect.png", Texture.class);
		assetManager.load("pic/btn/stagedisable.png", Texture.class);
		assetManager.load("pic/btn/cancel.png", Texture.class);
		assetManager.load("pic/btn/selected.png", Texture.class);
		assetManager.load("pic/btn/unselected.png", Texture.class);
		assetManager.load("pic/btn/go.png", Texture.class);
		assetManager.load("pic/btn/return.png", Texture.class);

		assetManager.load("pic/sprite/cnormal.png", Texture.class);
		assetManager.load("pic/sprite/dead.png", Texture.class);
		assetManager.load("pic/sprite/bulletred.png", Texture.class);
		assetManager.load("pic/sprite/bulletgreen.png", Texture.class);
		assetManager.load("pic/sprite/bulletblue.png", Texture.class);
		assetManager.load("pic/sprite/pencil.png", Texture.class);
		assetManager.load("pic/sprite/ebullet.png", Texture.class);

		assetManager.finishLoading();
	}
}
