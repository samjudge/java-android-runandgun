package sam.runandgun.enemies;

import java.util.List;

import sam.runandgun.weapons.Bullet;
import sam.runandgun.weapons.Weapon;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class Enemy { //I may want to superclass some stuff in this and player...
	private Point pos;
	private Bitmap enemyIcon;
	private Weapon weapon; 
	private int destructionScoreValue;
	private boolean canDraw;
	private int chanceToFire;
	
	public Enemy(Bitmap enemyIcon, Weapon w, int x, int y, int score, int chanceToFire){
		destructionScoreValue = 25; //default
		pos = new Point(x,y);
		this.weapon = w;
		this.enemyIcon = enemyIcon;
		canDraw = true;
		this.destructionScoreValue = score;
		this.chanceToFire = chanceToFire;
	}
	
	public List<Bullet> fireWeapon(){
		if ((Math.random()*1000) < this.chanceToFire){
			return this.weapon.shoot(pos.x, pos.y, 180, false);// origin rotation is 180, since shooting downwards
		}
		return null;
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

	public int getDestructionScoreValue() {
		return destructionScoreValue;
	}

}
