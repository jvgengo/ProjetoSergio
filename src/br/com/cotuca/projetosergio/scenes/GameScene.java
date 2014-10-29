package br.com.cotuca.projetosergio.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.types.CGPoint;

import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.screens.ScreenBackground;

public class GameScene extends CCLayer{
	private ScreenBackground background;
	
	public GameScene() {
		this.background = new ScreenBackground(Assets.BACKGROUND);
		this.background.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(
				DeviceSettings.screenWidth() / 2.0f, 
				DeviceSettings.screenHeight() / 2.0f)));
		this.addChild(this.background);
	}
	
	public static CCScene createGame() {
		CCScene scene = CCScene.node();
		GameScene layer = new GameScene();
		scene.addChild(layer);
		return scene;
	}
}
