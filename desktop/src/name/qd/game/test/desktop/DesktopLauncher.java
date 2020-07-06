package name.qd.game.test.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import name.qd.game.test.LibTest;
import name.qd.game.test.screen.GameScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)GameScreen.WIDTH;
		config.height = (int)GameScreen.HEIGHT;
		config.title = "LibTest";
		new LwjglApplication(new LibTest(), config);
	}
}
