/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gamestates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import com.jme3.input.controls.AnalogListener;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import java.util.logging.Logger;
import mygame.controls.MovementControl;

/**
 *
 * @author Joosep
 */
public class PlayerAppState extends AbstractAppState {
    
    private Camera cam;
    private InputManager input;
    
    private Node player;
    private MovementControl playerMovement;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        
        cam = app.getCamera();
        input = app.getInputManager();
        
        // Create player node
        player = new Node();
        playerMovement = new MovementControl();
        player.addControl(playerMovement);
        ((SimpleApplication) app).getRootNode().attachChild(player);
        
        // Add player input listeners
        input.addListener(new AnalogListener() {
            @Override
            public void onAnalog(String name, float value, float tpf) {
                switch (name) {
                    case "Forward":
                        playerMovement.moveForward();
                        break;
                    case "Turn Left":
                        playerMovement.turnLeft();
                        break;
                    case "Turn Right":
                        playerMovement.turnRight();
                        break;
                    default:
                        break;
                }
            }
        }, "Forward", "Turn Left", "Turn Right");
    }
    
    @Override
    public void update(float tpf) {
        
        // Update camera position
        cam.setLocation(player.getWorldTranslation());
        cam.setRotation(player.getWorldRotation());
    }
    
    @Override
    public void cleanup() {
        super.cleanup();
        //TODO: clean up what you initialized in the initialize method,
        //e.g. remove all spatials from rootNode
        //this is called on the OpenGL thread after the AppState has been detached
    }
    
}
