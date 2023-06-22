package com.example.imtrying.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.imtrying.Models.DataClassSchedule;
import com.example.imtrying.Models.Schedule;
import com.example.imtrying.Models.User;
import com.example.imtrying.R;
import com.example.imtrying.firebase.Database;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityEditSchedule extends AppCompatActivity {

    private ValueEventListener listener;
    Button saveButton;
    EditText uploadMorning, uploadMidday, uploadEvening;
    DatabaseReference sheduleNow = FirebaseDatabase.getInstance().getReference("Schedule").child("Main");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shedule);
        uploadMorning = findViewById(R.id.EditMorning);
        uploadMidday = findViewById(R.id.EditMidDay);
        uploadEvening = findViewById(R.id.EditEvening);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> saveData());
        listener = Database.fetchSchedule(schedules -> {
            fillData(schedules.get(0));
        }, () -> {/* OnError */});
    }
    public void saveData() {
        String morning = uploadMorning.getText().toString();
        String midday = uploadMidday.getText().toString();
        String evening = uploadEvening.getText().toString();

        Schedule dataClass = new Schedule(morning, midday, evening);
        FirebaseDatabase.getInstance().getReference("Schedule").child("Main")
                .setValue(dataClass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(ActivityEditSchedule.this, "Saved", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(e -> Toast.makeText(ActivityEditSchedule.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show());

    }
    private void fillData(Schedule schedule) {
        uploadMorning.setText(schedule.getDataMorning());
        uploadMidday.setText(schedule.getDataMidDay());
        uploadEvening.setText(schedule.getDataEvening());
    }
}
