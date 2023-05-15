package com.example.imtrying;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detailTitle);
        detailTime = findViewById(R.id.detailTime);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            detailTitle.setText(bundle.getString("Title"));
            detailDesc.setText(bundle.getString("Description"));
            detailTime.setText(bundle.getString("Time"));
        }
    }
}