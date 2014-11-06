package br.com.cotuca.projetosergio.objects;

import java.util.List;
import java.util.Random;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.interfaces.BottleEngineDelegate;
import br.com.cotuca.projetosergio.scenes.GameScene;

public class Bottle extends CCSprite {

	private float x, y;
	private int rotacao = 0;
	private int lado;
	
	private BottleEngineDelegate delegate;

	public Bottle(String image) {
		super(image);
		
		int key = new Random().nextInt(3);
		lado = key;
		
		switch (key) {
		case 0:
			x = new Random().nextInt(Math.round(DeviceSettings.screenWidth() - 30));
			y = DeviceSettings.screenHeight();
			break;
		case 1:
			x = new Random().nextInt(Math.round(DeviceSettings.screenWidth() - 30));
			y = 0;
			break;
		case 2:
			y = new Random().nextInt(Math.round(DeviceSettings.screenHeight() - 30));
			x= 0;
			break;
		case 3:
			y = new Random().nextInt(Math.round(DeviceSettings.screenHeight() - 30));
			x = DeviceSettings.screenWidth();
			break;
		default:
			break;
		}
	}

	public void start() {
		this.schedule("update");
	}

	public void update(float dt) {
		
		switch (lado) {
		case 0:
			y -= 1;
			break;
		case 1:
			y+=1;
			break;
		case 2:
			x+=1;
			break;
		case 3:
			x-=1;
			break;
		default:
			break;
		}
		
		this.setRotation(rotacao);
		rotacao += 1;
		this.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(x, y)));
	}

	public void removeMe() {
		this.removeFromParentAndCleanup(true);
	}

	public void setDelegate(BottleEngineDelegate delegate) {
		this.delegate = delegate;
	}
	
	public void shooted() {
		this.delegate.removeBottle(this);
		
		this.unschedule("update");
		
		float dt = 0.2f;
		CCScaleBy a1 = CCScaleBy.action(dt, 0.5f);
		CCFadeOut a2 = CCFadeOut.action(dt);
		CCSpawn s1 = CCSpawn.actions(a1, a2);
		
		CCCallFunc c1 = CCCallFunc.action(this, "removeMe");
		this.runAction(CCSequence.actions(s1, c1));
		
	}
	

}
