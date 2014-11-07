package br.com.cotuca.projetosergio.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;

import br.com.cotuca.projetosergio.R;
import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.control.FinalButtons;
import br.com.cotuca.projetosergio.screens.ScreenBackground;

public class FinalScreen extends CCLayer{
	
	public ScreenBackground background;
	
	public FinalScreen() {
		this.background = new ScreenBackground(Assets.GAMEOVER);
		this.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() / 2.0f, DeviceSettings.screenHeight() / 2.0f)));
		this.addChild(this.background);
		
		
		FinalButtons finalLayer = new FinalButtons();
		this.addChild(finalLayer);
		
		SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(),R.raw.game_over);
	}
	
	public CCScene scene() {
		CCScene scene = CCScene.node();
		scene.addChild(this);
		return scene;
	}
}
