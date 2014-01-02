package sam.runandgun.weapons;

import java.util.ArrayList;
import java.util.List;

import sam.runandgun.gen.R;
import sam.runandgun.player.Player;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;

public class LaserGun extends Weapon{

	private double charge = 0;
	private boolean isCharging = false;
	private double shotDirection;
	private Player targetPlayer;
	
	public LaserGun(Resources res, Player targetPlayer) {
		super(res);
		this.targetPlayer = targetPlayer;
		this.setBulletImage(BitmapFactory.decodeResource(res, R.drawable.shotlaser));
	}

	public List<Bullet> shoot(int originx, int originy, double originRotationDegrees, boolean friendly) {
		List<Bullet> bullets = new ArrayList<Bullet>();
		if (charge > 1 && isCharging == false){
			bullets.add(new LaserBullet(8, originRotationDegrees + shotDirection, originx, originy, 10, 10, this.getBulletImage(), friendly));
			charge--;
			return bullets;
		} else if (charge <= 1 && isCharging == false){
			isCharging = true;
		} else if (isCharging == true){
			charge += 0.3;
		}
		if (charge > 20){
			shotDirection = findTargetAngle(originx,originy);
			isCharging = false;
		}
		return null;
	}
	
	public double findTargetAngle(int originx, int originy) {
		Point target = targetPlayer.getPos();
		Point pos = new Point(originx,originy);
		double xDist = pos.x - target.x;
		double yDist = target.y - pos.y;
		if (xDist == 0|| yDist == 0){
			return 0;
		}
		double angle = Math.atan(xDist/yDist);
		Log.e("Xd - ", ""+xDist);
		Log.e("Yd - ", ""+yDist);
		Log.e("Angle - ", ""+angle);
		return Math.toDegrees(angle);
	}

}
