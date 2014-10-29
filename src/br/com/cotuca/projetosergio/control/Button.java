package br.com.cotuca.projetosergio.control;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;

public class Button extends CCLayer{
	private CCSprite buttonImage;
	private ButtonDelegate delegate;
	
	public Button(String buttonImage) {
		this.setIsTouchEnabled(true);
		this.buttonImage = CCSprite.sprite(buttonImage);
		this.addChild(this.buttonImage);
	}
}
