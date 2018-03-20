package org.woodbridgehigh.healthid.models;

public class MinPerson {
	public String n;
	public String p;
	public static MinPerson fromPerson(Person from) {
		MinPerson to = new MinPerson();
		to.n = from.name;
		to.p = from.phone;
		return to;
	}
}
