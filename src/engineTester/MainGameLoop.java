package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		RawModel model = OBJLoader.loadObjModel("stall", loader);
			
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("tree")));
		ModelTexture texture = staticModel.getTexture();

		
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-50), 0,0,0, 1);
		Light light = new Light(new Vector3f(3000,2000, 2000), new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(-1,-1, loader, new ModelTexture(loader.loadTexture("grass")));
		
		Camera camera = new Camera();
		
		
		MasterRenderer renderer = new MasterRenderer();
		
		
		while(!Display.isCloseRequested())
		{
			entity.increaseRotation(0, 1, 0);
			camera.move();	
			
			renderer.proccessTerrain(terrain);
			renderer.proccessEntity(entity);
			
			renderer.render(light,camera);
			DisplayManager.updatedisplay();
		
		}

		renderer.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
	}

}
