package tv.rocketbeans.rbcgj.tweens;

import aurelienribon.tweenengine.TweenAccessor;
import tv.rocketbeans.rbcgj.core.GameObject;

public class GameObjectTween implements TweenAccessor<GameObject> {

    public static final int SCALE_X = 100;
    public static final int SCALE_Y = 102;
    public static final int POS_X = 103;
    public static final int POS_Y = 104;
    public static final int SCALE = 105;
    public static final int OFFSET_X = 106;
    public static final int OFFSET_Y = 107;
    public static final int ALPHA = ColorTween.A;
    public static final int R = ColorTween.R;
    public static final int G = ColorTween.G;
    public static final int B = ColorTween.B;

    private ColorTween colorTween = new ColorTween();

    @Override
    public int getValues(GameObject target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case SCALE:
                returnValues[0] = target.getScale().x;
                return 1;
            case SCALE_X:
                returnValues[0] = target.getScale().x;
                return 1;
            case SCALE_Y:
                returnValues[0] = target.getScale().y;
                return 1;
            case POS_X:
                returnValues[0] = target.getLeft();
                return 1;
            case POS_Y:
                returnValues[0] = target.getTop();
                return 1;
            case OFFSET_X:
                returnValues[0] = target.getOffset().x;
                return 1;
            case OFFSET_Y:
                returnValues[0] = target.getOffset().y;
                return 1;
            case ALPHA: case R:
            case G: case B:
                return colorTween.getValues(target.getColor(), tweenType, returnValues);
        }
        return 0;
    }

    @Override
    public void setValues(GameObject target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case SCALE:
                target.getScale().x = newValues[0];
                target.getScale().y = newValues[0];
                break;
            case SCALE_X:
                target.getScale().x = newValues[0];
                break;
            case SCALE_Y:
                target.getScale().y = newValues[0];
                break;
            case POS_X:
                target.setPosition(newValues[0], target.getTop());
                break;
            case POS_Y:
                target.setPosition(target.getLeft(), newValues[0]);
                break;
            case ALPHA: case R:
            case G: case B:
                colorTween.setValues(target.getColor(), tweenType, newValues);
                break;
            case OFFSET_X:
                target.setOffset(newValues[0], target.getOffset().y);
                break;
            case OFFSET_Y:
                target.setOffset(target.getOffset().x, newValues[0]);
                break;
        }
    }

}