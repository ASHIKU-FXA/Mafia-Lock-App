package com.mafia.lock;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // CHANGE THIS TO YOUR DESIRED PASSWORD
    private static final String CORRECT_PASSWORD = "1234"; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnUnlock = findViewById(R.id.btnUnlock);
        
        btnUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPasswordDialog();
            }
        });
    }

    private void showPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Administrator Access");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        input.setHint("Enter Password");
        builder.setView(input);

        builder.setPositiveButton("UNLOCK", (dialog, which) -> {
            String enteredPassword = input.getText().toString();
            if (enteredPassword.equals(CORRECT_PASSWORD)) {
                Toast.makeText(MainActivity.this, "System Unlocked", Toast.LENGTH_SHORT).show();
                finish(); // Closes the app to simulate unlocking
            } else {
                Toast.makeText(MainActivity.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("CANCEL", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    // Trap the back button so the user can't easily exit the app
    @Override
    public void onBackPressed() {
        // Do nothing
        Toast.makeText(this, "System Locked. Password Required.", Toast.LENGTH_SHORT).show();
    }
}
