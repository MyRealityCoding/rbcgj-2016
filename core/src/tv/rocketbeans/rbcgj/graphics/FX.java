package tv.rocketbeans.rbcgj.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import tv.rocketbeans.rbcgj.tweens.SpriteTween;

public final class FX {

    static {
        Tween.registerAccessor(Sprite.class, new SpriteTween());
    }

    private static final FX INSTANCE = new FX();

    private TweenManager tweenManager;

    private ScreenShaker shake;

    private Sprite flash;

    private OrthographicCamera camera;

    private Color flashColor;

    private FX() {
        flash = new Sprite(GraphicsFactory.createTexture(2, 2, Color.WHITE));
        flash.setAlpha(0f);
        flashColor = Color.WHITE.cpy();
    }

    public static FX getInstance() {
        return INSTANCE;
    }

    public void setFadeColor(Color color) {
        flashColor = color.cpy();
    }

    public void begin() {
        if (camera != null && shake != null) {
            camera.translate(shake.getShake().x, shake.getShake().y);
        }
    }

    public void end() {
        if (camera != null && shake != null) {
            camera.translate(-shake.getShake().x, -shake.getShake().y);
        }
    }

    public void init(TweenManager tweenManager, OrthographicCamera camera) {
        this.tweenManager = tweenManager;
        shake = new ScreenShaker(this.tweenManager);
        this.camera = camera;
    }

    public void render(Batch batch, float delta) {
        flash.setPosition(camera.position.x - (camera.zoom * camera.viewportWidth) / 2, camera.position.y
                - (camera.zoom * camera.viewportHeight) / 2);
        flash.setSize(camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);
        flash.setColor(flashColor.r, flashColor.g, flashColor.b, flash.getColor().a);
        flash.draw(batch, 1f);
    }

    public void fadeIn(float duration, TweenEquation equation) {
        flash.setAlpha(1f);
        tweenManager.killTarget(flash);
        Tween.to(flash, SpriteTween.ALPHA, duration).target(0f).ease(equation).start(tweenManager);
    }

    public void fadeOut(float duration) {
        fadeOut(duration, TweenEquations.easeInQuad, null);
    }

    public void fadeOut(float duration, TweenEquation equation, TweenCallback callback) {
        flash.setAlpha(0f);
        tweenManager.killTarget(flash);

        Tween tween = Tween.to(flash, SpriteTween.ALPHA, duration).target(1f).ease(equation);
        if (callback != null) {
            tween.setCallback(callback).setCallbackTriggers(TweenCallback.COMPLETE);
        }
        tween.start(tweenManager);
    }

    public void fadeOutIn(float duration) {
        flash.setAlpha(0f);
        tweenManager.killTarget(flash);

        Tween tween = Tween.to(flash, SpriteTween.ALPHA, duration).target(1f).repeatYoyo(1, 0f).ease(TweenEquations.easeInOutQuad);
        tween.start(tweenManager);
    }

    public void fadeIn(float duration) {
        fadeIn(duration, TweenEquations.easeInQuad);
    }


    /**
     * Shakes the screen by the given intensity for the given duration
     *
     * @param intensity the intensity of the shake
     * @param duration the duration of the shake
     */
    public void shake(float intensity, float duration) {
        shake.shake(intensity, duration);
    }
}