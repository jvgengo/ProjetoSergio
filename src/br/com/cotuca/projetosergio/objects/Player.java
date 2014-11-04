package br.com.cotuca.projetosergio.objects;

import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;

import br.com.cotuca.projetosergio.R;
import br.com.cotuca.projetosergio.calibrate.AccelerometerDelegate;
import br.com.cotuca.projetosergio.calibrate.Acelerometro;
import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.interfaces.BottleEngineDelegate;


public class Player extends CCSprite implements AccelerometerDelegate {

	float positionX = 50;
	float positionY = 100;
	
	private BottleEngineDelegate delegate;
	
	private Acelerometro accelerometer;

	private float currentAccelX;
	private float currentAccelY;
	
	private static final double NOISE = 1;
	
	public Player() {
		super(Assets.DRUNK);
		setPosition(positionX, positionY);
		this.schedule("update");
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
			if(this.currentAccelX< -NOISE){
				this.positionX++;
			}
			
			if(this.currentAccelX> NOISE){
				this.positionX--;
			}
			
			if(this.currentAccelY< -NOISE){
				this.positionY++;
			}
			
			if(this.currentAccelY> NOISE){
				this.positionY--;
			}
			
			// Update Player Position
			this.setPosition(CGPoint.ccp(this.positionX, this.positionY));

	}
	
}