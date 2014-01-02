package sam.runandgun.weapons;

import android.graphics.Bitmap;
import android.graphics.Point;

public abstract class AimedShot extends Bullet{
	
	private Point target;
	
	public AimedShot(int dmg, double shotSpeed, double shotDirection, int x, int y, int w, int h, Bitmap bImage, boolean isFriendly, Point target) {
		super(dmg, shotSpeed, shotDirection, x, y, w, h, bImage, isFriendly);
		this.target = target;
	}
	
	public abstract void findTarget();

	public Point getTarget() {
		return target;
	}

	public void setTarget(Point target) {
		this.target = target;
	}

}
