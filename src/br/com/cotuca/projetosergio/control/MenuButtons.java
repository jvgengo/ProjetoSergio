package br.com.cotuca.projetosergio.control;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;

import android.util.Log;
import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.scenes.FinalScreen;

public class MenuButtons extends CCLayer implements ButtonDelegate{
	private Button playButton;
	private Button helpButton;
	
	public MenuButtons(){

		Log.i("Teste de Método", "criou menu dos botões");
		this.setIsTouchEnabled(true);
		
		this.playButton = new Button(Assets.PLAY);
		this.helpButton = new Button(Assets.HELP);
		
		this.playButton.setDelegate(this);
		this.helpButton.setDelegate(this);
		
		this.setButtonsPosition();
		
		this.addChild(playButton);
		this.addChild(helpButton);
	}

	private void setButtonsPosition() {
		
		playButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth()/2 -160, DeviceSettings.screenHeight()-420)));
		helpButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth()/2 -160, DeviceSettings.screenHeight()-520)));
		
	}

	public void buttonClicked(Button sender) {
		if (sender.equals(this.playButton)) {
			Log.i("TESTE BUTTON", "Button clicked: Play");
			System.out.println("Button clicked: Play");

			CCDirector.sharedDirector().replaceScene(new FinalScreen().scene());
//			CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(1.0f, GameScene.createGame()));
		}
		
		if (sender.equals(this.helpButton)) {
			Log.i("TESTE BUTTON", "Button clicked:Help");
		}

		Log.i("Teste de Método", "entrou no método dos botões");
	}
}
