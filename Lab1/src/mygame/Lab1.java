package mygame;
 
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;
 
/** Sample 2 - How to use nodes as handles to manipulate objects in the scene.
 * You can rotate, translate, and scale objects by manipulating their parent nodes.
 * The Root Node is special: Only what is attached to the Root Node appears in the scene. */
public class Lab1 extends SimpleApplication {
 
    public static void main(String[] args){
        Lab1 app = new Lab1();
        app.start();
    }
 
    protected Node center;
    protected Node pivot;
    protected Node sphereNode;
    protected Node cylinderNode;
    protected int rotatesRight = 1;
    
    private void initNodeTree(){
 
        //Create node. This should be at (0,0,0).
        center = new Node("center");
        rootNode.attachChild(center); 
        
        
        pivot = new Node("pivot");
        center.attachChild(pivot);
        
        
        sphereNode = new Node("sphereNode");
        pivot.attachChild(sphereNode); 
        
        
        cylinderNode = new Node("cylinderNode");
        pivot.attachChild(cylinderNode); 
    }

    private void initShapes() {
        // create blue box
        Box box = new Box(1f,9f,4f);
        Geometry blue = new Geometry("Box", box);
        Material mat1 = new Material(assetManager, 
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Blue);
        blue.setMaterial(mat1);
        
        Cylinder cyl = new Cylinder(32,32,2f,3f, true);
        Geometry red = new Geometry("Cylinder", cyl);
        Material mat2 = new Material(assetManager, 
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Red);
        red.setMaterial(mat2);
        
        Sphere sphere = new Sphere(32,32,3f);
        Geometry yellow = new Geometry("Sphere", sphere);
        sphere.setTextureMode(Sphere.TextureMode.Projected);
        Material mat3 = new Material(assetManager, 
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat3.setColor("Color", ColorRGBA.Yellow);
        yellow.setMaterial(mat3);
 
        /** Attach the two boxes to the *pivot* node. (And transitively to the root node.) */
        center.attachChild(blue);
        cylinderNode.attachChild(red);
        sphereNode.attachChild(yellow);
    }
    
    private void initKeys() {
    //Immediate actions:
    inputManager.addMapping("Reverse",  new KeyTrigger(KeyInput.KEY_R));
    inputManager.addMapping("Toggle", new KeyTrigger(KeyInput.KEY_T));
    inputManager.addMapping("Opposite", new KeyTrigger(KeyInput.KEY_O));
    
    //while pressed
    inputManager.addMapping("Backward",   new KeyTrigger(KeyInput.KEY_B));
    inputManager.addMapping("Forward",  new KeyTrigger(KeyInput.KEY_F));
    inputManager.addMapping("Shrink", new KeyTrigger(KeyInput.KEY_S));
    inputManager.addMapping("Grow", new KeyTrigger(KeyInput.KEY_G));
    
    //When released
    inputManager.addMapping("Disappear", new KeyTrigger(KeyInput.KEY_D));
    
    // Add the names to the action listener.
    inputManager.addListener(actionListener, "Reverse","Toggle","Opposite");
    inputManager.addListener(analogListener,"Backward","Forward","Shrink",)
 
  }
    
    @Override
    public void simpleInitApp() {
        
        initNodeTree();
        initShapes();

        
        center.move(0, 0, -20f);
        pivot.move(8f, 0, 0);
        sphereNode.move(0, 3f, 0);
        cylinderNode.move(0, -2f, 0);
        /** Rotate the pivot node: Note that both boxes have rotated! */
        //pivot.rotate(.4f,.4f,0f);
    }
    *
    private ActionListener actionListener = new ActionListener() {
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("Pause") && !keyPressed) {
        isRunning = !isRunning;
      }
    }
  };
 
  /*
  private AnalogListener analogListener = new AnalogListener() {
    public void onAnalog(String name, float value, float tpf) {
      if (isRunning) {
        if (name.equals("Rotate")) {
          player.rotate(0, value*speed, 0);
        }
        if (name.equals("Right")) {
          Vector3f v = player.getLocalTranslation();
          player.setLocalTranslation(v.x + value*speed, v.y, v.z);
        }
        if (name.equals("Left")) {
          Vector3f v = player.getLocalTranslation();
          player.setLocalTranslation(v.x - value*speed, v.y, v.z);
        }
      } else {
        System.out.println("Press P to unpause.");
      }
    }
    */
    @Override
    public void simpleUpdate(float tpf) {
        // make the player rotate:
        center.rotate(0, 0.5f*tpf*rotatesRight, 0); 
        pivot.rotate(0,0,2.5f*tpf);
    }
}