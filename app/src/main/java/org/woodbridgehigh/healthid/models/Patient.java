package org.woodbridgehigh.healthid.models;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Patient {
	public Person self = new Person();
	public List<Person> contacts = new ArrayList<>();
	public static Patient fromMinPatient(MinPatient from) {
		Patient to = new Patient();
		to.self = from.s;
		for(MinPerson p : from.e) {
			to.contacts.add(Person.fromMinPerson(p));
		}
		return to;
	}
}
