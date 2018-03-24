package org.woodbridgehigh.healthid;

import com.brother.ptouch.sdk.Printer;
import com.brother.ptouch.sdk.PrinterInfo;
import com.brother.ptouch.sdk.PrinterStatus;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.aztec.AztecWriter;
import com.google.zxing.common.BitMatrix;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.woodbridgehigh.healthid.printprocess.BasePrint;
import org.woodbridgehigh.healthid.printprocess.Common;
import org.woodbridgehigh.healthid.printprocess.ImagePrint;
import org.woodbridgehigh.healthid.printprocess.MsgDialog;
import org.woodbridgehigh.healthid.printprocess.MsgHandle;


public class PrintActivity extends AppCompatActivity {
	Bitmap textBitmap,qrBitmap;
	BasePrint imagePrint;
	MsgHandle mHandle;
	Button printButton;
	MsgDialog mDialog;
	public static final String TAG = "PrintActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_print);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ImageView textImgView = findViewById(R.id.text_bmp);
		ImageView qrImgView = findViewById(R.id.qr_bmp);
		Log.w(TAG, "entering print activity");
		textBitmap = (Bitmap) getIntent().getParcelableExtra(JsInterface.TEXT_BMP_EXTRA);
		qrBitmap = (Bitmap) getIntent().getParcelableExtra(JsInterface.CODE_BMP_EXTRA);
		if (textBitmap == null){
			Snackbar.make(textImgView, "Text bitmap not found", Snackbar.LENGTH_LONG).show();
		}
		else {
			textImgView.setImageBitmap(textBitmap);
			textImgView.setScaleX(2);
			textImgView.setScaleY(2);
		}
		if (qrBitmap == null){
			Snackbar.make(qrImgView, "QR bitmap not found", Snackbar.LENGTH_LONG).show();
		}
		else {
			qrImgView.setImageBitmap(qrBitmap);
			qrImgView.setScaleX(2);
			qrImgView.setScaleY(2);
		}
		Button connectButton = findViewById(R.id.connectButton);
		connectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptConnection();
//				PRINTER.getPrinterStatus();
			}
		});
		printButton = findViewById(R.id.printButton);
		printButton.setEnabled(false);
		printButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				printImages();
			}
		});
		mDialog = new MsgDialog(this);
		mHandle = new MsgHandle(this, mDialog);
		imagePrint = new ImagePrint(this, mHandle, mDialog);
		BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
		imagePrint.setBluetoothAdapter(bluetoothAdapter);
	}
	public void onResume(){
		super.onResume();
		BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
		imagePrint.setBluetoothAdapter(bluetoothAdapter);
	}
	private void printImages() {
		((ImagePrint)(imagePrint)).setFiles(textBitmap, qrBitmap);
		if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
			Log.e("PrintActivity", "Permision not granted");
			ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
		}
		else {
			((ImagePrint)(imagePrint)).setFiles(qrBitmap , textBitmap);
			imagePrint.print();
		}
	}
	public void attemptConnection(){
		startActivityForResult( new Intent(this, Activity_BluetoothPrinterList.class),1);
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == 1)
			if (resultCode == Activity.RESULT_OK){
				imagePrint.setPrinterInfo(data.getStringExtra(Activity_BluetoothPrinterList.IP_EXTRA) , data.getStringExtra(Activity_BluetoothPrinterList.MAC_ADDRESS_EXTRA), data.getStringExtra(Activity_BluetoothPrinterList.PRINTER_EXTRA));
				imagePrint.getPrinterStatus();
				printButton.setEnabled(true);
			}
	}
	BluetoothAdapter getBluetoothAdapter() {
		final BluetoothAdapter bluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
			final Intent enableBtIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			enableBtIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(enableBtIntent);
		}
		return bluetoothAdapter;
	}
	private void sendFile() {
		SendFileThread getTread = new SendFileThread();
		getTread.start();
	}
	private class SendFileThread extends Thread {
		@Override
		public void run() {

			// set info. for printing
			imagePrint.setPrinterInfo();

			// start message
			Message msg = mHandle.obtainMessage(Common.MSG_PRINT_START);
			mHandle.sendMessage(msg);

			imagePrint.getPrinter().startCommunication();



			imagePrint.getPrinter().endCommunication();

			// end message
			mHandle.setResult(imagePrint.showResult());
			mHandle.setBattery(imagePrint.getBattery());
			msg = mHandle.obtainMessage(Common.MSG_PRINT_END);
			mHandle.sendMessage(msg);

		}
	}
}
