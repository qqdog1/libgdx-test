package name.qd.game.test.screen;

import java.util.HashMap;
import java.util.Map;

import name.qd.game.test.constant.ScreenType;

public class ScreenManager {
    private static ScreenManager instance = new ScreenManager();

    private Map<ScreenType, GameScreen> map = new HashMap<>();

    public static ScreenManager getInstance() {
        return instance;
    }

    private ScreenManager() {
    }

    public GameScreen getScreen(ScreenType screenType) {
        if(!map.containsKey(screenType)) {
            switch (screenType) {
                case LOGO:
                    map.put(screenType, new LogoScreen());
                    break;
                case START:
                    map.put(screenType, new StartScreen());
                    break;
                case INTRO:
                    map.put(screenType, new IntroAnimationScreen());
                    break;
                case MENU:
                    map.put(screenType, new MenuScreen());
                    break;
                case UPGRADE:
                    map.put(screenType, new UpgradeScreen());
                    break;
                case L1:
                    map.put(screenType, new Stage1Screen());
                    break;
            }
        }
        return map.get(screenType);
    }

    public void closeScreen(ScreenType screenType) {
        switch (screenType) {
            case LOGO:
            case START:
            case INTRO:
            case L1:
                map.remove(screenType).dispose();
                break;
            // 常用的screen 留著instance
            case MENU:
            case UPGRADE:
                map.get(screenType).dispose();
                break;
        }
    }
}
