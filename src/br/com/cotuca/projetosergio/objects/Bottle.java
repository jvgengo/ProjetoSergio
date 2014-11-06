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
	
	private BottleEngineDelegate delegate;

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
