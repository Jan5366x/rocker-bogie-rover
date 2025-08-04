package de.jan5366x.rocker.bogie.rover.desktop.remote.shared.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.jan5366x.rocker.bogie.rover.desktop.remote.core.RoverMath;
import de.jan5366x.rocker.bogie.rover.desktop.remote.shared.Box;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class MainScreen implements InputProcessor, Screen {
    private static final Logger LOGGER = LogManager.getLogger(MainScreen.class);
    @Getter
    private final BitmapFont font;
    @Getter
    private final Stage stage;
    @Getter
    private final Viewport viewport;
    @Getter
    private final Skin skin;
    private SpriteBatch batch;
    private Texture background;
    private final OrthographicCamera camera;
    private final ShapeRenderer shapeRenderer;
    private final InputMultiplexer inputMultiplexer = new InputMultiplexer();
    private final ArrayList<Box> boxes = new ArrayList<>();

    private Table table;
    Touchpad touchpad;

    public MainScreen() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("ui/game-ui.json"));

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(true); //  enables debug lines

        TextButton button = new TextButton("Text Button", skin);
        table.addActor(button);
        touchpad = new Touchpad(20, skin);
        touchpad.setBounds(15, 15, 200, 200);
        stage.addListener(new InputListener() {

            Vector2 p = new Vector2();
            Rectangle b = new Rectangle();

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (event.getTarget() != touchpad) {
                    // If we didn't actually touch the touchpad, set position to our touch point
                    b.set(touchpad.getX(), touchpad.getY(), touchpad.getWidth(), touchpad.getHeight());
                    b.setCenter(x, y);
                    touchpad.setBounds(b.x, b.y, b.width, b.height);

                    // Let the touchpad know to start tracking touch
                    touchpad.fire(event);
                }
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                touchpad.stageToLocalCoordinates(p.set(x, y));

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Put the touchpad back to its original position
                touchpad.clearActions();
            }
        });
        stage.addActor(touchpad);

        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();

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

        // Test boxes
        boxes.add(new Box(100.0F, 100.0F, 30.0F, 60.0F, -30.0F, Color.CYAN));
        boxes.add(new Box(100.0F, 250.0F, 30.0F, 60.0F, 0.0F, Color.CYAN));
        boxes.add(new Box(100.0F, 400.0F, 30.0F, 60.0F, 30.0F, Color.CYAN));
        boxes.add(new Box(350.0F, 100.0F, 30.0F, 60.0F, -30.0F, Color.CYAN));
        boxes.add(new Box(350.0F, 250.0F, 30.0F, 60.0F, 0.0F, Color.CYAN));
        boxes.add(new Box(350.0F, 400.0F, 30.0F, 60.0F, 30.0F, Color.CYAN));
        boxes.add(new Box(150.0F, 140.0F, 180.0F, 300.0F, 0.0F, Color.CYAN));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, viewport.getScreenWidth(), viewport.getScreenHeight());
        font.draw(batch, Gdx.graphics.getFramesPerSecond() + " FPS; Delta " + delta * 1000 + "ms", 0, viewport.getScreenHeight());
        batch.end();

        Vector2 p1 = new Vector2(110, 100);
        Vector2 p2 = new Vector2(120, 250);
        Vector2 p3 = new Vector2( 110, 400);

        var c1 = RoverMath.circleFromThreePoints(p1, p2, p3);

        Vector2 p4 = new Vector2(110 + 250, 100);
        Vector2 p5 = new Vector2(120 + 250, 250);
        Vector2 p6 = new Vector2( 110 + 250, 400);

        var c2 = RoverMath.circleFromThreePoints(p4, p5, p6);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.circle(c1.a().x, c1.a().y, c1.b());
        shapeRenderer.circle(c2.a().x, c2.a().y, c2.b());
        shapeRenderer.end();

        for (Box box : boxes) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(box.color());
            // public void rect(float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float degrees) {
            shapeRenderer.rect(box.x(), box.y(), box.width() / 2.0F, box.height() / 2.0F, box.width(), box.height(), 1.0F, 1.0F, box.rotation());
            shapeRenderer.end();
        }

        stage.act(Gdx.graphics.getDeltaTime());
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
        shapeRenderer.dispose();
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
