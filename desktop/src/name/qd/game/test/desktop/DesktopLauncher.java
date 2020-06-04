package name.qd.game.test.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import name.qd.game.test.LibTest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = LibTest.WIDTH;
		config.height = LibTest.HEIGHT;
		config.title = "LibTest";
		new LwjglApplication(new LibTest(), config);
	}
}
