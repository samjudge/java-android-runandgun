package sam.runandgun.weapons;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import sam.runandgun.gen.R;

public class PhaserGun extends Weapon{

	public PhaserGun(Resources res) {
		super(res);
		this.setBulletImage(BitmapFactory.decodeResource(res, R.drawable.shotphase));
	}

	@Override
	public List<Bullet> shoot(int originx, int originy,	double originRotationDegrees, boolean friendly) {
		Bullet bullet1 = new Bullet(4, originRotationDegrees+((Math.random()*50)-25), originx, originy, this.getBulletImage(), friendly); 
		Bullet bullet2 = new Bullet(4, originRotationDegrees+((Math.random()*50)-25), originx, originy, this.getBulletImage(), friendly); 
		List<Bullet> bullets = new ArrayList<Bullet>();
		bullets.add(bullet1);
		bullets.add(bullet2);
		return bullets;
	}

}
