package balloon.walloon.app;

import android.view.Menu;

public class Const {
    static final int WALLOONN_NUMBER = 5;
    static final int LAUGH_NUMBER = 3;
    static final int EMERGE= 25;
    static final int COUNT_RESET = 23;
    static final int SET_BACKGROUND_CODE = Menu.FIRST;
    static final int TWEET = Menu.FIRST + 1;
    static final int TYPE = 7;
    static final int[] BALLOONVIEW_ID = {
    	R.id.balloonView1,
    	R.id.balloonView2,
    	R.id.balloonView3,
    	R.id.balloonView4,
    	R.id.balloonView5,
    };
    static final int[] LAUGH_ID = {
    	R.raw.warai1,
    	R.raw.warai2,
    	R.raw.warai3
    };

	static final String CONSUMER_KEY = "自分のキー";
	static final String CONSUMER_SECRET = "自分のシークレット";

	static final String TWITTER_SETTING = "Twitter_seting";
	static final String STATUS = "status";
	static final String AVAILABLE = "available";
	static final String OAUTH_KEY_SECRET = "oauth_token_secret";
	static final String OAUTH_KEY_TOKEN = "oauth_token";
	static final String OAUTH_VERIFIER = "oauth_verifier";

	static final String CALLBACK_URL = "https://twitter.com/";

	static final String AUTH_URL = "auth_url";
	static final String[] TWEET_STRING_HEAD_7 = {
		"ボクは",  "現在の記録は",  "ただいま",  "",
		"なんだかんだで",  "ちなみに",  "僕のスコアは"
	};
	static final String[] TWEET_STRING_TAIL_7 = {
		"をわるーんしたよ！ ", "でございます。 ",  "わるーんしました。 ", "か...ふむ、こんなものか ",
		"なのよ？",  "をわるーんしたけどなにか？",  "なんだよ!"
	};
}
