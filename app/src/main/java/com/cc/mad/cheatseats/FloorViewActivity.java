package com.cc.mad.cheatseats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FloorViewActivity extends BaseActivity {

    SpaceCardItem spaceCardItem;
    FloorItem floorItem;
    ImageView rate_low, rate_medium, rate_high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_view);
        Intent intent = getIntent();
        floorItem = (FloorItem) intent.getSerializableExtra("FloorItem");
        spaceCardItem = floorItem.getSpaceCardItem();

        final String libraryID = spaceCardItem.getSpaceName() + floorItem.getName();
        final String iid = "TEMP";

        rate_low = findViewById(R.id.rate_low);
        final TextView select_low = findViewById(R.id.select_low);
        rate_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response data = new Response(0, libraryID, iid);
                dbHandler.addHandler(data);
                String output = "Rated - Low, count: " + dbHandler.getCount(0, libraryID);
                select_low.setText(output);
            }
        });
        rate_medium = findViewById(R.id.rate_medium);
        rate_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response data = new Response(1, libraryID, iid);
                dbHandler.addHandler(data);
                String output = "Rated - Medium, count: " + dbHandler.getCount(1, libraryID);
                select_low.setText(output);
            }
        });
        rate_high = findViewById(R.id.rate_high);
        rate_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response data = new Response(2, libraryID, iid);
                dbHandler.addHandler(data);
                String output = "Rated - High, count: " + dbHandler.getCount(2, libraryID);
                select_low.setText(output);
            }
        });

        updateViews();
    }

    private void updateViews() {
        TextView textView_spaceName = findViewById(R.id.space_name);
        TextView textView_spaceType = findViewById(R.id.space_type);
        TextView textView_floorName = findViewById(R.id.floor_name);
        TextView textView_divider = findViewById(R.id.divider);
        TextView textView_hours = findViewById(R.id.hours_text);
        TextView textView_booking = findViewById(R.id.booking_text);
        TextView textView_floorplan = findViewById(R.id.floorplan_text);
        TextView textView_phone = findViewById(R.id.number_text);

        textView_spaceName.setText(spaceCardItem.getSpaceName());
        textView_spaceType.setText(spaceCardItem.getSpaceType());
        textView_floorName.setText(floorItem.getName());
        textView_booking.setText(floorItem.getLinkBooking());
        textView_floorplan.setText(floorItem.getLinkFloorPlan());
        textView_phone.setText(floorItem.getPhoneNumber());

        if (spaceCardItem.getFloors().size() == 1) {
            textView_floorName.setVisibility(View.GONE);
            textView_divider.setVisibility(View.GONE);
        }
    }
}