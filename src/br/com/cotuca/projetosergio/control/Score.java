package br.com.cotuca.projetosergio.control;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.opengl.CCBitmapFontAtlas;

import br.com.cotuca.projetosergio.config.DeviceSettings;

public class Score extends CCLayer {
	private int score;
	private CCBitmapFontAtlas text;
	
	public Score() {
		score = 0;
		
		this.text = CCBitmapFontAtlas.bitmapFontAtlas(String.valueOf(this.score), "UniSansSemiBold_Nunbers_240.fnt");
		this.text.setScale((float) 240/240);
		this.setPosition(DeviceSettings.screenWidth()-50,DeviceSettings.screenHeight()-50);
		this.addChild(this.text);
	}
	
	public void increase() {
		score++;
		this.text.setString(String.valueOf(this.score));
	}
	
}
