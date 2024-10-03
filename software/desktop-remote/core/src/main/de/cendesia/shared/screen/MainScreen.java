package de.cendesia.shared.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainScreen implements InputProcessor, Screen {
    private static final Logger LOGGER = LogManager.getLogger(MainScreen.class);
    @Getter
    private BitmapFont font;
    @Getter
    private final Stage stage;
    @Getter
    private final Viewport viewport;
    @Getter
    private Skin skin;
    private SpriteBatch batch;
    private Texture background;


    private final InputMultiplexer inputMultiplexer = new InputMultiplexer();


    public MainScreen() {

        viewport = new ScreenViewport();
        stage = new Stage(viewport);

        skin = new Skin(Gdx.files.internal("ui/game-ui.json"));


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/fonts/cascadia/CaskaydiaCoveNerdFont-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 14;
        parameter.borderWidth = 0;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.WHITE;
        parameter.hinting = FreeTypeFontGenerator.Hinting.AutoMedium;
        parameter.kerning = true;
        parameter.shadowOffsetX = 0;
        parameter.shadowOffsetY = 0;
        parameter.shadowColor = Color.CLEAR;
        parameter.incremental = true; // => Do not dispose!
        font = generator.generateFont(parameter);

        skin.get(Label.LabelStyle.class).font = font;
        skin.get(TextButton.TextButtonStyle.class).font = font;
        skin.get(SelectBox.SelectBoxStyle.class).font = font;
        skin.get(List.ListStyle.class).font = font;
        skin.get(TextField.TextFieldStyle.class).font = font;
        skin.get(Window.WindowStyle.class).titleFont = font;

        Gdx.input.setInputProcessor(stage);

        createBackground();

        // begin layout
        var root = new Table();
        root.setSize(300, 500);
        stage.addActor(root);

        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, viewport.getScreenWidth(), viewport.getScreenHeight());
        font.draw(batch, Gdx.graphics.getFramesPerSecond() + " FPS; Delta " + delta * 1000 + "ms", 0, viewport.getScreenHeight());
        batch.end();



        stage.act();
        stage.draw();
    }

    private void createBackground() {
        batch = new SpriteBatch();
        background = new Texture("textures/background.jpg");
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        batch.dispose();
        background.dispose();
        stage.dispose();
        skin.dispose();
        font.dispose();
    }



    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
