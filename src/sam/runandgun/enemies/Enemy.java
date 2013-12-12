package sam.runandgun.enemies;

import java.util.List;

import sam.runandgun.weapons.Bullet;
import sam.runandgun.weapons.Weapon;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class Enemy {
	private Point pos;
	private Bitmap enemyIcon;
	private Weapon weapon; 
	private boolean canDraw;
	
	public Enemy(Bitmap enemyIcon, Weapon w, int x, int y){
		pos = new Point(x,y);
		this.weapon = w;
		this.enemyIcon = enemyIcon;
		canDraw = true;
	}
	
	public List<Bullet> fireWeapon(){
		return this.weapon.shoot(pos.x, pos.y, 180);// origin rotation is 180, since shooting downwards
	}
	
	public void drawToCanvas(Canvas c){
		if (this.canDraw){
			c.drawBitmap(this.enemyIcon, getPos().x, getPos().y, null);
		}
		return;
	}
	
	public Bitmap getPlayerImage(){
		return enemyIcon;
	}
	
	public void setXPos(int x){
		pos.x = x;
	}
	
	public void setYPos(int y){
		pos.y = y;
	}
	
	public void move(int movement){
		this.setYPos(this.pos.y + 2); //move down
		this.setXPos(this.getPos().x + movement); //move left/right
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
