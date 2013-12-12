package sam.runandgun.views;

import sam.runandgun.gen.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Controls extends RelativeLayout{
	
	private Button left;
	private Button right;
	private Button fire;
	
	public Controls(Context context, AttributeSet aS) {
		super(context,aS);
		
		LayoutInflater inflater = LayoutInflater.from(context);
		
		inflater.inflate(R.layout.compound_controls, this, true);
		
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

}
