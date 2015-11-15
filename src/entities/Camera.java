package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera
{

	private Vector3f position = new Vector3f(200, 3, 200);
	private float pitch;
	private float yaw = -50f;
	private float roll;

	public Camera()
	{
	}

	public void move()
	{

		float arg_yaw = Mouse.getDX();
		
		yaw += arg_yaw/10 ;
		
		float arg_roll = Mouse.getDY();
		
		pitch += -(arg_roll/10) ;
		
		
		Mouse.setGrabbed(true);

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) 
		{
			float toZ = ((float)Math.sin( Math.toRadians(yaw+90))) ;
			float toX = ((float)Math.cos( Math.toRadians(yaw+90))) ;
			position.x -= toX;
			position.z -= toZ;

		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) 
		{
			float toZ = ((float)Math.sin( Math.toRadians(yaw+90))) ;
			float toX = ((float)Math.cos( Math.toRadians(yaw+90))) ;
			position.x += toX;
			position.z += toZ;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) 
		{
			float toZ = ((float)Math.sin( Math.toRadians(yaw))) ;
			float toX = ((float)Math.cos( Math.toRadians(yaw))) ;
			position.x += toX;
			position.z += toZ;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) 
		{
			float toZ = ((float)Math.sin( Math.toRadians(yaw))) ;
			float toX = ((float)Math.cos( Math.toRadians(yaw))) ;
			position.x -= toX;
			position.z -= toZ;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) 
		{
			position.y += 0.2f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) 
		{
			position.y -= 0.2f;
		}
	}
	public Vector3f getPosition()
	{
		return position;
	}
	public float getPitch()
	{
		return pitch;
	}
	public float getYaw()
	{
		return yaw;
	}
	public float getRoll()
	{
		return roll;
	}
}
