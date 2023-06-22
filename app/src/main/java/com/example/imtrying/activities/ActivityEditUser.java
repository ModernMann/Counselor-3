package com.example.imtrying.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imtrying.Models.User;
import com.example.imtrying.R;
import com.example.imtrying.firebase.Database;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityEditUser extends AppCompatActivity {

    private ValueEventListener listener;
    Button saveButton;
    EditText uploadName, uploadTeam, uploadPassword;
    String email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        uploadName = findViewById(R.id.EditName);
        uploadTeam = findViewById(R.id.EditTeam);
        uploadPassword = findViewById(R.id.EditPassword);
        User user = (User) getIntent().getSerializableExtra("user");
        listener = Database.fetchUser(users -> {
            for (User u : users) {
                if (user.getEmail().equals(u.getEmail())
                        && user.getPassword().equals(u.getPassword())) {
                    fillData(u);
                    break;
                }
            }
        }, () -> {/* OnError */});
    }

    public void saveData() {
        String name = uploadName.getText().toString();
        String team = uploadTeam.getText().toString();
        String password = uploadPassword.getText().toString();

        User dataClass = new User(name, team, password, email, phone);
        FirebaseDatabase.getInstance().getReference("Users").push()
                .setValue(dataClass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ActivityEditUser.this, "Saved", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(e -> Toast.makeText(ActivityEditUser.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show());

    }


    private void fillData(User users) {
        uploadName.setText(users.getName());
        uploadTeam.setText(users.getTeam());
        uploadPassword.setText(users.getPassword());
        email = users.getEmail();
        phone = users.getPhone();
    }
}