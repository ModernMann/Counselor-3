package com.example.imtrying;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivityGame extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailTime, detailType, detailYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_game);

        detailDesc = findViewById(R.id.detailDescGame);
        detailTitle = findViewById(R.id.detailTitleGame);
        detailTime = findViewById(R.id.detailTimeGame);
        detailType = findViewById(R.id.detailTypeGame);
        detailYear = findViewById(R.id.detailYearGame);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            detailTitle.setText(bundle.getString("Title"));
            detailDesc.setText(bundle.getString("Description"));
            detailTime.setText(bundle.getString("Time"));
            detailYear.setText(bundle.getString("Year"));
            detailType.setText(bundle.getString("Type"));
        }
    }
}