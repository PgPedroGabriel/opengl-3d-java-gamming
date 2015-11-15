package textures;

public class ModelTexture {
	private int textureID;
	
	private float shineDamper = 1;
	private float reflectivity = 0;
	
	private boolean hasTranparency = false;
	
	private boolean useFakeLighting = false;
	

	
	public void setHasTranparency(boolean hasTranparency) {
		this.hasTranparency = hasTranparency;
	}
	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}
	public float getReflectivity() {
		return reflectivity;
	}
	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}

	public ModelTexture(int id)
	{
		this.textureID = id;
	}
	public int getID(){
		return this.textureID;
	}
	
	public float getShineDamper() {
		return shineDamper;
	}
	public boolean isHasTranparency() {
		return hasTranparency;
	}
	public boolean isUseFakeLighting() {
		return useFakeLighting;
	}
	public void setUseFakeLighting(boolean useFakeLighting) {
		this.useFakeLighting = useFakeLighting;
	}
}
