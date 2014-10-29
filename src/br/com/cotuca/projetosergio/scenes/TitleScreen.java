package br.com.cotuca.projetosergio.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.types.CGPoint;

import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.screens.ScreenBackground;

public class TitleScreen extends CCLayer{
	
	public ScreenBackground background;
	
	public TitleScreen() {
		this.background = new ScreenBackground(Assets.BACKGROUND);
		this.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() / 2.0f, DeviceSettings.screenHeight() / 2.0f)));
		this.addChild(this.background);
	}
	
	public CCScene scene() {
		CCScene scene = CCScene.node();
		scene.addChild(this);
		return scene;
	}
}
