package edu.northeastern.numad25sp_sichunteng;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private final ArrayList<Contact> contactsList;
    private final Context context;

    public ContactAdapter(ArrayList<Contact> contactsList, Context context) {
        this.contactsList = contactsList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactsList.get(position);
        holder.name.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());

        holder.itemView.setOnClickListener(view -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + contact.getPhoneNumber()));
            context.startActivity(call);
        });

        holder.itemView.setOnLongClickListener(view -> {
            contactOption(position, contact, holder);
            return true;
        });
    }


    @SuppressLint("NotifyDataSetChanged")
    private void contactOption(int position, Contact contact, ViewHolder holder) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Options")
                .setItems(new String[]{"Edit", "Delete"}, (dialog, which) -> {
                    if (which == 0) {
                        Intent intent = new Intent(context, EditContact.class);
                        intent.putExtra("contact_name", contact.getName());
                        intent.putExtra("contact_phone", contact.getPhoneNumber());
                        intent.putExtra("position", position);
                        ((Activity) context).startActivityForResult(intent, 100);
                    } else if (which == 1) {
                        Contact removedContact = contactsList.get(position);
                        contactsList.remove(position);
                        notifyDataSetChanged();

                        Snackbar snackbar = Snackbar.make(holder.itemView, "Contact Deleted", Snackbar.LENGTH_LONG)
                                .setAction("UNDO", v -> {
                                    contactsList.add(position, removedContact);
                                    notifyDataSetChanged();
                                });
                        snackbar.show();
                    }
                })
                .show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phoneNumber;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            phoneNumber = itemView.findViewById(R.id.textViewPhone);
        }
    }
}
