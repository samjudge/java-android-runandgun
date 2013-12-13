package sam.runandgun.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sam.runandgun.enemies.Enemy;
import sam.runandgun.gen.R;
import sam.runandgun.player.Player;
import sam.runandgun.weapons.Bullet;
import sam.runandgun.weapons.MachineGun;
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
		this.generateEnemies();
		this.moveEnemies();
		this.moveBullets();
		detectCollision();
	}
	
	private void moveBullets(){
		Iterator<Bullet> bulIterator = bullets.iterator();
		while(bulIterator.hasNext()){
			Bullet b = bulIterator.next();
			b.move();
			if(b.getPos().y > 700){
				bulIterator.remove();
			}
			if(b.getPos().y < 25){
				bulIterator.remove();
			}
		}
	}
	
	public void movePlayerLeft(){
		if(!(player.getPos().x < -25)){
			player.movePlayer(-4);
		}
	}
	
	public void movePlayerRight(){
		if(!(player.getPos().x > 375)){
			player.movePlayer(4);
		}
	}
	
	private void moveEnemies(){
		Iterator<Enemy> eIterator = enemies.iterator();
		while(eIterator.hasNext()){
			Enemy e = eIterator.next();
			e.move(0);
			if (e.getPos().y > 700){
				eIterator.remove();
			}
		}
	}
	
	private void generateEnemies(){
		if((int)(Math.random()*1000) < 15){
			this.getEnemies().add(new Enemy(BitmapFactory.decodeResource(this.getResources(), R.drawable.enemy), new MachineGun(this.getResources()), (int)(Math.random()*350),-45));
		}
		
		for(Enemy e : enemies){ //this generates random shots
			if((int)(Math.random()*1000) < 10){ //this logic should be migrated into the fireWeapon() method in Enemy
				bullets.addAll(e.fireWeapon());
			}
		}
	}
	
	private void detectCollision(){
		Iterator<Bullet> bulIterator = bullets.iterator();
		while(bulIterator.hasNext()){
			Bullet b = bulIterator.next();
			if (b.isFriendly() == false){
				//Log.e("It", "Belongs to them");
				if( b.getPos().x+25 > this.player.getPos().x && b.getPos().x+25 < this.player.getPos().x+50){
					if (b.getPos().y+25 > this.player.getPos().y && b.getPos().y+25 < this.player.getPos().y+50){
						//Log.e("It", "Hits You");
						bulIterator.remove();
					}
				}
			}
		}
		//
		bulIterator = bullets.iterator();
		while(bulIterator.hasNext()){
			Bullet b = bulIterator.next();
			//Log.e("isFriendly?", ""+b.isFriendly());
			if(b.isFriendly() == true){
				Iterator<Enemy> enemyIterator = enemies.iterator(); //fuuuck took so long realize i had to move this
				while (enemyIterator.hasNext()) {
					Enemy e = enemyIterator.next();
					if(b.getPos().x+25 > e.getPos().x && b.getPos().x+25 < e.getPos().x+50){
						if (b.getPos().y+25 > e.getPos().y && b.getPos().y+25 < e.getPos().y+50){
								bulIterator.remove();
								enemyIterator.remove();
								break;
						}
					}
				}
			}
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
