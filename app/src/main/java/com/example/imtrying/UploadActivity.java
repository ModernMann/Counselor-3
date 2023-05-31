package com.example.imtrying;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class UploadActivity extends AppCompatActivity {

    Button saveButton;
    EditText uploadTopic, uploadDesc, uploadTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        uploadDesc = findViewById(R.id.uploadDesc);
        uploadTime = findViewById(R.id.uploadTime);
        uploadTopic = findViewById(R.id.uploadTopic);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    public void saveData(){

        String title = uploadTopic.getText().toString();
        String description = uploadDesc.getText().toString();
        String time = uploadTime.getText().toString();
        if (uploadTime.getText().toString() == "" || uploadDesc.getText().toString() == "" || uploadTime.getText().toString() == ""){
            Toast.makeText(this, "Проверьте корректность заполнения полей", Toast.LENGTH_SHORT).show();
        }
        else {
            DataClass dataClass = new DataClass(title, description, time);
            FirebaseDatabase.getInstance().getReference("Candles").child(UUID.randomUUID().toString())
                    .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(UploadActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UploadActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }


    }
}
