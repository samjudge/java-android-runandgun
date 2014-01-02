package sam.runandgun.weapons;

import android.graphics.Bitmap;

public class LaserBullet extends Bullet{

	public LaserBullet(double shotSpeed, double shotDirection, int x, int y, int w, int h, Bitmap bImage, boolean isFriendly) {
		super(1, shotSpeed, shotDirection, x, y, w, h, bImage, isFriendly);
	}

}
