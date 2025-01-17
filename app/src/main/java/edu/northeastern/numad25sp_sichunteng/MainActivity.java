package edu.northeastern.numad25sp_sichunteng;

import android.os.Bundle;
import android.widget.Button;
//import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //TextView t = findViewById(R.id.helloText);
        Button b = findViewById(R.id.Button);
        b.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Name: Sichun Teng\nEmail: teng.sic@northeastern.edu", Toast.LENGTH_LONG).show());
    }
}