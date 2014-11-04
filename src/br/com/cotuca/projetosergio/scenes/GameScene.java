package br.com.cotuca.projetosergio.scenes;

import java.util.ArrayList;
import java.util.List;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

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
	private CCLayer bottlesLayer,playerLayer;
	private List bottlesArray,playersArray;
	private Player player;
	
	public GameScene() {
		this.background = new ScreenBackground(Assets.BG_GAME);
		this.background.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(
				DeviceSettings.screenWidth() / 2.0f, 
				DeviceSettings.screenHeight() / 2.0f)));
		this.addChild(this.background);
		
		this.bottlesLayer = CCLayer.node();
		this.addChild(this.bottlesLayer);
		
		this.playerLayer = CCLayer.node();
		this.addChild(this.playerLayer);
		
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
		this.player = new Player();
		this.playerLayer.addChild(this.player);
		this.playersArray = new ArrayList();
		this.playersArray.add(this.player);
		
	}
	
	@Override
	public void onEnter() {
		super.onEnter();
		this.schedule("checkhits");
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
	
	public void checkHits(float dt) {
//		this.checkRadiusHitsOfArray(array1, array2, this, "bottlehit");
	}
	
}
