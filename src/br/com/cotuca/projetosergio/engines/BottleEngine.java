package br.com.cotuca.projetosergio.engines;

import java.util.Random;

import org.cocos2d.layers.CCLayer;

import br.com.cotuca.projetosergio.config.Assets;
import br.com.cotuca.projetosergio.interfaces.BottleEngineDelegate;
import br.com.cotuca.projetosergio.objects.Bottle;

public class BottleEngine extends CCLayer {
	
	private BottleEngineDelegate delegate;
	
	public BottleEngine() {
		this.schedule("bottleEngine",1.0f / 10f);
	}
	
	public void bottleEngine(float dt) {
		if (new Random().nextInt(30) == 0) {
			this.getDelegate().createBottle(new Bottle(Assets.BOTTLE));
		}
	}

	public BottleEngineDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(BottleEngineDelegate delegate) {
		this.delegate = delegate;
	}
	
	
	
	
}
