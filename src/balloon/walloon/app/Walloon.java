package balloon.walloon.app;

<<<<<<< HEAD




import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
=======
import android.app.Activity;
<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Display;
<<<<<<< HEAD
import android.view.KeyEvent;
=======
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
<<<<<<< HEAD
=======
=======
import android.os.Bundle;
>>>>>>> f98daafc26026abbb0587e4bf65ef6cf7f01452f
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
import android.widget.TextView;

public class Walloon extends Activity {
 	private TextView numView;
 	private int num = 0;   //割った数を格納する変数
	private Balloon walloon;
	private Sound sound;
	private TouchWalloon touch_walloon;
<<<<<<< HEAD
	private Tweet tw;
	private Twitter twitter = null;
	private RequestToken requestToken = null;
	private String walloonStatus;
	private Bitmap bitmap;
	private int w,h;
=======
<<<<<<< HEAD
	Canvas canvas;
	Bitmap bitmap;
	int w,h;

=======
>>>>>>> f98daafc26026abbb0587e4bf65ef6cf7f01452f
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
<<<<<<< HEAD
        //まぁ一応書いときます
	    SharedPreferences pref = getSharedPreferences(Const.TWITTER_SETTING, MODE_PRIVATE);
	    //これは必要だから消さないでね
		walloonStatus  = pref.getString(Const.STATUS, "");
		tw = new Tweet(Walloon.this);

