package br.com.cotuca.projetosergio.control;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;

import android.util.Log;
import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.scenes.GameScene;

public class MenuButtons extends CCLayer implements ButtonDelegate{
	private Button playButton;
	
	public MenuButtons(){

		Log.i("Teste de M�todo", "criou menu dos bot�es");
		this.setIsTouchEnabled(true);
		
		this.playButton = new Button(Assets.PLAY);
		
		this.playButton.setDelegate(this);
		
		this.setButtonsPosition();
		
		this.addChild(playButton);
	}

	private void setButtonsPosition() {
		
		playButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth()/2 -80, DeviceSettings.screenHeight()-450)));
	}

	public void buttonClicked(Button sender) {
		if (sender.equals(this.playButton)) {
			Log.i("TESTE BUTTON", "Button clicked: Play");
			System.out.println("Button clicked: Play");

			CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(1.0f, GameScene.createGame()));
		}

	}
}
