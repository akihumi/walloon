package balloon.walloon.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class TwitterLogin extends Activity{
	private WebView webView;
	Walloon wa = new Walloon();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twitterlogin);

		  webView = (WebView)findViewById(R.id.twitterlogin);
			WebSettings webSettings = webView.getSettings();
			//これで別のユーザーとしてサインインする。が実行できる
			webSettings.setJavaScriptEnabled(true);
			webView.setWebViewClient(new WebViewClient(){

				public void onPageFinished(WebView view, String url) {
					super.onPageFinished(view, url);

					if(url != null && url.startsWith(Const.CALLBACK_URL)){
						try{
						  String[] urlParameters = url.split("\\?")[1].split("&");
						  String oauthToken = "";
						  String oauthVerifier = "";
						  if(urlParameters[0].startsWith(Const.OAUTH_KEY_TOKEN)){
							  oauthToken = urlParameters[0].split("=")[1];
						  }else if(urlParameters[1].startsWith(Const.OAUTH_KEY_TOKEN)){
							  oauthToken = urlParameters[1].split("=")[1];
						  }
						  if(urlParameters[0].startsWith(Const.OAUTH_VERIFIER)){
							  oauthVerifier = urlParameters[0].split("=")[1];
						  }else if(urlParameters[1].startsWith(Const.OAUTH_VERIFIER)){
							  oauthVerifier = urlParameters[1].split("=")[1];
						  }
						
						  Intent intent = getIntent();
						  intent.putExtra(Const.OAUTH_KEY_TOKEN, oauthToken);
						  intent.putExtra(Const.OAUTH_VERIFIER, oauthVerifier);

						  setResult(Const.TWEET, intent);
						}catch(ArrayIndexOutOfBoundsException e){
							Toast.makeText(TwitterLogin.this, R.string.sorry_link, 1).show();
						}
						finish();
					}
				}
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					view.loadUrl(url);
					return true;
				}
			});
			webView.loadUrl(this.getIntent().getExtras().getString(Const.AUTH_URL));
    }
}
