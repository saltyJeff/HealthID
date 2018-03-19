package org.woodbridgehigh.healthid;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.woodbridgehigh.healthid.databinding.ViewPersondisplayBinding;
import org.woodbridgehigh.healthid.models.Person;

public class PersonDisplayFrag extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	private static final String PERSON_ARG = "personArg";

	private Person person;
	public PersonDisplayFrag() {}

	public static PersonDisplayFrag newInstance(Person p) {
		PersonDisplayFrag fragment = new PersonDisplayFrag();
		Bundle args = new Bundle();
		args.putString(PERSON_ARG, new Gson().toJson(p));
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			person = new Gson().fromJson(getArguments().getString(PERSON_ARG), Person.class);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		ViewPersondisplayBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_persondisplay, null, false);
		binding.setPerson(person);
		return binding.getRoot();
	}

}
