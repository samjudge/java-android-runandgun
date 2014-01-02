package sam.runandgun.weapons;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;

public abstract class Weapon {
	private Bitmap bulletImage;
	private Resources res;
	public Weapon(Resources res){
		this.res = res;
	}
	
	public abstract List<Bullet> shoot(int originx, int originy, double originRotationDegrees, boolean friendly);
	

	protected Bitmap getBulletImage() {
		return bulletImage;
	}

	protected void setBulletImage(Bitmap bulletImage) {
		this.bulletImage = bulletImage;
	}

	protected Resources getRes() {
		return res;
	}

	protected void setRes(Resources res) {
		this.res = res;
	}

}
