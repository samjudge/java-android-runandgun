package sam.runandgun.weapons;

import sam.runandgun.canvasDrawable.canvasDrawable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class Bullet implements canvasDrawable{ //Need to clean up this class
	
	private double shotDirection;
	private double shotSpeed;
	private Point pos;
	double xPos;
	double yPos;
	private Bitmap bulletImage;
	private boolean isFriendly;
	private int dmg;
	
	public Bullet(double shotSpeed, double shotDirection, int x, int y, Bitmap bImage, boolean isFriendly){
		this.dmg = 20; //default
		this.isFriendly = isFriendly;
		this.shotSpeed = shotSpeed;
		this.shotDirection = checkDirection(shotDirection);
		this.pos = new Point();
		this.xPos = x;
		this.yPos = y;
		this.pos.x = x;
		this.pos.y = y;
		this.bulletImage = bImage;
	}
	
	public void move(){
		//the calculations below move the bullet in it's given direction, converting a degree number to rads
		double dirInRads = Math.toRadians(shotDirection - 90);
		xPos += shotSpeed*Math.cos(dirInRads);
		yPos += shotSpeed*Math.sin(dirInRads);
		pos.x = (int)xPos;
		pos.y = (int)yPos;
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

	public int getDmg() {
		return dmg;
	}
	
}