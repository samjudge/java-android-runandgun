package sam.runandgun.player;

import java.util.List;

import sam.runandgun.canvasDrawable.canvasDrawable;
import sam.runandgun.gen.R;
import sam.runandgun.weapons.Bullet;
import sam.runandgun.weapons.Weapon;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

public class Player implements canvasDrawable{

	private Point pos;
	private Bitmap playerIcon;
	private Weapon weapon; 
	private boolean canDraw;
	
	public Player(Resources res){
		pos = new Point(177,440);
		canDraw = true;
		playerIcon = BitmapFactory.decodeResource(res, R.drawable.ship);
	}
	
	public List<Bullet> fireWeapon(){
		return this.weapon.shoot(pos.x, pos.y, 0, true);// origin rotation is 0, since it is unimplemented in player
	}
	
	public void drawToCanvas(Canvas c){
		if (this.canDraw){
			c.drawBitmap(this.playerIcon, getPos().x, getPos().y, null); //draw the player
		}
		return;
	}
	
	public Bitmap getPlayerImage(){
		return playerIcon;
	}
	
	public void setXPos(int x){
		pos.x = x;
	}
	
	public void setYPos(int y){
		pos.y = y;
	}
	
	public void movePlayer(int movement){
		this.setXPos(this.getPos().x + movement);
	}
	
	public Point getPos(){
		return pos;
	}
	
	public void setCanDraw(boolean b){
		canDraw = b;
	}
	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}
