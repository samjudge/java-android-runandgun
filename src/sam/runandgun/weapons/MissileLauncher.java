package sam.runandgun.weapons;

import java.util.ArrayList;
import java.util.List;

import sam.runandgun.gen.R;
import sam.runandgun.player.Player;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class MissileLauncher extends Weapon{

	private Player targetPlayer;
	
	public MissileLauncher(Resources res, Player p) {
		super(res);
		targetPlayer = p;
		this.setBulletImage(BitmapFactory.decodeResource(res, R.drawable.shotseeker));
	}

	@Override
	public List<Bullet> shoot(int originx, int originy,	double originRotationDegrees, boolean friendly) {
		Bullet bullet = new Missile(5, originRotationDegrees, originx, originy, this.getBulletImage(), friendly, targetPlayer.getPos()); 
		List<Bullet> bullets = new ArrayList<Bullet>();
		bullets.add(bullet);
		return bullets;
	}
}