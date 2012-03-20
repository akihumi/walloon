package balloon.walloon.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Walloon extends Activity {
 	private TextView numView;
 	private int num = 0;   //割った数を格納する変数
	private Balloon walloon;
	private Sound sound;
	private TouchWalloon touch_walloon;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //各クラスのインスタンスの生成
        walloon = new Balloon(Walloon.this);
        sound = new Sound(Walloon.this);
        touch_walloon = new TouchWalloon(Walloon.this, walloon, sound);
        //  カウンターの描画
        numView = (TextView) findViewById(R.id.numView1);
        numView.setText(Integer.toString(num));
        // TouchWalloonクラスを初期化して、setTouchEvent()でOnTouchListenerを登録
        touch_walloon.init(numView, num);
        touch_walloon.setTouchEvent();
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
}