package com.outlook;

/**
 * Created by Ievgen_Ostapenko on 5/2/2016.
 */
public enum Devices
{

	IPHONE_6("Iphone 6", 375, 667, true),
	IPAD("Ipad", 768, 1024, true),
	NEXUS_6P("Nexus 6P", 435, 773, true);

	private int width;
	private int height;

	public String getModel()
	{
		return model;
	}

	private String model;
	private boolean isPortrait;

	Devices(String model, int width, int height, boolean isPortrait)
	{
		this.width = width;
		this.height = height;
		this.model = model;
		this.isPortrait = isPortrait;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public boolean isPortrait(){
		return isPortrait;
	}

	public Devices changeOrientation(String orientation)
	{
		if(orientation.equalsIgnoreCase("Panorama")){
			if (isPortrait())
			{
				int localHeight = this.height;
				this.height = this.width;
				this.width = localHeight;
				this.isPortrait = false;
				return this;
			}
		}
		else if(orientation.equalsIgnoreCase("Portrait")){
			if (!isPortrait())
			{
				int localHeight = this.height;
				this.height = this.width;
				this.width = localHeight;
				this.isPortrait = true;
				return this;
			}
		}
		else {
			throw new IllegalArgumentException("There aren't " + orientation + " option to choose");
		}
		return null;
	}


	public static Devices getByModelName(String model)
	{
		Devices[] models = values();
		for (Devices m : models)
		{
			if (m.getModel().equals(model))
			{
				return m;
			}
		}
		throw new RuntimeException("Model '" + model + "' is not found in Devices enum");
	}
}
