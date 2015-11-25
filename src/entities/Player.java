package entities;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;

public class Player extends Entity{

	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 30;
	
	private static final float TERRAIN_HEIGHT = 0; 
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	
	private List<Integer> eatedsCarrot = new ArrayList<Integer>();
	
	private boolean isInAir = false;
	
	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
		// TODO Auto-generated constructor stub
	}
	
	
	public void move(List<Entity> carrots)
	{
		checkInputs();
		super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePostion(dx, 0, dz);
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePostion(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		
		if(super.getPosition().y < TERRAIN_HEIGHT)
		{
			upwardsSpeed = 0;
			super.getPosition().y = TERRAIN_HEIGHT;
			isInAir = false;
		}
		
		for(Entity carrot: carrots)
		{
			if(this.getXPosRounded() >= carrot.getXPosRounded() - 3 &&
			   this.getXPosRounded() <= carrot.getXPosRounded() + 3 &&
			   this.getZPosRounded() >= carrot.getZPosRounded() - 3 &&
			   this.getZPosRounded() <= carrot.getZPosRounded() + 3)
			{
				this.eatCarrot(carrot);
			}
		}
	}
	
	private void eatCarrot(Entity carrot)
	{
		this.eatedsCarrot.add(carrot.id);
	}
	
	
	private void jump()
	{
		if(!isInAir)
		{
			this.upwardsSpeed = JUMP_POWER;
			isInAir = true;
		}
			
	}
	
	public boolean eated(Entity carrot)
	{
		return this.eatedsCarrot.contains(carrot.id);
	}
	
	private void checkInputs()
	{
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) 
		{
			currentSpeed = RUN_SPEED;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_S)) 
		{
			currentSpeed = -RUN_SPEED;
		}
		else
		{
			currentSpeed = 0;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) 
		{
			currentTurnSpeed = -TURN_SPEED;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_A)) 
		{
			currentTurnSpeed = TURN_SPEED;
		}
		else
		{
			currentTurnSpeed = 0;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) 
		{
			jump();
		}
		
	}
	
}
