package org.woodbridgehigh.healthid.models;

import java.util.List;

public class Patient {
	public Person self;
	public List<Person> contacts;
	public static Patient fromMinPatient(MinPatient from) {
		Patient to = new Patient();
		to.self = from.s;
		for(MinPerson p : from.e) {
			to.contacts.add(Person.fromMinPerson(p));
		}
		return to;
	}
}
