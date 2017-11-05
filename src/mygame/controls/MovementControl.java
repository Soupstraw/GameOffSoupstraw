/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.controls;

import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import java.io.IOException;

/**
 *
 * @author Joosep
 */
public class MovementControl extends AbstractControl {

    private MovementState movementState = MovementState.IDLE;
    
    private Vector3f targetPos;
    private float targetRot;
    
    private float currentRot = 0f;
    
    private float movementSpeed = 3f;
    private float turnSpeed = 3f;
    private float walkStep = 2f;
    
    public enum MovementState{
        IDLE,
        FORWARD,
        TURN
    }
    
    public MovementControl(){
        super();
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        switch(movementState){
            case FORWARD:
                Vector3f newPos = new Vector3f(getSpatial().getLocalTranslation());
                float delta = Math.min(movementSpeed * tpf, newPos.distance(targetPos));
                newPos = newPos.add(new Vector3f(targetPos).subtract(newPos).normalize().mult(delta));
                getSpatial().setLocalTranslation(newPos);
                if(getSpatial().getLocalTranslation().distance(targetPos) < 0.01f){
                    getSpatial().setLocalTranslation(targetPos);
                    movementState = MovementState.IDLE;
                }
                break;
            case TURN:
                float deltaRot = Math.min(tpf * turnSpeed, Math.abs(currentRot - targetRot));
                if(currentRot > targetRot){
                    currentRot -= deltaRot;
                }else if(currentRot < targetRot){
                    currentRot += deltaRot;
                }else{
                    movementState = MovementState.IDLE;
                }
                getSpatial().setLocalRotation(new Quaternion(new float[]{0, currentRot, 0}));
                break;
        }
    }
    
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }
    
    public Control cloneForSpatial(Spatial spatial) {
        MovementControl control = new MovementControl();
        return control;
    }
    
    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule in = im.getCapsule(this);
    }
    
    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule out = ex.getCapsule(this);
    }
    
    public void moveForward(){
        if(movementState == MovementState.IDLE){
            movementState = MovementState.FORWARD;
            Vector3f forwardVec = getSpatial().getLocalRotation().mult(new Vector3f(0, 0, walkStep));
            targetPos = getSpatial().getLocalTranslation().add(forwardVec);
        }
    }
    
    public void turnLeft(){
        if(movementState == MovementState.IDLE){
            movementState = MovementState.TURN;
            targetRot += Math.toRadians(90);
        }
    }
    
    public void turnRight(){
        if(movementState == MovementState.IDLE){
            movementState = MovementState.TURN;
            targetRot += Math.toRadians(-90);
        }
    }
    
}
