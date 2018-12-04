package com.cc.mad.cheatseats;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FloorViewActivity extends BaseActivity {

    SpaceCardItem spaceCardItem;
    FloorItem floorItem;
    ImageButton button_ratingLow, button_ratingMedium, button_ratingHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_view);

        Intent intent = getIntent();
        floorItem = (FloorItem) intent.getSerializableExtra("FloorItem");
        spaceCardItem = floorItem.getSpaceCardItem();

        button_ratingLow = findViewById(R.id.rate_low);
        button_ratingMedium = findViewById(R.id.rate_medium);
        button_ratingHigh = findViewById(R.id.rate_high);

        final TextView select_low = findViewById(R.id.select_low);
        final String userID = "TEMP";
        final String libraryID = String.valueOf(spaceCardItem.getSpaceName()) + String.valueOf(floorItem.getName());
        button_ratingLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response data = new Response(0, libraryID, userID);
                dbHandler.addHandler(data);
                String output = "Rated - Low, count: " + dbHandler.getCount(0, libraryID);
                select_low.setText(output);
            }
        });

        button_ratingMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response data = new Response(1, libraryID, userID);
                dbHandler.addHandler(data);
                String output = "Rated - Medium, count: " + dbHandler.getCount(1, libraryID);
                select_low.setText(output);
                dbHandler.close();
            }
        });
        button_ratingHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response data = new Response(2, libraryID, userID);
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

    public void selectAvailability(View v) {
//        System.out.println(v.getId());
//        this.setButtonOutlinesGrey();
        Drawable unselected_border = getResources().getDrawable(R.drawable.selector_button_outline);
        Drawable selected_border = getResources().getDrawable(R.drawable.button_border_selected);

        String id = v.getResources().getResourceEntryName(v.getId());

        if (id.equalsIgnoreCase("rate_low")) {
            button_ratingMedium.setBackground(unselected_border);
            button_ratingHigh.setBackground(unselected_border);
        }

        else if (id.equalsIgnoreCase("rate_medium")) {
            button_ratingLow.setBackground(unselected_border);
            button_ratingHigh.setBackground(unselected_border);
        }

        else if (id.equalsIgnoreCase("rate_high")) {
            button_ratingMedium.setBackground(unselected_border);
            button_ratingLow.setBackground(unselected_border);
        }

        v.setBackground(selected_border);
    }

    public void setButtonOutlinesGrey() {
        Drawable unselected_border = getResources().getDrawable(R.drawable.selector_button_outline);
        button_ratingLow.setBackground(unselected_border);
        button_ratingMedium.setBackground(unselected_border);
        button_ratingHigh.setBackground(unselected_border);
    }
}
