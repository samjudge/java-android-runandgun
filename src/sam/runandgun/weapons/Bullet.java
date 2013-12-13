package sam.runandgun.weapons;

import sam.runandgun.canvasDrawable.canvasDrawable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class Bullet implements canvasDrawable{
	
	private double shotDirection;
	private double shotSpeed;
	private Point pos;
	private Bitmap bulletImage;
	private boolean isFriendly;
	
	//debug
	
	public int id;
	
	//enddebug
	
	public Bullet(double shotSpeed, double shotDirection, int x, int y, Bitmap bImage, boolean isFriendly){
		this.isFriendly = isFriendly;
		this.shotSpeed = shotSpeed;
		this.shotDirection = checkDirection(shotDirection);
		this.pos = new Point();
		this.pos.x = x;
		this.pos.y = y;
		this.bulletImage = bImage;
	}
	
	public void move(){
		//the calculations below move the bullet in it's given direction, converting a degree number to rads
		pos.x += (int)shotSpeed*(int)Math.cos(Math.toRadians(shotDirection-90));
		pos.y += (int)shotSpeed*(int)Math.sin(Math.toRadians(shotDirection-90));
	}
	
	private double checkDirection(double direction){ //Used to make sure the degrees range between 0 and 359
		while (direction > 359){
			direction -= 360; 
		}
		while (direction < 0){
			direction += 360;
		}
		return direction;
	}
	
	public void drawToCanvas(Canvas c){
		c.drawBitmap(bulletImage, pos.x, pos.y, null);
	}

	public boolean isFriendly() {
		return isFriendly;
	}

	public void setFriendly(boolean isFriendly) {
		this.isFriendly = isFriendly;
	}
	
	public Point getPos() {
		return pos;
	}
	
}