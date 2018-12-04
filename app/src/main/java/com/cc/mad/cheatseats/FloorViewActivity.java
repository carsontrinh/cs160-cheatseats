package com.cc.mad.cheatseats;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        rate_low = (ImageView) findViewById(R.id.rate_low);
        final TextView select_low = findViewById(R.id.select_low);
        final String userID = "TEMP";
        final String libraryID = String.valueOf(spaceCardItem.getSpaceName()) + String.valueOf(floorItem.getName());
        rate_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response toAdd = new Response(0, libraryID, userID);
                dbHandler.addHandler(toAdd);
                select_low.setText(String.valueOf(dbHandler.getCount(0, libraryID)));
                dbHandler.close();
            }
        });
        rate_medium = findViewById(R.id.rate_medium);
        rate_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response toAdd = new Response(1, libraryID, userID);
                dbHandler.addHandler(toAdd);
                select_low.setText(String.valueOf(dbHandler.getCount(1, libraryID)));
                dbHandler.close();
            }
        });
        rate_high = findViewById(R.id.rate_high);
        rate_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response toAdd = new Response(2, libraryID, userID);
                dbHandler.addHandler(toAdd);
                select_low.setText(String.valueOf(dbHandler.getCount(2, libraryID)));
                dbHandler.close();
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

        Button button_bookRooms = findViewById(R.id.book_rooms_button);
        Button button_call = findViewById(R.id.phone_number_button);
        Button button_floorPlans = findViewById(R.id.floor_plans_button);
        Button button_popularTimes = findViewById(R.id.popular_times_button);

        textView_spaceName.setText(spaceCardItem.getSpaceName());
        textView_spaceType.setText(spaceCardItem.getSpaceType());
        textView_floorName.setText(floorItem.getName());

        button_popularTimes.setEnabled(false);  // Not yet implemented

        // Disable buttons if the floor does not have that info available.
        if (floorItem.getLinkBooking().equalsIgnoreCase("")) {
            button_bookRooms.setEnabled(false);
        }
        if (floorItem.getPhoneNumber().equalsIgnoreCase("")) {
            button_call.setEnabled(false);
        }
        if (floorItem.getLinkFloorPlan().equalsIgnoreCase("")) {
            button_floorPlans.setEnabled(false);
        }

        // TODO update hours using the current day

        if (spaceCardItem.getFloors().size() == 1) {
            textView_floorName.setVisibility(View.GONE);
            textView_divider.setVisibility(View.GONE);
        }
    }

    public void phoneOnClick(View view) {
        String phoneNumber = floorItem.getPhoneNumber();
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                "tel", phoneNumber, null));
        startActivity(phoneIntent);
    }

    public void bookRoomsOnClick(View view) {
        openWebURI(Uri.parse(floorItem.getLinkBooking()));
    }

    public void floorPlansOnClick(View view) {
        openWebURI(Uri.parse(floorItem.getLinkFloorPlan()));

    }

    public void openWebURI(Uri uri) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(browserIntent);
    }


}