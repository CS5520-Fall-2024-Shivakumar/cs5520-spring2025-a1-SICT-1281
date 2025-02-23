package edu.northeastern.numad25sp_sichunteng;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditContact extends AppCompatActivity {
    private EditText editName, editPhone;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);

        Intent intent = getIntent();
        String originalName = intent.getStringExtra("contact_name");
        String originalPhone = intent.getStringExtra("contact_phone");
        position = intent.getIntExtra("position", -1);

        editName.setText(originalName);
        editPhone.setText(originalPhone);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("new_name", editName.getText().toString().trim());
            resultIntent.putExtra("new_phone", editPhone.getText().toString().trim());
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
