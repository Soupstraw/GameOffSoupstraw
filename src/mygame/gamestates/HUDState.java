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
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Node;
import de.lessvoid.nifty.Nifty;

/**
 *
 * @author Joosep
 */
public class HUDState extends AbstractAppState {
    
    private Node guiNode;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        
        guiNode = ((SimpleApplication) app).getGuiNode();
        
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(
                app.getAssetManager(), 
                app.getInputManager(), 
                app.getAudioRenderer(), 
                app.getGuiViewPort());
        Nifty nifty = niftyDisplay.getNifty();
        nifty.fromXml("Interface/gameHUD.xml", "MainScreen");
        
        app.getGuiViewPort().addProcessor(niftyDisplay);
    }
    
    @Override
    public void update(float tpf) {
        //TODO: implement behavior during runtime
    }
    
    @Override
    public void cleanup() {
        super.cleanup();
        //TODO: clean up what you initialized in the initialize method,
        //e.g. remove all spatials from rootNode
        //this is called on the OpenGL thread after the AppState has been detached
    }
    
}
