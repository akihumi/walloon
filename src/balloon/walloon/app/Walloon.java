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
    
    private static final int WALLOONN_NUMBER = 5;  // 風船画像の数
	private MediaPlayer[] mp = new MediaPlayer[WALLOONN_NUMBER];
	private MediaPlayer[] voice_mp = new MediaPlayer[WALLOONN_NUMBER];
 	private ImageView[] walloon_touch = new ImageView[WALLOONN_NUMBER];
 	private ImageView kakusi_walloon;
 	private MediaPlayer[] laugh_voice = new MediaPlayer[3];
	final Handler mHandler = new Handler();
	private Timer timer = new Timer();
 	private TextView numView;
 	private int num = 0;   //割った数を格納する変数
    private Random rand = new Random();
    private int bomb_flag = 0;
    private int kakusi_bomb = 0;
	private int laugh_change = 0;
	


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //  カウンターの描画
        numView = (TextView) findViewById(R.id.numView1);
        numView.setText(Integer.toString(num));

        //  笑い声の生成
        laugh_voice[0] = MediaPlayer.create(this, R.raw.warai1);
        laugh_voice[1] = MediaPlayer.create(this, R.raw.warai2);
        laugh_voice[2] = MediaPlayer.create(this, R.raw.warai3);
        //  破裂音の生成
        for(int i = 0; i < WALLOONN_NUMBER; i++){
	 	  mp[i] = MediaPlayer.create(this,R.raw.bomb);
          voice_mp[i] = MediaPlayer.create(this, R.raw.bonb_vo); 
        }
        	//ImageViewのIDを取得
	    walloon_touch[0] = (ImageView) findViewById(R.id.balloonView1);
	    walloon_touch[1] = (ImageView) findViewById(R.id.balloonView2);
	    walloon_touch[2] = (ImageView) findViewById(R.id.balloonView3);
	    walloon_touch[3] = (ImageView) findViewById(R.id.balloonView4);
	    walloon_touch[4] = (ImageView) findViewById(R.id.balloonView5);
	 	for(int i = 0; i < WALLOONN_NUMBER; i++)
	 		touchEve(walloon_touch[i], mp[i], voice_mp[i]);
	 	kakusi_walloon = (ImageView) findViewById(R.id.balloonView6);
        touchEve(kakusi_walloon, laugh_voice[2]);	 	
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
				if(num % 50 == 0){
					switch(laugh_change){
					case 0:  laugh_voice[laugh_change].start();
					              laugh_change = 1;  break;
					case 1:  laugh_voice[laugh_change].start();
					              laugh_change = 0;  break;
					}
				}
				//  1/50の確率で隠しわるーん出現
				if(kakusi_bomb == condition){
				  kakusi_walloon.setVisibility(View.VISIBLE);
				}
				//  タッチ後200ミリ秒に再描画
			 	timer.schedule( new TimerTask(){
					@Override
					public void run() {
						iv.post(new Runnable(){
							public void run() {
								if(kakusi_bomb != condition){
									kakusi_walloon.setVisibility(View.GONE);   //わるーんを隠す
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
				if(num % 50 == 0){
					switch(laugh_change){
					case 0:  laugh_voice[laugh_change].start();
					              laugh_change = 1;  break;
					case 1:  laugh_voice[laugh_change].start();
					              laugh_change = 0;  break;
					}
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