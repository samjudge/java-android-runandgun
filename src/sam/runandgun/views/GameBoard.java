package sam.runandgun.views;

import java.util.ArrayList;
import java.util.List;

import sam.runandgun.player.Player;
import sam.runandgun.weapons.Bullet;
import sam.runandgun.weapons.MachineGun;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class GameBoard extends View{
	
	Player player;
	List<Bullet> bullets = new ArrayList<Bullet>();
	
	public GameBoard(Context context, AttributeSet aS) {
		super(context, aS);
		player = new Player(context.getResources());
		player.setWeapon(new MachineGun(context.getResources()));
	}
	
	//logic
	
	public void movePlayerLeft(){
		player.setXPos(player.getPos().x - 5);
	}
	
	public void movePlayerRight(){
		player.setXPos(player.getPos().x + 5);
	}
	
	//handlers
	
	synchronized public void onDraw(Canvas canvas){
		player.drawToCanvas(canvas);
		
		for(Bullet b: bullets){
			b.drawToCanvas(canvas);
		}
		
	}

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
}
