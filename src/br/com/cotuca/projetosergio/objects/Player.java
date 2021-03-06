package br.com.cotuca.projetosergio.objects;

import java.util.ArrayList;
import java.util.List;

import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.types.CGPoint;

import android.util.Log;
import br.com.cotuca.projetosergio.calibrate.AccelerometerDelegate;
import br.com.cotuca.projetosergio.calibrate.Acelerometro;
import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.interfaces.BottleEngineDelegate;


public class Player extends CCSprite implements AccelerometerDelegate {

	public float positionX = DeviceSettings.screenWidth()/2;
	public float positionY = DeviceSettings.screenHeight()/2;
	
	private BottleEngineDelegate delegate;
	
	
	private Acelerometro accelerometer;

	private float currentAccelX;
	private float currentAccelY;
	
	private int life;
	
	private List images = new ArrayList<String>();
	private int act = 0;
	
	private static final double NOISE = 1;
	
	public Player() {
		super(Assets.A);
		setPosition(positionX, positionY);
		this.schedule("update");
		this.life = 3;
		images.add(Assets.A);
		images.add(Assets.B);
		images.add(Assets.C);
		images.add(Assets.D);
	}

	public void setDelegate(BottleEngineDelegate delegate) {
		this.delegate = delegate;
	}

	public void moveLeft() {
		if (positionX > 30) {
			positionX -= 10;
		}
		setPosition(positionX, positionY);
	}

	public void moveRight() {
		if (positionX < 50) {
			positionX += 10;
		}
		setPosition(positionX, positionY);
	}
	
	public void moveUp() {
		if (positionY > 30) {
			positionY += 10;
		}
		setPosition(positionX, positionY);
	}

	public void moveDown() {
		if (positionY < DeviceSettings.screenHeight() - 30) {
			positionY -= 10;
		}
		setPosition(positionX, positionY);
	}
	
	public void explode() {

		// Stop Shoot
		this.unschedule("update");

		// Pop Actions
		float dt = 0.2f;
		CCScaleBy a1 = CCScaleBy.action(dt, 2f);
		CCFadeOut a2 = CCFadeOut.action(dt);
		CCSpawn s1 = CCSpawn.actions(a1, a2);

		// Run actions!
		this.runAction(CCSequence.actions(s1));
		Log.d("VIDA", life+"");
		
		//life -= 1;
		
	}

	public void catchAccelerometer() {
		Acelerometro.sharedAccelerometer().catchAccelerometer();
		this.accelerometer = Acelerometro.sharedAccelerometer();
		this.accelerometer.setDelegate(this);
	}

	
	public void accelerometerDidAccelerate(float x, float y) {
			System.out.println("X: " + x);
			System.out.println("Y: " + y);
			
			// Read acceleration
			this.currentAccelX = x;
			this.currentAccelY = y;
		
	}

	public void update(float dt) {

			//fazer primeiro com tudo zero depois colocar essa constant
			if(this.currentAccelX < -NOISE && DeviceSettings.screenWidth() - 50 > this.positionX){
				this.positionX += 2;
			}
			
			if(this.currentAccelX > NOISE && 50 < this.positionX){
				this.positionX -= 2;
			}
			
			if(this.currentAccelY < -NOISE && DeviceSettings.screenHeight() - 50 > this.positionY){
				this.positionY += 2;
			}
			
			if(this.currentAccelY > NOISE && 50 < this.positionY){
				this.positionY -= 2;
			}
			
//			this.setTexture(CCTextureCache.sharedTextureCache().addImage((String) images.get(act)));
//			
//			if (act < 4) {
//				act++;
//			} else {
//				act = 0;
//			}	
			this.setPosition(CGPoint.ccp(this.positionX, this.positionY));

	}
	
	public int getLife(){
		return life;
	}
	
}
