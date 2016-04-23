package tv.rocketbeans.rbcgj.graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import tv.rocketbeans.rbcgj.core.GameObject;

public class CameraTracker {

    private OrthographicCamera camera;

    private Vector2 velocity;

    private GameObject target;

    private float speed = 2.2f;

    private float zoomScale = 0.0025f;

    public CameraTracker(OrthographicCamera camera) {
        this.camera = camera;
        velocity = new Vector2();
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void update(float delta) {
        if (target == null) {
            return;
        }
        velocity.x = (float) (target.getLeft() + Math.floor(target.getWidth() / 2.0f) - (camera.position.x));
        velocity.y = (float) (target.getTop() + Math.floor(target.getHeight() / 2.0f) - (camera.position.y));

        float distance = velocity.len();
        velocity.nor();
        double overAllSpeed = distance * speed;

        // Round it up to prevent camera shaking
        camera.position.x = (float) (camera.position.x + (velocity.x * overAllSpeed * delta));
        camera.position.y = (float) (camera.position.y + (velocity.y * overAllSpeed * delta));
        camera.zoom = 0.7f;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setZoomScale(float zoomScale) {
        this.zoomScale = zoomScale;
    }

    public void focus() {
        if (target == null) {
            return;
        }
        camera.position.x = target.getLeft();
        camera.position.y = target.getTop();
        camera.zoom = 1;
    }
}