package tv.rocketbeans.rbcgj.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;

import tv.rocketbeans.rbcgj.NutGame;
import tv.rocketbeans.rbcgj.assets.Assets;
import tv.rocketbeans.rbcgj.core.GameObject;
import tv.rocketbeans.rbcgj.core.GameObjectType;
import tv.rocketbeans.rbcgj.graphics.SpriteRenderer;

public class IngameScreen extends AbstractScreen {

    public IngameScreen(NutGame game) {
        super(game);
    }

    @Override
    protected void onCreateStage(Stage stage, int width, int height) {
        super.onCreateStage(stage, width, height);
        GameObject eddy = world.addObject();
        eddy.setPosition(0f, 0f);
        eddy.setDimensions(64f, 64f);
        eddy.setType(GameObjectType.EDDY);
        world.registerRenderer(GameObjectType.EDDY, new SpriteRenderer(Assets.Textures.EDDY));
    }
}
