package br.com.cotuca.projetosergio.objects;

import java.util.Random;

import org.cocos2d.nodes.CCSprite;

import br.com.cotuca.projetosergio.config.DeviceSettings;

public class Bottle extends CCSprite {
	
	private float x,y;
	
	public Bottle(String image) {
		super(image);
		
		x = new Random().nextInt(Math.round(DeviceSettings.screenWidth()));
		y = new Random().nextInt(Math.round(DeviceSettings.screenHeight()));
	}

	public void start() {
		this.schedule("update");
	}
	
	public void update(float dt) {
		//logica de movimentacao da garrafa
	}
	
	public void removeMe() {
		this.removeFromParentAndCleanup(true);
	}
	
}
