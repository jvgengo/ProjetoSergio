package br.com.cotuca.projetosergio;

import java.util.ArrayList;

import android.util.Log;
import android.view.MotionEvent;

public class TouchManager {

	private class Toque{
		private double x;
		private double y;
		
		public Toque(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
    
    public boolean onTouchEvent(MotionEvent event) {
    	
    	ArrayList<Toque> t = new ArrayList<Toque>();
    	
        final int pointerCount = event.getPointerCount();
        
        for (int p = 0; p < pointerCount; p++) {
            t.add(new Toque(event.getX(p),event.getY(p)));
        }
        
        t.add(new Toque(event.getX(),event.getY()));
        
        return true;
    }
  
}