package mygame.gamestates;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.system.AppSettings;

/**
 *
 * @author Joosep
 */
public class LevelGeneratorState extends AbstractAppState {
    
    private Node rootNode;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        
        
        rootNode = ((SimpleApplication) app).getRootNode();
        
        // Set camera to (0,0,0)
        app.getCamera().setLocation(new Vector3f(0, 0, 0));
        
        AppSettings settings = app.getContext().getSettings();
        app.getCamera().setFrustumPerspective(70f, settings.getWidth()/settings.getHeight(), 0.1f, 1000f);
        
        // Add point light to camera
        PointLight lamp = new PointLight();
        lamp.setPosition(app.getCamera().getLocation());
        lamp.setColor(ColorRGBA.White);
        rootNode.addLight(lamp); 
        
        {
        // Create floor quad
        Quad quadMesh = new Quad(5, 5);
        Geometry boxGeo = new Geometry("Colored Box", quadMesh); 
        boxGeo.setLocalRotation(new Quaternion().fromAngles((float) Math.toRadians(-90), 0, 0));
        boxGeo.setLocalTranslation(-2.5f, -1f, 2.5f);
        Material boxMat = new Material(app.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md"); 
        boxMat.setBoolean("UseMaterialColors", true); 
        boxMat.setColor("Ambient", ColorRGBA.Green); 
        boxMat.setColor("Diffuse", ColorRGBA.Green); 
        boxGeo.setMaterial(boxMat); 
        rootNode.attachChild(boxGeo);
        }
        {
        // Create floor quad
        Quad quadMesh = new Quad(5, 5);
        Geometry boxGeo = new Geometry("Colored Box", quadMesh); 
        boxGeo.setLocalTranslation(-2.5f, -1f, 2.5f);
        Material boxMat = new Material(app.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md"); 
        boxMat.setBoolean("UseMaterialColors", true); 
        boxMat.setColor("Ambient", ColorRGBA.Red); 
        boxMat.setColor("Diffuse", ColorRGBA.White); 
        boxGeo.setMaterial(boxMat); 
        rootNode.attachChild(boxGeo);
        }
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
