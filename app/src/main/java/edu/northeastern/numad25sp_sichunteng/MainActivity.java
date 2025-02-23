package edu.northeastern.numad25sp_sichunteng;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
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

        Button b = findViewById(R.id.Button);
        b.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(intent);
        });

        Button b2 = findViewById(R.id.Button2);
        b2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuicCalcActivity.class);
            startActivity(intent);
        });

        Button b3 = findViewById(R.id.Button3);
        b3.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ContactsCollectorActivity.class);
            startActivity(intent);
        });
    }
}