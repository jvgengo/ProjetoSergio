package br.com.cotuca.projetosergio.scenes;

/*
 * 					 ii	   iiiiiiii
 * 					 iiiiiiii	 ii		
 * 
 * 		iiiiiiiii    iiiiiiiiiiiiii		iiiiiiiiiiiiiii		
 * 		   iii	     iii		iii		iii			iii
 * 		   iii		 iii		iii		iii			iii
 * 		   iii		 iii        iii  	iii			iii
 * 		   iii		 iiiiiiiiiiiiii		iii			iii
 *  iii	   iii       iii		iii		iii			iii
 *  iiiiiiiiii		 iii		iii		iiiiiiiiiiiiiii
 */

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.engines.BottleEngine;
import br.com.cotuca.projetosergio.interfaces.BottleEngineDelegate;
import br.com.cotuca.projetosergio.objects.Bottle;
import br.com.cotuca.projetosergio.objects.Player;
import br.com.cotuca.projetosergio.screens.ScreenBackground;

public class GameScene extends CCLayer implements BottleEngineDelegate {

	private ScreenBackground background;
	private BottleEngine bottleEngine;
	private CCLayer bottlesLayer, playerLayer;
	private List bottlesArray, playersArray;
	private Player player;

	public GameScene() {
		this.setIsTouchEnabled(true);
		this.background = new ScreenBackground(Assets.BG_GAME);
		this.background.setPosition(DeviceSettings.screenResolution(CGPoint
				.ccp(DeviceSettings.screenWidth() / 2.0f,
						DeviceSettings.screenHeight() / 2.0f)));
		this.addChild(this.background);

		this.bottlesLayer = CCLayer.node();
		this.addChild(this.bottlesLayer);

		this.playerLayer = CCLayer.node();
		this.addChild(this.playerLayer);

		this.addGameObjects();

		player.catchAccelerometer();
	}

	public static CCScene createGame() {
		CCScene scene = CCScene.node();
		GameScene layer = new GameScene();
		scene.addChild(layer);
		return scene;
	}

	@Override
	public void createBottle(Bottle bottle) {
		bottle.setDelegate(this);
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
		this.schedule("checkHits");
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
		((Bottle) bottle).shooted();
		((Player) player).explode();

		// if (((Player) player).getLife() <= 0)
		CCDirector.sharedDirector().replaceScene(new FinalScreen().scene());
	}

	public void quitGame() {
		CCDirector.sharedDirector().replaceScene(new TitleScreen().scene());
	}

	public CGRect getBoarders(CCSprite object) {
		CGRect rect = object.getBoundingBox();
		CGPoint glPoint = rect.origin;
		CGRect glRect = CGRect.make(glPoint.x, glPoint.y, rect.size.width,
				rect.size.height);
		return glRect;
	}

	private boolean checkRadiusHitsOfArraySprite(
			List<? extends CCSprite> array1, List<? extends CCSprite> array2,
			GameScene gameScene, String hit) {
		boolean result = false;

		for (int i = 0; i < array1.size(); i++) {
			CGRect rect1 = getBoarders(array1.get(i));
			for (int j = 0; j < array2.size(); j++) {
				CGRect rect2 = getBoarders(array2.get(j));

				if (CGRect.intersects(rect1, rect2)) {
					Log.d("Colisao", hit);
					result = true;

					Method method;
					try {
						method = GameScene.class.getMethod(hit, CCSprite.class,
								CCSprite.class);

						method.invoke(gameScene, array1.get(i), array2.get(j));
					} catch (Exception e) {
					}
				}
			}
		}
		return result;
	}

	public void checkHits(float dt) {
		// this.checkRadiusHitsOfArray(array1, array2, this, "bottlehit");

		this.checkRadiusHitsOfArraySprite(bottlesArray, playersArray, this,
				"playerHit");
	}

	@Override
	public void removeBottle(Bottle bottle) {
		this.bottlesArray.remove(this);
	}
	
	@Override
	protected void registerWithTouchDispatcher() {
		CCTouchDispatcher.sharedDispatcher().addTargetedDelegate(this, 0, false);
	}

	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		final int pointerCount = event.getPointerCount();

		for (int p = 0; p < pointerCount; p++) {
			CGPoint touchLocation = CGPoint.make(event.getX(p), event.getY(p));
			touchLocation = CCDirector.sharedDirector().convertToGL(touchLocation);
			touchLocation = this.convertToNodeSpace(touchLocation);

			for (int i = 0; i < bottlesArray.size(); i++) {
				Bottle b = (Bottle) bottlesArray.get(i);
				CGRect boudingBox = b.getBoundingBox();
				boudingBox.set(boudingBox.origin.x, boudingBox.origin.y,
						CGRect.width(boudingBox) + 30,
						CGRect.height(boudingBox) + 30);
				if (CGRect.containsPoint(boudingBox, touchLocation)) {
					b.shooted();
				}
			}
		}
		return true;
	}

}
