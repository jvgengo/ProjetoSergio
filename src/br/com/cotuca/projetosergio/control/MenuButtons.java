package br.com.cotuca.projetosergio.control;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.types.CGPoint;

import android.util.Log;
import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;

public class MenuButtons extends CCLayer implements ButtonDelegate{
	private Button playButton;
	private Button helpButton;
	
	public MenuButtons(){
		this.setIsTouchEnabled(true);
		
		this.playButton = new Button(Assets.PLAY);
		this.helpButton = new Button(Assets.HELP);
		
		//coloca os botoes nas possicoes corretas
		
		this.setButtonsPosition();
		this.playButton.setDelegate(this);
		this.helpButton.setDelegate(this);
		
		
		this.addChild(playButton);
		this.addChild(helpButton);
	}

	private void setButtonsPosition() {
		
		playButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth()/2, DeviceSettings.screenHeight()/2)));
		helpButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth()/2, DeviceSettings.screenHeight()/2 - 100)));
		
	}

	public void buttonClicked(Button sender) {
		if (sender.equals(this.playButton)) {
			Log.i("TESTE BUTTON", "Button clicked:Play");
		}
		
		if (sender.equals(this.helpButton)) {
			Log.i("TESTE BUTTON", "Button clicked:Help");
		}
	}
}
