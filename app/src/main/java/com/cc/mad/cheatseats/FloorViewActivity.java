package com.cc.mad.cheatseats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FloorViewActivity extends BaseActivity {

    ImageView rate_low, rate_medium, rate_high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_view);
        rate_low = (ImageView) findViewById(R.id.rate_low);
        final TextView select_low = findViewById(R.id.select_low);
        rate_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                select_low.setText("Low");
            }
        });
        rate_medium = findViewById(R.id.rate_medium);
        rate_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHander = new DBHandler(FloorViewActivity.this);
                select_low.setText("Medium");
            }
        });
        rate_high = findViewById(R.id.rate_high);
        rate_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                select_low.setText("High");
            }
        });
    }


}