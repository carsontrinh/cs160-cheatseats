package com.cc.mad.cheatseats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FloorViewActivity extends BaseActivity {

    SpaceCardItem spaceCardItem;
    FloorItem floorItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_view);

        Intent intent = getIntent();
        floorItem = (FloorItem) intent.getSerializableExtra("FloorItem");
        spaceCardItem = floorItem.getSpaceCardItem();

        updateViews();
    }

    private void updateViews() {
        TextView textView_spaceName = findViewById(R.id.space_name);
        TextView textView_spaceType = findViewById(R.id.space_type);
        TextView textView_floorName = findViewById(R.id.floor_name);
        TextView textView_divider = findViewById(R.id.divider);
        TextView textView_hours = findViewById(R.id.hours_text);

        Button button_bookRooms, button_call, button_floorPlans, button_popularTimes;
        button_bookRooms = findViewById(R.id.book_rooms_button);
        button_call = findViewById(R.id.phone_number_button);
        button_floorPlans = findViewById(R.id.floor_plans_button);
        button_popularTimes = findViewById(R.id.popular_times_button);

        button_popularTimes.setEnabled(false);

//        TextView textView_booking = findViewById(R.id.booking_text);
//        TextView textView_floorplan = findViewById(R.id.floorplan_text);
//        TextView textView_phone = findViewById(R.id.number_text);
//
//        textView_spaceName.setText(spaceCardItem.getSpaceName());
//        textView_spaceType.setText(spaceCardItem.getSpaceType());
//        textView_floorName.setText(floorItem.getName());
//        textView_booking.setText(floorItem.getLinkBooking());
//        textView_floorplan.setText(floorItem.getLinkFloorPlan());
//        textView_phone.setText(floorItem.getPhoneNumber());

        if (spaceCardItem.getFloors().size() == 1) {
            textView_floorName.setVisibility(View.GONE);
            textView_divider.setVisibility(View.GONE);
        }

    }
}
