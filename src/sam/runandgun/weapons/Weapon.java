package sam.runandgun.weapons;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;

public abstract class Weapon {
	private Bitmap bulletImage;
	private Resources res;
	private double shotDirection;
	private double shotSpeed;
	
	public Weapon(Resources res){
		this.res = res;
	}
	
	public abstract List<Bullet> shoot(int originx, int originy, double originRotationDegrees);
	

	protected Bitmap getBulletImage() {
		return bulletImage;
	}

	protected void setBulletImage(Bitmap bulletImage) {
		this.bulletImage = bulletImage;
	}
	
	protected double getShotSpeed() {
		return shotSpeed;
	}
	
	protected void setShotSpeed(double shotSpeed) {
		this.shotSpeed = shotSpeed;
	}

	protected double getShotDirection() {
		return shotDirection;
	}
	
	protected void setShotDirection(double shotDirection){
		this.shotDirection = shotDirection;
	}

}
