package org.woodbridgehigh.healthid;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class MainActivity extends AppCompatActivity {
	public static final String TAG = "HealthId";
	private CaptureManager capture;
	private DecoratedBarcodeView barcodeScannerView;
	private FloatingActionButton toggleFlash;
	private boolean flashOn = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		barcodeScannerView = (DecoratedBarcodeView)findViewById(R.id.barcode_scanner);
		toggleFlash = (FloatingActionButton) findViewById(R.id.flash_fab);
		if(hasFlash()) {
			barcodeScannerView.setTorchListener(new DecoratedBarcodeView.TorchListener() {
				@Override
				public void onTorchOn() {
					flashOn = true;
				}

				@Override
				public void onTorchOff() {
					flashOn = false;
				}
			});
			toggleFlash.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					switchFlashlight();
				}
			});
		}
		else {
			toggleFlash.hide();
		}

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.create_fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

		capture = new CaptureManager(this, barcodeScannerView);
		capture.initializeFromIntent(getIntent(), savedInstanceState);
		capture.decode();
	}
	private boolean hasFlash() {
		return getApplicationContext().getPackageManager()
				.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
	}
	public void switchFlashlight() {
		if (flashOn) {
			barcodeScannerView.setTorchOff();
		}
		else {
			barcodeScannerView.setTorchOn();
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		capture.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		capture.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		capture.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		capture.onSaveInstanceState(outState);
	}
}
