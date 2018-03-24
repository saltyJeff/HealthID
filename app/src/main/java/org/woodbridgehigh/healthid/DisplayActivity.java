package org.woodbridgehigh.healthid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {
	private WebView webView;
	public static final String TAG = "DisplayActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		String extra = getIntent().getStringExtra(MainActivity.PATIENT_EXTRA);
		Uri uri = Uri.parse(extra);
		String path = uri.getEncodedSchemeSpecificPart();
		Log.w(TAG, path);
		if(!path.contains("saltyJeff.github.io/HealthID/display.html")) {
			Toast.makeText(this, path+" was not a known site, opening in web browser", Toast.LENGTH_LONG);
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(browserIntent);
			finish();
			return;
		}
		Log.w(TAG, uri.getQuery());
		Log.w(TAG, uri.getQuery().length()+"");
		webView = (WebView)findViewById(R.id.webview);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if(URLUtil.isNetworkUrl(url)) {
					return false;
				}
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity( intent );
				return true;
			}
		});
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/display.html?"+uri.getQuery());
	}
}
