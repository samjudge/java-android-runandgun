package sam.runandgun.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sam.runandgun.enemies.Enemy;
import sam.runandgun.gen.R;
import sam.runandgun.player.Player;
import sam.runandgun.weapons.Bullet;
import sam.runandgun.weapons.LaserGun;
import sam.runandgun.weapons.MachineGun;
import sam.runandgun.weapons.MissileLauncher;
import sam.runandgun.weapons.PhaserGun;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class GameBoard extends View{
	
	Player player;
	List<Bullet> bullets = new ArrayList<Bullet>();
	List<Enemy> enemies = new ArrayList<Enemy>();

	//nested star class for background
	private class Star extends Point {
		Bitmap starImg;
		
		private int speed = 10;
		
		Star(int x, int y, int speed, Bitmap b){
			super (x,y);
			this.speed = speed;
			this.starImg = b;
		}
		
		public void move(){
			this.y += this.speed;
		}
		
		public void drawToCanvas(Canvas c){
			c.drawBitmap(starImg, this.x, this.y, null);
		}
	}
	//maybe move ^^ it out later
	
	List<Star> stars = new ArrayList<Star>();
	
	
	public GameBoard(Context context, AttributeSet aS) {
		super(context, aS);
		this.setBackgroundColor(0);//black
		player = new Player(context.getResources());
		player.setWeapon(new MachineGun(context.getResources()));
	}
	
	//handlers
	
	synchronized public void onDraw(Canvas canvas){
		for(Star s: stars){
			s.drawToCanvas(canvas);
		}
		for(Bullet b: bullets){
			b.drawToCanvas(canvas);
		}
		for(Enemy e: enemies){
			e.drawToCanvas(canvas);
		}
		player.drawToCanvas(canvas);
	}
	
	//logic
	
	public void updateBoard(){
		if (player.isAlive()){
			player.setScore(player.getScore()+1);
		}
		this.generateStars();
		this.generateEnemies();
		this.moveEnemies();
		this.moveBullets();
		this.moveStars();
		detectCollision();
	}
	
	private void moveStars(){
		Iterator<Star> starIterator = stars.iterator();
		while (starIterator.hasNext()){
			Star s = starIterator.next();
			s.move();
			if (s.y > 700){ //if off of the board
				starIterator.remove();
			}
		}
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
	
	
	private void generateStars(){
		if ((int)(Math.random()*1000) < 100){
			stars.add(new Star((int)(Math.random()*350),-45,3+((int)((Math.random()*4)-2)),BitmapFactory.decodeResource(this.getResources(), R.drawable.star)));
		}
	}
	
	private void generateEnemies(){
		int spawnSeed = (int)(Math.random()*5000)+(player.getScore()/2500); //the addition will have to be removed at some point. For now it gives a false difficulty scaling device...
		if (spawnSeed == 4999 || spawnSeed == 4998 || (player.getScore() != 0 && player.getScore()%15000 == 0)) {
			enemies.add(new Enemy(BitmapFactory.decodeResource(this.getResources(), R.drawable.enemygolden), new MachineGun(this.getResources()), (int)(Math.random()*350),-45, 5000, 0,6));
		} else if (spawnSeed > 4990 && player.getScore() > 14000){
			enemies.add(new Enemy(BitmapFactory.decodeResource(this.getResources(), R.drawable.enemylaser), new LaserGun(this.getResources(), player), (int)(Math.random()*350),-45, 500, 850,1));
		} else if(spawnSeed > 4980 && player.getScore() > 6000){
			enemies.add(new Enemy(BitmapFactory.decodeResource(this.getResources(), R.drawable.enemyseeker), new MissileLauncher(this.getResources(), player), (int)(Math.random()*350),-45, 250, 5,2));
		} else if(spawnSeed > 4970 && player.getScore() > 9000){
			enemies.add(new Enemy(BitmapFactory.decodeResource(this.getResources(), R.drawable.enemyphase), new PhaserGun(this.getResources()), (int)(Math.random()*350),-45, 750, 25,3));
		} else if (spawnSeed > 4940) {
			enemies.add(new Enemy(BitmapFactory.decodeResource(this.getResources(), R.drawable.enemymgun), new MachineGun(this.getResources()), (int)(Math.random()*350),-45, 50, 15,2));	
		}
		
		for(Enemy e : enemies){ //this generates random shots
			List<Bullet> shotCheck = e.fireWeapon();
			if(shotCheck != null){
				bullets.addAll(shotCheck);
			}
		}
	}
	
	private void detectCollision(){
		Iterator<Bullet> bulIterator = bullets.iterator();
		//your bullets
		while(bulIterator.hasNext()){
			Bullet b = bulIterator.next();
			if (b.isFriendly() == false){
				if( b.getPos().x+25 > this.player.getPos().x && b.getPos().x+25 < this.player.getPos().x+50){
					if (b.getPos().y+25 > this.player.getPos().y && b.getPos().y+25 < this.player.getPos().y+50){
						this.player.setHealth(this.player.getHealth() - b.getDmg());
						bulIterator.remove();
						break;//do I need this?
					}
				}
			}
		}
		//enemies bullets
		bulIterator = bullets.iterator();
		while(bulIterator.hasNext()){
			Bullet b = bulIterator.next();
			if(b.isFriendly() == true){
				Iterator<Enemy> enemyIterator = enemies.iterator(); //It took so long realize i had to move this here
				while (enemyIterator.hasNext()) {
					Enemy e = enemyIterator.next();
					if(b.getPos().x+25 > e.getPos().x && b.getPos().x+25 < e.getPos().x+50){
						if (b.getPos().y+25 > e.getPos().y && b.getPos().y+25 < e.getPos().y+50){
								this.player.setScore(player.getScore() + e.getDestructionScoreValue());
								bulIterator.remove();
								enemyIterator.remove();
								if (e.getDestructionScoreValue() == 5000) player.setHealth(100);//temp, 5000 because it is saucer score
								break;//again, needed?
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
