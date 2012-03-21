package balloon.walloon.app;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class TouchWalloon {
    private static final int WALLOONN_NUMBER = 5;
	private Timer timer = new Timer();
 	private TextView num_view;
 	private int num = 0;   //割った数を格納する変数
    private Random rand = new Random();
    private int bomb_flag = 0;
    private int kakusi_bomb = 0;
	private Balloon walloon;
	private Sound sound;
	private Walloon wa;
	
	public TouchWalloon(Walloon _wa, Balloon _walloon, Sound _sound){
		wa = _wa;
		walloon = _walloon;
		sound = _sound;
	}
	public void setTouchEvent(){
	 	for(int i = 0; i < WALLOONN_NUMBER; i++)
	 		touchEve(walloon.getWalloonView(i), sound.getBombSound(i, true), sound.getBombSound(i, false));
        touchEve(walloon.getKakusiWalloonView(), sound.getKakusiSound());	 	
	}
	public void init(TextView _view, int _num){
		num_view = (TextView) wa.findViewById(R.id.numView1);
		num = _num;
	}
	//  わるーんにタッチした時に呼ばれるメソッド
	private void touchEve(ImageView img_v, MediaPlayer mp_bomb, MediaPlayer voice_bomb){
		final ImageView iv = img_v;
		final MediaPlayer bomb = mp_bomb;
		final MediaPlayer v_bomb = voice_bomb;
		bomb_flag = rand.nextInt(100);
		kakusi_bomb = rand.nextInt(50);
		img_v.setOnTouchListener(new OnTouchListener(){
			public boolean onTouch(View arg0, MotionEvent arg1) {
				iv.setImageResource(R.drawable.bomb_str);
				//  1/100の確率で破裂音変化、わるーん数の初期化　23は製作者の生まれ日だから
				if(bomb_flag == 23){
					v_bomb.start();
				    num = -1;
			    }else bomb.start();
				num_view.setText(Integer.toString(++num));
				//  わるーん数が50 % num,0に戻った時に笑い声を鳴らす
				sound.getKakusiSound(sound.laughChange(), num);
				//  1/50の確率で隠しわるーん出現
				  walloon.getKakusiWalloonVisible(kakusi_bomb);
				//  タッチ後200ミリ秒に再描画
			 	timer.schedule( new TimerTask(){
					@Override
					public void run() {
						iv.post(new Runnable(){
							public void run() {
								//隠しわるーんのViewを消す
								walloon.getKakusiWalloonVisible(kakusi_bomb);
								iv.setImageResource(R.drawable.balloon);
								bomb_flag = rand.nextInt(100);
								kakusi_bomb = rand.nextInt(50);
							}						
						});
					}
			 	},200);
				return false;
			}
        });
	}
	//隠しわるーん専用のメソッド
	private void touchEve(ImageView img_v, MediaPlayer mp_bomb){
		final ImageView iv = img_v;
		final MediaPlayer bomb = mp_bomb;
		bomb_flag = rand.nextInt(100);
		img_v.setOnTouchListener(new OnTouchListener(){
			public boolean onTouch(View arg0, MotionEvent arg1) {
				iv.setImageResource(R.drawable.bomb_str);
				bomb.start();
				num += 10;
				num_view.setText(Integer.toString(num));
				//  わるーん数が50 % num,0に戻った時に笑い声を鳴らす
					sound.getKakusiSound(sound.laughChange(), num);
				//  タッチ後200ミリ秒に再描画
			 	timer.schedule( new TimerTask(){
					@Override
					public void run() {
						iv.post(new Runnable(){
							public void run() {
								bomb_flag = rand.nextInt(100);
							    iv.setVisibility(View.GONE);
								iv.setImageResource(R.drawable.balloon);								
							}							
						});
					}
			 	},200);
				return false;
			}
        });
	}



}
