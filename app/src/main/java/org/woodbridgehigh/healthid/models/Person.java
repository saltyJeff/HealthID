package org.woodbridgehigh.healthid.models;

public class Person {
	public String name;
	public String phone;
	public static Person fromMinPerson(MinPerson from) {
		Person to = new Person();
		to.name = from.n;
		to.phone = from.p;
		return to;
	}
}
