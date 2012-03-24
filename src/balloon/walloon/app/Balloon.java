package balloon.walloon.app;

import android.view.View;
import android.widget.ImageView;

public class Balloon{
	private Walloon _wa;
 	private ImageView[] walloon_touch = new ImageView[Const.WALLOONN_NUMBER];
 	private ImageView kakusi_walloon;

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
		if(n != Const.EMERGE){  flag = false; }
	    return flag;
	}	
	private void loadImageFile(){
		//Viewの取得
		for(int i = 0; i < Const.WALLOONN_NUMBER; i++)
			walloon_touch[i] = (ImageView) _wa.findViewById(Const.BALLOONVIEW_ID[i]);
        //隠しわるーんのViewの取得
		kakusi_walloon = (ImageView) _wa.findViewById(R.id.balloonView6);
		
	}
}
