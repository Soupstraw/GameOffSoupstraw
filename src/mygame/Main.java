package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import mygame.gamestates.HUDState;
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
        app.setShowSettings(false);
        
        // Settings
        AppSettings settings = new AppSettings(true);
        settings.setFrameRate(60);
        settings.setResolution(640, 480);
        settings.setGammaCorrection(false);
        settings.setBitsPerPixel(16);
        app.setSettings(settings); 
        
        app.start();
    }

    @Override
    public void simpleInitApp() {
        // Disale flycam
        flyCam.setEnabled(false);
        
        // Create keyboard bindings
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Turn Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Turn Right", new KeyTrigger(KeyInput.KEY_D));
        
        // Attach states
        stateManager.attach(new LevelGeneratorState());
        stateManager.attach(new PlayerAppState());
        stateManager.attach(new HUDState());
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
