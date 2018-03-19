package org.woodbridgehigh.healthid.models;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

public class Person {
	public final ObservableField<String> name = new ObservableField<>();
	public final ObservableField<String> phone = new ObservableField<>();
	public Person () {
		name.set("John Doe");
		phone.set("(123) 456-789");
	}
	public static Person fromMinPerson(MinPerson from) {
		Person to = new Person();
		to.name.set(from.n);
		to.phone.set(from.p);
		return to;
	}
}
