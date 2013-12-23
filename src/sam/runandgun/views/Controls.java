package sam.runandgun.views;

import sam.runandgun.gen.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Controls extends RelativeLayout{
	
	private Button left;
	private Button right;
	private Button fire;
	private TextView score;
	private ProgressBar health;
	
	public Controls(Context context, AttributeSet aS) {
		super(context,aS);
		
		LayoutInflater inflater = LayoutInflater.from(context);
		
		inflater.inflate(R.layout.compound_controls, this, true);
		
		score = (TextView)this.findViewById(R.id.scoreNumber);
		health = (ProgressBar)this.findViewById(R.id.healthBar);
		left = (Button)this.findViewById(R.id.left);
		right = (Button)this.findViewById(R.id.right);
		fire = (Button)this.findViewById(R.id.fire);
	}

	public Button getLeftButton() {
		return left;
	}

	public Button getRightButton() {
		return right;
	}

	public Button getFireButton() {
		return fire;
	}

	public TextView getScore() {
		return score;
	}

	public ProgressBar getHealth() {
		return health;
	}


}
