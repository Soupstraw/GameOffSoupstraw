package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import mygame.gamestates.LevelGeneratorState;
import mygame.gamestates.PlayerAppState;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setEnabled(false);
        
        // Create keyboard bindings
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Turn Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Turn Right", new KeyTrigger(KeyInput.KEY_D));
        
        // Attach states
        stateManager.attach(new LevelGeneratorState());
        stateManager.attach(new PlayerAppState());
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
