package edu.itstep.myapplic10;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContactDAO contactDAO;
    private AddressDAO addressDAO;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private Button btnAddContact;

    private final ActivityResultLauncher<Intent> addContactLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            Contact addedContact = result.getData().getParcelableExtra("contact");
                            contactAdapter.addContact(addedContact);
                        }
                    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactDAO = App.getInstance().getContactDAO();
        addressDAO = App.getInstance().getAddressDAO();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchContacts();


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                showDeleteConfirmationDialog(viewHolder.getAdapterPosition());
            }
        };

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        btnAddContact = findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(view -> addContactLauncher.launch(new Intent(MainActivity.this, AddContactActivity.class)));
    }

    private void fetchContacts() {
        // Fetch the list of contacts from the database
        List<Contact> contacts = contactDAO.getAll();

        // Set up the RecyclerView adapter
        contactAdapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(contactAdapter);
    }

    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this contact?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> deleteContact(position));
        builder.setNegativeButton("No", (dialogInterface, i) -> {
            contactAdapter.notifyItemChanged(position);
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteContact(int position) {
        Contact contact = contactAdapter.getContactAtPosition(position);
        contactDAO.delete(contact);
        contactAdapter.removeContact(position);

        Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show();
    }
}
