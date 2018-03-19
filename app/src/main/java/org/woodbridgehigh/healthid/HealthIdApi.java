package org.woodbridgehigh.healthid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import org.woodbridgehigh.healthid.models.Patient;

public class HealthIdApi {
	public static final String TEXT_BMP_EXTRA = "textBmp";
	public static final String AZTEC_BMP_EXTRA = "textBmp";
	private Context context;
	public HealthIdApi(Context context) {
		this.context = context;
	}
	public void printPatient(Patient patient) {
		Bitmap textBmp = PersonBmpCoder.patientToTextBmp(patient);
		Bitmap aztecBmp = PersonBmpCoder.patientToAztecBmp(patient);
		Intent intent = new Intent(context, PrintActivity.class);
		intent.putExtra(TEXT_BMP_EXTRA, textBmp);
		intent.putExtra(AZTEC_BMP_EXTRA, aztecBmp);
		context.startActivity(intent);
	}
	public void scanPatient(byte[] rawData) {
		return;
	}
}
