package name.qd.game.test.screens;

import name.qd.game.test.ResourceInstance;

public class IntroAnimationScreen extends GameScreen {
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        ResourceInstance.getInstance().setScreen(new MenuScreen());
        dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
