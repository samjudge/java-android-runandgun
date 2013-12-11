package sam.runandgun.weapons;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import sam.runandgun.gen.R;
public class MachineGun extends Weapon {

	public MachineGun(Resources res) {
		super(res);
		this.setBulletImage(BitmapFactory.decodeResource(res, R.drawable.shot));
	}

	public Bullet[] shoot(int originx, int originy, double originRotationDegrees) {
		Bullet[] bullets = new Bullet[0];
		bullets[0] = new Bullet(5, originRotationDegrees, originx, originy, this.getBulletImage(), true); //NOTE: Setting friendly here because it's easy. plz change later
		return bullets;
	}

}
