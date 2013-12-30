package sam.runandgun.weapons;

import java.util.ArrayList;
import java.util.List;

import sam.runandgun.gen.R;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
public class MachineGun extends Weapon {

	public MachineGun(Resources res) {
		super(res);
		this.setBulletImage(BitmapFactory.decodeResource(res, R.drawable.shotmgun));
	}

	public List<Bullet> shoot(int originx, int originy, double originRotationDegrees, boolean friendly) {
		Bullet bullet = new Bullet(3, originRotationDegrees, originx, originy, this.getBulletImage(), friendly);
		List<Bullet> bullets = new ArrayList<Bullet>();
		bullets.add(bullet);
		return bullets;
	}

}
