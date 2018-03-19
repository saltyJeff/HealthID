package org.woodbridgehigh.healthid;

//TODO: Alek

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.woodbridgehigh.healthid.models.Person;

public class PersonBmpCoder {
	private static Gson gson = new GsonBuilder().create();
	public static Bitmap patientToTextBmp(String patientStr) {
		return null;
	}
	public static Bitmap patientToAztecBmp(String patientStr) {
		return null;
	}
	public static String aztecToPatient(byte[] data) {
		return null;
	}
	//utilities (you're welcome Alek)
	private Person stringToPerson(String personStr) {
		return gson.fromJson(personStr, Person.class);
	}
}
