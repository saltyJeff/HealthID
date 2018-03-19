package org.woodbridgehigh.healthid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

public class HealthIdApi {
	private Context context;
	public HealthIdApi(Context context) {
		this.context = context;
	}
	public void printPatient(String patientStr) {
		Bitmap textBmp = PersonBmpCoder.patientToTextBmp(patientStr);
		Bitmap aztecBmp = PersonBmpCoder.patientToAztecBmp(patientStr);
		Intent intent = new Intent(context, PrintActivity.class);
		intent.putExtra("textBmp", textBmp);
		intent.putExtra("aztecBmp", aztecBmp);
		context.startActivity(intent);
	}
	public void scanPatient(byte[] rawData) {
		return;
	}
}
