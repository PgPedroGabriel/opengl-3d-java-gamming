package engineTester;

import javax.swing.Renderer;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TextureModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Render;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Render renderer = new Render(shader);
		
		
		RawModel model = OBJLoader.loadObjModel("stall", loader);
			
		TextureModel staticModel = new TextureModel(model, new ModelTexture(loader.loadTexture("e")));
		
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-50), 0,0,0, 1);
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested())
		{
			entity.increaseRotation(0, 1, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updatedisplay();
		
		}

		shader.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
	}

}
