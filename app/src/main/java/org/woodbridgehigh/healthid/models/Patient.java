package org.woodbridgehigh.healthid.models;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Patient {
	public final ObservableField<Person> self = new ObservableField<>();
	public final ObservableList<Person> contacts = new ObservableArrayList<>();
	public Patient () {
		self.set(new Person());
	}
	public static Patient fromMinPatient(MinPatient from) {
		Patient to = new Patient();
		to.self.set(from.s);
		for(MinPerson p : from.e) {
			to.contacts.add(Person.fromMinPerson(p));
		}
		return to;
	}
}
