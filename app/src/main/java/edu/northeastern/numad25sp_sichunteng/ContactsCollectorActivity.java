package edu.northeastern.numad25sp_sichunteng;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ContactsCollectorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private final ArrayList<Contact> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_collector);
        recyclerView = findViewById(R.id.recyclerViewContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ContactAdapter(contactsList, this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAddContact);
        fab.setOnClickListener(view -> showAddContactDialog());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showAddContactDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.add_contact, null);

        EditText editName = view.findViewById(R.id.editName);
        EditText editPhone = view.findViewById(R.id.editPhone);
        builder.setView(view)
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = editName.getText().toString().trim();
                    String phone = editPhone.getText().toString().trim();

                    if (!name.isEmpty() && !phone.isEmpty()) {
                        contactsList.add(new Contact(name, phone));
                        adapter.notifyDataSetChanged();

                        Snackbar.make(recyclerView, "Contact Added!", Snackbar.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            if (position != -1) {
                String newName = data.getStringExtra("new_name");
                String newPhone = data.getStringExtra("new_phone");

                contactsList.get(position).setName(newName);
                contactsList.get(position).setPhoneNumber(newPhone);
                adapter.notifyDataSetChanged();

                Snackbar.make(recyclerView, "Contact Updated!", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        ArrayList<String> contactNames = new ArrayList<>();
        ArrayList<String> contactPhones = new ArrayList<>();

        for (Contact contact : contactsList) {
            contactNames.add(contact.getName());
            contactPhones.add(contact.getPhoneNumber());
        }

        outState.putStringArrayList("contact_names", contactNames);
        outState.putStringArrayList("contact_phones", contactPhones);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ArrayList<String> contactNames = savedInstanceState.getStringArrayList("contact_names");
        ArrayList<String> contactPhones = savedInstanceState.getStringArrayList("contact_phones");

        if (contactNames != null && contactPhones != null) {
            contactsList.clear();
            for (int i = 0; i < contactNames.size(); i++) {
                contactsList.add(new Contact(contactNames.get(i), contactPhones.get(i)));
            }
            adapter.notifyDataSetChanged();
        }
    }


}