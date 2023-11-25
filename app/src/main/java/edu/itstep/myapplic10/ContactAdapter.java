package edu.itstep.myapplic10;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        notifyItemInserted(contacts.size() - 1);
        Log.d("ContactAdapter", "Contacts after adding: " + contacts);
    }
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public Contact getContactAtPosition(int position) {
        return contacts.get(position);
    }

    public void removeContact(int position) {
        contacts.remove(position);
        notifyItemRemoved(position);
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFullName;
        private TextView tvPhone;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }

        public void bind(Contact contact) {
            tvFullName.setText(contact.getFullName());
            tvPhone.setText(contact.getPhone());
        }
    }
}

