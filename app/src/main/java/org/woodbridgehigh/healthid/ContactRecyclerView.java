package org.woodbridgehigh.healthid;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.woodbridgehigh.healthid.databinding.ViewPersondisplayBinding;
import org.woodbridgehigh.healthid.models.Person;

public class ContactRecyclerView extends RecyclerView {
	public ContactRecyclerView(Context context) {
		super(context);
	}
}
class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
	private ObservableList<Person> contacts = new ObservableArrayList<>();
	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ViewPersondisplayBinding binding;

		public ViewHolder(ViewPersondisplayBinding v) {
			super(v.getRoot());
			binding = v;
		}
	}
	public Adapter(ObservableList<Person> contacts) {
		this.contacts = contacts;
		contacts.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Person>>() {
			@Override
			public void onChanged(ObservableList<Person> people) {
				Adapter.this.notifyDataSetChanged();
			}

			@Override
			public void onItemRangeChanged(ObservableList<Person> people, int i, int i1) {
				Adapter.this.notifyItemRangeChanged(i, i1);
			}

			@Override
			public void onItemRangeInserted(ObservableList<Person> people, int i, int i1) {
				Adapter.this.notifyItemRangeInserted(i, i1);
			}

			@Override
			public void onItemRangeMoved(ObservableList<Person> people, int i, int i1, int i2) {
				Adapter.this.notifyDataSetChanged();
			}

			@Override
			public void onItemRangeRemoved(ObservableList<Person> people, int i, int i1) {
				Adapter.this.notifyItemRangeRemoved(i, i1);
			}
		});
	}
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int layoutId) {
		LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
		ViewPersondisplayBinding binding = DataBindingUtil.inflate(inflater, layoutId, viewGroup, false);
		return new ViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
		holder.binding.setPerson(contacts.get(position));
	}

	@Override
	public int getItemCount() {
		return contacts.size();
	}
}