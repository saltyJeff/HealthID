package org.woodbridgehigh.healthid.models;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

public class Person {
	public String name = "John Doe";
	public String phone = "(123) 456-7890";

	public static Person fromMinPerson(MinPerson from) {
		Person to = new Person();
		to.name = from.n;
		to.phone = from.p;
		return to;
	}
}
