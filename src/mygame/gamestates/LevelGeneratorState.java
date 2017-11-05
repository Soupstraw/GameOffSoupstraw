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
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetManager;
import com.jme3.asset.MaterialKey;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;

/**
 *
 * @author Joosep
 */
public class LevelGeneratorState extends AbstractAppState {
    
    private Node rootNode;
    private AssetManager assetManager;
    
    private float tileSize = 2f;
    
    private Node[][] tileMap;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        
        rootNode = ((SimpleApplication) app).getRootNode();
        assetManager = app.getAssetManager();
        
        // Add point light to camera
        PointLight lamp = new PointLight();
        lamp.setPosition(new Vector3f(0, 0, 0));
        lamp.setColor(ColorRGBA.White);
        rootNode.addLight(lamp);
        
        generateLevel(16, 16);
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
    
    private void generateLevel(int width, int height){
        tileMap = new Node[width][height];
        
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                Node tile = new Node();
                tile.setLocalTranslation(new Vector3f(x*tileSize, 0f, y*tileSize));
                
                // Create floor quad
                Quad quadMesh = new Quad(tileSize, tileSize);
                Geometry boxGeo = new Geometry("Colored Box", quadMesh); 
                boxGeo.setLocalRotation(new Quaternion().fromAngles((float) Math.toRadians(-90), 0, 0));
                boxGeo.setLocalTranslation(-tileSize/2, -1.5f, tileSize/2);
                Material boxMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                boxMat.setTexture("ColorMap", assetManager.loadTexture("Textures/floor.png"));
                boxGeo.setMaterial(boxMat); 
                
                tile.attachChild(boxGeo);
                rootNode.attachChild(tile);
                tileMap[x][y] = tile;
            }
        }
    }
    
}
