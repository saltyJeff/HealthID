package org.woodbridgehigh.healthid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;

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
			Log.w(TAG, "intent intended");
			activity.startActivity(intent);
		}
		catch(WriterException e) {
			throw new IllegalStateException("How dafuq is the encoder broken");
		}
		activity.finish();
	}
	public Bitmap createPatientBitmap(Patient p) {
		Log.w(TAG, p.self.name);
		Bitmap bmp = Bitmap.createBitmap(300, 300, Bitmap.Config.RGB_565);
		Log.w(TAG, Boolean.toString(bmp.isMutable()));
		Canvas canvas = new Canvas(bmp);

		//do ur shit here
		String patient = p.self.name;// + p.contacts.get(0);

		Paint drawingPad = new Paint(Paint.ANTI_ALIAS_FLAG);
		drawingPad.setColor(Color.rgb(0,0,0));
		drawingPad.setTextSize(18);

		canvas.drawText(patient, 0,0, drawingPad);
		Log.w(TAG, "patient name drawn: "+patient);
		if(p.contacts.size() > 0) {
			Person contact = p.contacts.get(0);
			drawingPad.setTextSize(12);
			drawingPad.setColor(Color.rgb(31, 0, 0));
			String str = contact.name+"\n"+contact.phone;
			canvas.drawText(str, 0 ,50 , drawingPad);
			Log.w(TAG, "contact info painted");
		}
		return bmp;
	}
}
