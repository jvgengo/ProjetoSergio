package br.com.cotuca.projetosergio.objects;

import java.util.List;
import java.util.Random;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.scenes.GameScene;

public class Bottle extends CCSprite {

	private float x, y;
	private int rotacao = 0;

	public Bottle(String image) {
		super(image);

		// y = DeviceSettings.screenHeight();
		x = new Random().nextInt(Math.round(DeviceSettings.screenWidth() - 30));
		y = new Random().nextInt(Math.round(DeviceSettings.screenHeight()- 30));

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
	
	public CGRect getBoarders(CCSprite object) {
		CGRect rect = object.getBoundingBox();
		CGPoint glPoint = rect.origin;
		CGRect glRect = CGRect.make(glPoint.x,glPoint.y, rect.size.width,rect.size.height);
		return glRect;
	}
	
	
	private boolean checkRadiusHitsOfArray(List<? extends CGRect> array1, List<? extends CCSprite> array2,GameScene gameScene,String hit) {
		boolean result = false;
		
		for (int i = 0; i < array1.size(); i++) {
			CGRect rect1 = array1.get(i);
			
			for (int j = 0; j < array2.size(); j++) {
				CGRect rect2 = getBoarders(array2.get(j));
				
				if (CGRect.intersects(rect1, rect2)) {
					result = true;
				}
			}
		}
		return result;
	}
}
