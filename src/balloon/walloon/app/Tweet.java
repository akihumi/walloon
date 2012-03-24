package balloon.walloon.app;

import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Tweet {
	private String text = null;
    private int num;
    private Walloon wa;
    private Random rand = new Random();
    private int str = 0;
	public Tweet(Walloon _wa){
		wa = _wa;
	}
	public void setTweetText(int _num){
		num = _num;
		str = rand.nextInt(Const.TYPE);
		text = Const.TWEET_STRING_HEAD_7[str] + Integer.toString(num) + "わるーん" + 
		           Const.TWEET_STRING_TAIL_7[str] + " #わるーん";		
	}
	public void startTweet(){
        try {
			tweet();
		} catch (TwitterException e) {
			e.printStackTrace();
		}		
	}
	public void tweet() throws TwitterException {
		// 記録していた設定ファイルを読み込む。		
		SharedPreferences pref = wa.getSharedPreferences(Const.TWITTER_SETTING, Activity.MODE_PRIVATE);
		// 設定ファイルからoauth_tokenとoauth_token_secretを取得。
		String oauthToken  = pref.getString(Const.OAUTH_KEY_TOKEN, "");
		String oauthTokenSecret = pref.getString(Const.OAUTH_KEY_SECRET, "");

		ConfigurationBuilder confbuilder  = new ConfigurationBuilder(); 
		confbuilder.setOAuthAccessToken(oauthToken) 
		.setOAuthAccessTokenSecret(oauthTokenSecret) 
		.setOAuthConsumerKey(Const.CONSUMER_KEY) 
		.setOAuthConsumerSecret(Const.CONSUMER_SECRET); 

		Twitter twitter = new TwitterFactory(confbuilder.build()).getInstance(); 
		// 任意の文字列を引数にして、ツイート。
		Toast.makeText(wa, R.string.tweet_success, 1).show();
		twitter.updateStatus(text);
	 
	}

	
}
