package org.woodbridgehigh.healthid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
		String path = uri.getPath();
		if(!path.contains("https://saltyJeff.github.io/display.html")) {
			Log.i(TAG, path+" was not a known site");
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(browserIntent);
			finish();
			return;
		}
		webView = (WebView)findViewById(R.id.webview);
		webView.setWebViewClient(new WebViewClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/display.html?"+uri.getQuery());
	}
}
