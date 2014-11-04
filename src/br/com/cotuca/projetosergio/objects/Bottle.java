package br.com.cotuca.projetosergio.objects;

import java.util.Random;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import br.com.cotuca.projetosergio.config.DeviceSettings;

public class Bottle extends CCSprite {
	
	private float x,y;
	private int rotacao = 0;
	
	public Bottle(String image) {
		super(image);
		
		x = new Random().nextInt(Math.round(DeviceSettings.screenWidth()));
//		y = new Random().nextInt(Math.round(DeviceSettings.screenHeight()));
		y = DeviceSettings.screenHeight();
	}

	public void start() {
		this.schedule("update");
	}
	
	public void update(float dt) {
		y -= 5;
		this.setRotation(rotacao);
		rotacao += 5;
		this.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(x, y)));
	}
	
	public void removeMe() {
		this.removeFromParentAndCleanup(true);
	}
	
}
