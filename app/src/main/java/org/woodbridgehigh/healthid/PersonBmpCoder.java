package org.woodbridgehigh.healthid;

//TODO: Alek

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.woodbridgehigh.healthid.models.MinPatient;
import org.woodbridgehigh.healthid.models.Patient;

public class PersonBmpCoder {
	private static Gson gson = new GsonBuilder().create();
	public static Bitmap patientToTextBmp(Patient patient) {

		return null;
	}
	public static Bitmap patientToAztecBmp(Patient patient) {
		return null;
	}
	public static String aztecToPatient(byte[] data) {
		return null;
	}
	//utilities (you're welcome Alek)
	private Patient stringToPatient(String minPatientStr) {
		MinPatient minPatient = gson.fromJson(minPatientStr, MinPatient.class);
		return Patient.fromMinPatient(minPatient);
	}
	private String patientToString(Patient patient) {
		MinPatient minPatient = MinPatient.fromPatient(patient);
		return gson.toJson(minPatient);
	}
}
