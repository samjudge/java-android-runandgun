package sam.runandgun.weapons;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Missile extends AimedShot{
	
	public Missile(double shotSpeed, double shotDirection, int x, int y, Bitmap bImage, boolean isFriendly, Point playerTarget) {
		super(20, shotSpeed, shotDirection, x, y, 25, 25, bImage, isFriendly, playerTarget);
	}
	
	public void move(){
		super.move();
		findTarget();
	}
	
	public void findTarget(){ //find a player to hit
		Point thisPos = this.getPos();
		if (thisPos.x - this.getTarget().x > 0){ // if the missile is to the right
			this.setShotDirection(this.getShotDirection() + 0.3); //change direction slightly
		} else if (thisPos.x - this.getTarget().x < 0){ //if the missile is to the left
			this.setShotDirection(this.getShotDirection() - 0.3);
		}
	}

}
