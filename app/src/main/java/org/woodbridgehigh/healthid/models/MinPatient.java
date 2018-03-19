package org.woodbridgehigh.healthid.models;
import java.util.ArrayList;
import java.util.List;

/**
 * Minified version of the patient
 */

public class MinPatient {
	public Person s;
	public List<MinPerson> e = new ArrayList<>();
	public static MinPatient fromPatient(Patient from) {
		MinPatient to = new MinPatient();
		to.s = from.self.get();
		for(Person p : from.contacts) {
			to.e.add(MinPerson.fromPerson(p));
		}
		return to;
	}
}
