package org.woodbridgehigh.healthid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class JsInterface {
	private Activity activity;
	public static final String TEXT_BMP_EXTRA = "textBmp";
	public static final String CODE_BMP_EXTRA = "qrBmp";
	public static final String TAG = "JS";
	public JsInterface(Activity a) {
		activity = a;
	}
	@JavascriptInterface
	public void submitPatient(String msg, String patientStr) {
		String fullPath = "https://saltyJeff.github.io/HealthID/display.html?"+msg;
		Log.i(TAG, "printing out: "+fullPath);
		try {
			BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
			Bitmap qrBmp = barcodeEncoder.encodeBitmap(fullPath, BarcodeFormat.QR_CODE, 300, 300);
			Bitmap textBmp = null;
			Intent intent = new Intent(activity, PrintActivity.class);
			intent.putExtra(CODE_BMP_EXTRA, qrBmp);
			intent.putExtra(TEXT_BMP_EXTRA, textBmp);
			activity.startActivity(intent);
		}
		catch(WriterException e) {
			throw new IllegalStateException("How dafuq is the encoder broken");
		}
		activity.finish();
	}
}
