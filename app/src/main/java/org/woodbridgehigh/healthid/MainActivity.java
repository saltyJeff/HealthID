package org.woodbridgehigh.healthid;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	public static final String TAG = "MAIN_ACTIVITY";
	public static final String PATIENT_EXTRA = "patientExtra";
	private DecoratedBarcodeView barcodeScannerView;
	private FloatingActionButton toggleFlash;
	private boolean flashOn = false;
	private BeepManager beepManager;
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
				Intent intent = new Intent(MainActivity.this, CreateActivity.class);
				startActivity(intent);
			}
		});
		//barcode stuff
		List<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE);
		barcodeScannerView.getBarcodeView().setDecoderFactory(new DefaultDecoderFactory(formats));
		beepManager = new BeepManager(this);
		barcodeScannerView.decodeContinuous(new BarcodeCallback() {
			@Override
			public void barcodeResult(BarcodeResult result) {
				Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
				intent.putExtra(PATIENT_EXTRA, result.getText());
				Log.w(TAG, result.getText());
				startActivity(intent);
			}

			@Override
			public void possibleResultPoints(List<ResultPoint> resultPoints) {}
		});
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
		barcodeScannerView.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		barcodeScannerView.pause();
	}
}
