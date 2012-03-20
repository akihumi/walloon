package balloon.walloon.app;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Walloon extends Activity {
    private static final int WALLOONN_NUMBER = 5;
	final Handler mHandler = new Handler();
	private Timer timer = new Timer();
 	private TextView numView;
 	private int num = 0;   //割った数を格納する変数
    private Random rand = new Random();
    private int bomb_flag = 0;
    private int kakusi_bomb = 0;
	private Balloon walloon;
	private Sound sound;
	


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        walloon = new Balloon(Walloon.this);
        sound = new Sound(Walloon.this);
        //  カウンターの描画
        numView = (TextView) findViewById(R.id.numView1);
        numView.setText(Integer.toString(num));
        
	 	for(int i = 0; i < WALLOONN_NUMBER; i++)
	 		touchEve(walloon.getWalloonView(i), sound.getBombSound(i, true), sound.getBombSound(i, false));
        touchEve(walloon.getKakusiWalloonView(), sound.getKakusiSound());	 	
	 }
	  
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	@Override
	protected void onPause() {
		super.onPause();
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	//  わるーんにタッチした時に呼ばれるメソッド
	private void touchEve(ImageView img_v, MediaPlayer mp_bomb, MediaPlayer voice_bomb){
		final ImageView iv = img_v;
		final MediaPlayer bomb = mp_bomb;
		final MediaPlayer v_bomb = voice_bomb;
		final int condition = 25;
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
				numView.setText(Integer.toString(++num));
				//  わるーん数が50 % num,0に戻った時に笑い声を鳴らす
				if(num % 50 == 0 && num != 0){
					sound.getKakusiSound(sound.laughChange());
				}
				//  1/50の確率で隠しわるーん出現
				if(kakusi_bomb == condition){
				  walloon.getKakusiWalloonVisible(View.VISIBLE);
				}
				//  タッチ後200ミリ秒に再描画
			 	timer.schedule( new TimerTask(){
					@Override
					public void run() {
						iv.post(new Runnable(){
							public void run() {
								if(kakusi_bomb != condition){
									walloon.getKakusiWalloonVisible(View.GONE);   //わるーんを隠す
								}
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
				numView.setText(Integer.toString(num));
				//  わるーん数が50 % num,0に戻った時に笑い声を鳴らす
				if(num % 50 == 0 && num != 0){
					sound.getKakusiSound(sound.laughChange());
				}
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