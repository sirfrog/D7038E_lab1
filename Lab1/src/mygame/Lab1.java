package mygame;
 
import com.jme3.app.SimpleApplication;
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
 
    @Override
    public void simpleInitApp() {
 
        //Create node. This should be at (0,0,0).
        Node center = new Node("center");
        rootNode.attachChild(center); 
        
        
        Node pivot = new Node("pivot");
        center.attachChild(pivot);
        
        
        Node sphereNode = new Node("sphereNode");
        pivot.attachChild(sphereNode); 
        
        
        Node cylinderNode = new Node("cylinderNode");
        pivot.attachChild(cylinderNode); 
        
        
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
        
        center.move(0, 0, -20f);
        pivot.move(8f, 0, 0);
        sphereNode.move(0, 3f, 0);
        cylinderNode.move(0, -2f, 0);
        /** Rotate the pivot node: Note that both boxes have rotated! */
        //pivot.rotate(.4f,.4f,0f);
    }
}