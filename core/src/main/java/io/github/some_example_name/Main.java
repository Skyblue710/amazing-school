package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Sprite sprite;
    private BitmapFont font;
    private float alpha = 1f;
    private boolean increasing = true;
    private OrthographicCamera camera;
    private Viewport viewport;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("st.png");
        sprite = new Sprite(image);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.setPosition(0, 0);

        font = new BitmapFont();
        font.setColor(Color.WHITE);

        int width = 800; // 固定宽度
        int height = 600; // 固定高度
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        viewport = new FitViewport(width, height, camera);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        batch.begin();
        float scale = 2; // 缩放比例，例如2表示字体大小加倍
        font.getData().setScale(scale); // 设置字体缩放
        float x = Gdx.graphics.getWidth() / 2;
        float y = Gdx.graphics.getHeight() / 2;
        font.setColor(new Color(1, 1, 1, alpha));
        font.draw(batch, "Click here to start the game", x, y);
        batch.end();

        if (increasing) {
            alpha += Gdx.graphics.getDeltaTime() * 2;
            if (alpha >= 1) {
                increasing = false;
            }
        } else {
            alpha -= Gdx.graphics.getDeltaTime() * 2;
            if (alpha <= 0) {
                increasing = true;
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
