package balloon.walloon.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Walloon extends Activity {
 	private TextView numView;
 	private int num = 0;   //割った数を格納する変数
	private Balloon walloon;
	private Sound sound;
	private TouchWalloon touch_walloon;
	Canvas canvas;
	Bitmap bitmap;
	int w,h;

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Display disp=((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		w=disp.getWidth();
		h=disp.getHeight();
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
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi=getMenuInflater();
		mi.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.set_back :
			Intent intent=new Intent(this, FilePicker.class);
			startActivityForResult(intent, 0);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK){
			bitmap=loadImage(data.getStringExtra("fn"));
			//canvas = new Canvas(bitmap);
			ImageView iv = (ImageView) findViewById(R.id.backView);
			iv.setImageBitmap(bitmap);
		}
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
	Bitmap loadImage(String path){
		boolean landscape=false;
		Bitmap bm;
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;
		BitmapFactory.decodeFile(path, options);
		int oh=options.outHeight;
		int ow=options.outWidth;

		if(ow>oh){		
			landscape=true;	
			oh=options.outWidth;
			ow=options.outHeight;
		}

		options.inJustDecodeBounds=false;
		options.inSampleSize=Math.max(ow/w, oh/h);
		bm=BitmapFactory.decodeFile(path);

		if(landscape){
			Matrix matrix = new Matrix();
			matrix.setRotate(90.0f);
			bm=Bitmap.createBitmap(bm, 0, 0,
					bm.getWidth(), bm.getHeight(), matrix, false);
		}

		bm=Bitmap.createScaledBitmap(bm,
				(int)(w), (int)(w*(((double)oh)/((double)ow))), false);
		Bitmap offBitmap=Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas offCanvas=new Canvas(offBitmap);
		offCanvas.drawBitmap(bm, 0, (h-bm.getHeight())/2, null);
		bm=offBitmap;

		return bm;
	}

}