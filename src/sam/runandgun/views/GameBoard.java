package sam.runandgun.views;

import java.util.ArrayList;
import java.util.List;

import sam.runandgun.enemies.Enemy;
import sam.runandgun.gen.R;
import sam.runandgun.player.Player;
import sam.runandgun.weapons.Bullet;
import sam.runandgun.weapons.MachineGun;
import sam.runandgun.weapons.Weapon;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameBoard extends View{
	
	Player player;
	List<Bullet> bullets = new ArrayList<Bullet>();
	List<Enemy> enemies = new ArrayList<Enemy>();
	
	public GameBoard(Context context, AttributeSet aS) {
		super(context, aS);
		player = new Player(context.getResources());
		player.setWeapon(new MachineGun(context.getResources()));
	}
	
	//handlers
	
	synchronized public void onDraw(Canvas canvas){
		player.drawToCanvas(canvas);	
		for(Bullet b: bullets){
			b.drawToCanvas(canvas);
		}
		for(Enemy e: enemies){
			e.drawToCanvas(canvas);
		}
	}
	
	//logic
	
	public void updateBoard(){
		
		//temp code
		
		if((int)(Math.random()*1000) < 15){ //replace it?
			this.getEnemies().add(new Enemy(BitmapFactory.decodeResource(this.getResources(), R.drawable.enemy), new MachineGun(this.getResources()), (int)(Math.random()*350),-45));
		} //definately replace
		
		for(Enemy e : enemies){ //this to
			if((int)(Math.random()*1000) < 10){
				bullets.addAll(e.fireWeapon());
			}
		} //replace this too
		
		//end temp - put in own function later
		
		this.moveEnemies();
		this.moveBullets();
	}
	
	public void moveBullets(){
		for(Bullet b: bullets){
			b.move();
			Log.e("Point:", b.getPos().x + "/" + b.getPos().y);
		}
	}
	
	public void movePlayerLeft(){
		player.movePlayer(-4);
	}
	
	public void movePlayerRight(){
		player.movePlayer(4);
	}
	
	public void moveEnemies(){
		for(Enemy e: enemies){
			e.move(0);
		}
	}
	

	
	//getters, setters
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}
}