        //　画面の大きさを取得する
		Display disp=((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		w=disp.getWidth();
		h=disp.getHeight();
=======
<<<<<<< HEAD
		Display disp=((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		w=disp.getWidth();
		h=disp.getHeight();
=======
>>>>>>> f98daafc26026abbb0587e4bf65ef6cf7f01452f
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
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
<<<<<<< HEAD
		case R.id.tweet_login :
    		
    		//もう設定されているか？
    		if(isConnected(walloonStatus)){
    		    tw.setTweetText(touch_walloon.getScore());
    			tw.startTweet();
    		}else{   			
    			try {
					connectTwitter();
				} catch (TwitterException e) {
					e.printStackTrace();
				}
    		}
			break;
		case R.id.account :    		
    		disconnectTwitter();
			try {
				connectTwitter();
			} catch (TwitterException e) {
				e.printStackTrace();
			}
			break;
		case R.id.exit :
			AlertDialog.Builder ab = new AlertDialog.Builder(this);
			ab.setMessage(R.string.message_exit);
			ab.setPositiveButton(R.string.label_yes, new DialogInterface.OnClickListener() {				
				public void onClick(DialogInterface dialog, int which) {
					finish();				
				}
			});
			ab.setNegativeButton(R.string.label_no, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			ab.show();
			break;
=======
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
<<<<<<< HEAD
		if(resultCode==Const.SET_BACKGROUND_CODE){
			bitmap=loadImage(data.getStringExtra("fn"));
			ImageView iv = (ImageView) findViewById(R.id.backView);
			iv.setImageBitmap(bitmap);
		}else if(resultCode == Const.TWEET){
			AccessToken accessToken = null;
	
			try {
				accessToken = twitter.getOAuthAccessToken(
						requestToken,
						data.getExtras().getString(Const.OAUTH_VERIFIER));
	
		        SharedPreferences pref=getSharedPreferences(Const.TWITTER_SETTING, MODE_PRIVATE);
	
		        SharedPreferences.Editor editor=pref.edit();
		        editor.putString(Const.OAUTH_KEY_TOKEN, accessToken.getToken());
		        editor.putString(Const.OAUTH_KEY_SECRET, accessToken.getTokenSecret());
		        editor.putString(Const.STATUS, Const.AVAILABLE);
	
		        editor.commit();
		        walloonStatus = pref.getString(Const.STATUS, "");
			} catch (TwitterException e) {
				e.printStackTrace();
			}
	
			
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) return true;
		return super.onKeyDown(keyCode, event);
	}
	@Override
=======
		if(resultCode==RESULT_OK){
			bitmap=loadImage(data.getStringExtra("fn"));
			//canvas = new Canvas(bitmap);
			ImageView iv = (ImageView) findViewById(R.id.backView);
			iv.setImageBitmap(bitmap);
		}
	}
	@Override
=======
>>>>>>> f98daafc26026abbb0587e4bf65ef6cf7f01452f
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
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
<<<<<<< HEAD
	
    final private boolean isConnected(String walloonStatus){
		if(walloonStatus != null && walloonStatus.equals(Const.AVAILABLE)){
			return true;
		}else{
			return false;
		}
	}

    private void connectTwitter() throws TwitterException{	  	
		ConfigurationBuilder confbuilder  = new ConfigurationBuilder(); 
		confbuilder.setOAuthConsumerKey(Const.CONSUMER_KEY).setOAuthConsumerSecret(Const.CONSUMER_SECRET); 

		twitter = new TwitterFactory(confbuilder.build()).getInstance();

		// requestTokenもクラス変数。
		try {
			requestToken = twitter.getOAuthRequestToken(Const.CALLBACK_URL);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		  // 認証用URLをインテントにセット。
		  Intent intent = new Intent(this, TwitterLogin.class);
		  intent.putExtra(Const.AUTH_URL, requestToken.getAuthorizationURL());

		  this.startActivityForResult(intent, 0);		
	}
    
    public void disconnectTwitter(){
        SharedPreferences pref=getSharedPreferences(Const.TWITTER_SETTING, MODE_PRIVATE);

        SharedPreferences.Editor editor=pref.edit();
        editor.remove(Const.OAUTH_KEY_TOKEN);
        editor.remove(Const.OAUTH_KEY_SECRET);
        editor.remove(Const.STATUS);
        editor.commit();
        walloonStatus = pref.getString(Const.STATUS, "");
	}

	private Bitmap loadImage(String path){
		boolean landscape=false;
		Bitmap bm;
		// 画像サイズを取得
=======
<<<<<<< HEAD
	Bitmap loadImage(String path){
		boolean landscape=false;
		Bitmap bm;
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;
		BitmapFactory.decodeFile(path, options);
		int oh=options.outHeight;
		int ow=options.outWidth;

<<<<<<< HEAD
		//  縦より横のほうが長かった場合、縦と横を入れ替える
=======
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
		if(ow>oh){		
			landscape=true;	
			oh=options.outWidth;
			ow=options.outHeight;
		}

<<<<<<< HEAD
		//  画像のサイズを調節して読み込む
=======
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
		options.inJustDecodeBounds=false;
		options.inSampleSize=Math.max(ow/w, oh/h);
		bm=BitmapFactory.decodeFile(path);

<<<<<<< HEAD
		//  画像が横向きの場合90度回転させる
=======
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
		if(landscape){
			Matrix matrix = new Matrix();
			matrix.setRotate(90.0f);
			bm=Bitmap.createBitmap(bm, 0, 0,
					bm.getWidth(), bm.getHeight(), matrix, false);
		}

<<<<<<< HEAD
		//Viewと同じ大きさのBitmapに画像を合成する
=======
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
		bm=Bitmap.createScaledBitmap(bm,
				(int)(w), (int)(w*(((double)oh)/((double)ow))), false);
		Bitmap offBitmap=Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas offCanvas=new Canvas(offBitmap);
		offCanvas.drawBitmap(bm, 0, (h-bm.getHeight())/2, null);
		bm=offBitmap;

		return bm;
	}
<<<<<<< HEAD
	public String getUrl(){
		return requestToken.getAuthorizationURL();
	}
=======

=======
>>>>>>> f98daafc26026abbb0587e4bf65ef6cf7f01452f
>>>>>>> ff98aeeae3183774c25a298b1997af5d9f5e11da
}