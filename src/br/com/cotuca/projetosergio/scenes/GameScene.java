package br.com.cotuca.projetosergio.scenes;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGRect;

import android.util.Log;
import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.engines.BottleEngine;
import br.com.cotuca.projetosergio.interfaces.BottleEngineDelegate;
import br.com.cotuca.projetosergio.objects.Bottle;
import br.com.cotuca.projetosergio.objects.Player;
import br.com.cotuca.projetosergio.screens.ScreenBackground;

public class GameScene extends CCLayer implements BottleEngineDelegate{
	
	private ScreenBackground background;
	private BottleEngine bottleEngine;
	private CCLayer bottlesLayer;
	private List bottlesArray;
	private Player player;
	
	public GameScene() {
		this.background = new ScreenBackground(Assets.BG_INICIO);
		this.background.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(
				DeviceSettings.screenWidth() / 2.0f, 
				DeviceSettings.screenHeight() / 2.0f)));
		this.addChild(this.background);
		
		this.bottlesLayer = CCLayer.node();
		this.addChild(this.bottlesLayer);
		
		this.addGameObjects();
	}
	
	public static CCScene createGame() {
		CCScene scene = CCScene.node();
		GameScene layer = new GameScene();
		scene.addChild(layer);
		return scene;
	}

	@Override
	public void createBottle(Bottle bottle) {
		this.bottlesLayer.addChild(bottle);
		bottle.start();
		this.bottlesArray.add(bottle);
	}
	
	private void addGameObjects() {
		this.bottleEngine = new BottleEngine();
		this.bottlesArray = new ArrayList();
	}
	
	@Override
	public void onEnter() {
		super.onEnter();
		this.startEngines();
	}

	private void startEngines() {
		this.addChild(this.bottleEngine);
		this.bottleEngine.setDelegate(this);
	}
	
	public void moveLeft() {
		player.moveLeft();
	}
	
	public void moveRight() {
		player.moveRight();
	}
	
	public void playerHit(CCSprite bottle, CCSprite player) {
		((Bottle) bottle).removeMe();
		((Player) player).explode();
		CCDirector.sharedDirector().replaceScene(new TitleScreen().scene());
	}
	
	public void quitGame() {
		CCDirector.sharedDirector().replaceScene(new TitleScreen().scene());
	}
}
