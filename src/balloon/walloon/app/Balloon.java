package balloon.walloon.app;

import android.view.View;
import android.widget.ImageView;

public class Balloon{
    private static final int WALLOONN_NUMBER = 5;
	private Walloon _wa;
 	private ImageView[] walloon_touch = new ImageView[WALLOONN_NUMBER];
 	private ImageView kakusi_walloon;
    final int emerge = 25;

	public Balloon(Walloon wa) {
		//Activityを受け取り、imegeを読み込む
		_wa = wa;
		loadImageFile();		
	}
	
	public ImageView getWalloonView(int idx){
		return walloon_touch[idx];
	}
	public ImageView getKakusiWalloonView(){
		return kakusi_walloon;
	}
	public void getKakusiWalloonVisible(int emerge_bomb){
		if(isKakusiWalloonVisible(emerge_bomb))
		  kakusi_walloon.setVisibility(View.VISIBLE);
		else
			kakusi_walloon.setVisibility(View.GONE);
	}
	private boolean isKakusiWalloonVisible(int n){
		boolean flag = true;
		if(n != emerge){  flag = false; }
	    return flag;
	}
	
	private void loadImageFile(){
		//Viewの取得
		walloon_touch[0] = (ImageView) _wa.findViewById(R.id.balloonView1);
		walloon_touch[1] = (ImageView) _wa.findViewById(R.id.balloonView2);
		walloon_touch[2] = (ImageView) _wa.findViewById(R.id.balloonView3);
		walloon_touch[3] = (ImageView) _wa.findViewById(R.id.balloonView4);
		walloon_touch[4] = (ImageView) _wa.findViewById(R.id.balloonView5);
        //隠しわるーんのViewの取得
		kakusi_walloon = (ImageView) _wa.findViewById(R.id.balloonView6);
		
	}
	

}
