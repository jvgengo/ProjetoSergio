package br.com.cotuca.projetosergio.control;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;

import android.util.Log;
import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.config.DeviceSettings;
import br.com.cotuca.projetosergio.scenes.TitleScreen;

public class FinalButtons extends CCLayer implements ButtonDelegate{
	private Button continueButton;
	
	public FinalButtons(){

		this.setIsTouchEnabled(true);
		
		this.continueButton = new Button(Assets.CONTINUE);
		
		this.continueButton.setDelegate(this);
		
		this.setButtonsPosition();
		
		this.addChild(continueButton);
	}

	private void setButtonsPosition() {
		
		continueButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth()/2 - 160, DeviceSettings.screenHeight()/2 - 420)));
		
	}

	public void buttonClicked(Button sender) {
		if (sender.equals(this.continueButton)) {
			Log.i("TESTE BUTTON", "Button clicked: Continue");

			CCDirector.sharedDirector().replaceScene(new TitleScreen().scene());
		}
		
	}
}
