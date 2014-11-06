package br.com.cotuca.projetosergio.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.types.CGPoint;

import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.control.MenuButtons;
import br.com.cotuca.projetosergio.screens.ScreenBackground;

public class TitleScreen extends CCLayer{
	
	public ScreenBackground background;
	
	public TitleScreen() {
		this.background = new ScreenBackground(Assets.BG_INICIO);
		this.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() / 2.0f, DeviceSettings.screenHeight() / 2.0f)));
		this.addChild(this.background);
		
		
	//	CCSprite title = new CCSprite().sprite(Assets.LOGO);
	//	title.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() / 2, DeviceSettings.screenHeight() - 130)));
	//	this.addChild(title);
		
		MenuButtons menuLayer = new MenuButtons();
		this.addChild(menuLayer);
	}
	
	public CCScene scene() {
		CCScene scene = CCScene.node();
		scene.addChild(this);
		return scene;
	}
}
