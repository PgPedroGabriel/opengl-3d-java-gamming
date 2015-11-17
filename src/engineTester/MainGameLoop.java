package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
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
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		
		
		TerrainTexture backGroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backGroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		
		
		RawModel model = OBJLoader.loadObjModel("tree", loader);
			
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("tree")));

		TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader), 
				new ModelTexture(loader.loadTexture("grassTexture")));
		
		grass.getTexture().setHasTranparency(true);
		grass.getTexture().setUseFakeLighting(true);
		
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader), 
				new ModelTexture(loader.loadTexture("fern")));
		
		fern.getTexture().setHasTranparency(true);
		
		
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		
		for (int i = 0 ; i < 500; i++)
		{
			entities.add(new Entity(staticModel, new Vector3f(random.nextFloat() * 400, 0, 
						random.nextFloat() * 400), 0,0,0, 3));
			entities.add(new Entity(grass, new Vector3f(random.nextFloat() * 350, 0, 
					random.nextFloat() * 350), 0,0,0, 1));
			entities.add(new Entity(fern, new Vector3f(random.nextFloat() * 400, 0, 
					random.nextFloat() * 400), 0,0,0, 0.6f));
			
		}
		
		//Entity entity = new Entity(staticModel, new Vector3f(-300,0,0), 0,0,0, 1);
		Light light = new Light(new Vector3f(3000,2000, 2000), new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(0,0, loader, texturePack, blendMap);
	
		

		MasterRenderer renderer = new MasterRenderer();
	
		RawModel bunnyModel = OBJLoader.loadObjModel("tree", loader);
		TexturedModel stanfordBunny = new TexturedModel(bunnyModel, new ModelTexture(loader.loadTexture("tree")));
		
		Player player = new Player(stanfordBunny, new Vector3f(100, 0, 100), 0,180,0, 0.6f);
		
		Camera camera = new Camera(player);
		
		while(!Display.isCloseRequested())
		{
			//entity.increaseRotation(0, 1, 0);
			camera.move();	
			player.move();
			renderer.proccessEntity(player);
			renderer.proccessTerrain(terrain);
			for(Entity entity: entities)
				renderer.proccessEntity(entity);
			
			renderer.render(light,camera);
			DisplayManager.updatedisplay();
		
		}

		renderer.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
	}

}
