package de.jan5366x.rocker.bogie.rover.desktop.remote.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;


import de.jan5366x.rocker.bogie.rover.desktop.remote.shared.screen.MainScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoteControlAdapter extends ApplicationAdapter {
    private static final Logger LOGGER = LogManager.getLogger(RemoteControlAdapter.class);

    private final String[] args;

    private Screen screen;

    public RemoteControlAdapter(String[] args) {
        this.args = args;
    }

    @Override
    public void create() {
        screen = new MainScreen();
    }

    @Override
    public void render() {
        try {
            ScreenUtils.clear(0.1F, 0.1F, 0.18F, 1F);
            screen.render(Gdx.graphics.getDeltaTime());
        } catch (Exception e) {
            LOGGER.error("Critical error in render loop", e);
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}
