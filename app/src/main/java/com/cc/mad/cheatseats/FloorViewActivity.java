package com.cc.mad.cheatseats;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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


        if(floorItem.hasGoodCellular()){
            findViewById(R.id.cellular_status).setAlpha(1f);
        }

        if(floorItem.isQuiet()){
            findViewById(R.id.noise_status).setAlpha(1f);
        }

        if(floorItem.allowsFood()){
            findViewById(R.id.food_status).setAlpha(1f);
        }

        if(floorItem.hasOutlets()){
            findViewById(R.id.power_status).setAlpha(1f);
        }

        button_ratingLow = findViewById(R.id.rate_low);
        button_ratingMedium = findViewById(R.id.rate_medium);
        button_ratingHigh = findViewById(R.id.rate_high);

        ImageView img_status = findViewById(R.id.space_status);
        double status = floorItem.getCrowdedness();


        if (status <= Crowdedness.LOWER_THRESHOLD) {
            img_status.setImageResource(R.drawable.crowded_low);
        }

        if (status > Crowdedness.LOWER_THRESHOLD && status < Crowdedness.UPPER_THRESHOLD) {
            img_status.setImageResource(R.drawable.crowded_medium);
        }

        if (status >= Crowdedness.UPPER_THRESHOLD) {
            img_status.setImageResource(R.drawable.crowded_high);
        }


        final String userID = "TEMP";
        final String libraryID = String.valueOf(spaceCardItem.getSpaceName()) + String.valueOf(floorItem.getName());
        button_ratingLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtons(view);
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response data = new Response(0, libraryID, userID);
                dbHandler.addHandler(data);


                double status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
                status = status/dbHandler.getTotalCount(libraryID);
                floorItem.setCrowdedness(status);
                status = floorItem.getCrowdedness();

                floorItem.setAllowsFood(true);

                ImageView img_status = findViewById(R.id.space_status);

                if (status <= Crowdedness.LOWER_THRESHOLD) {
                    img_status.setImageResource(R.drawable.crowded_low);
                }

                if (status > Crowdedness.LOWER_THRESHOLD && status < Crowdedness.UPPER_THRESHOLD) {
                    img_status.setImageResource(R.drawable.crowded_medium);
                }

                if (status >= Crowdedness.UPPER_THRESHOLD) {
                    img_status.setImageResource(R.drawable.crowded_high);
                }

                dbHandler.close();
            }
        });

        button_ratingMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtons(view);
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response data = new Response(1, libraryID, userID);
                dbHandler.addHandler(data);

                double status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
                status = status/dbHandler.getTotalCount(libraryID);
                floorItem.setCrowdedness(status);

                ImageView img_status = findViewById(R.id.space_status);

                if (status <= Crowdedness.LOWER_THRESHOLD) {
                    img_status.setImageResource(R.drawable.crowded_low);
                }

                if (status > Crowdedness.LOWER_THRESHOLD && status < Crowdedness.UPPER_THRESHOLD) {
                    img_status.setImageResource(R.drawable.crowded_medium);
                }

                if (status >= Crowdedness.UPPER_THRESHOLD) {
                    img_status.setImageResource(R.drawable.crowded_high);
                }


                dbHandler.close();
            }
        });
        button_ratingHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtons(view);
                DBHandler dbHandler = new DBHandler(FloorViewActivity.this);
                Response data = new Response(2, libraryID, userID);
                dbHandler.addHandler(data);
                double status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
                status = status/dbHandler.getTotalCount(libraryID);
                floorItem.setCrowdedness(status);

                ImageView img_status = findViewById(R.id.space_status);

                if (status <= Crowdedness.LOWER_THRESHOLD) {
                    img_status.setImageResource(R.drawable.crowded_low);
                }

                if (status > Crowdedness.LOWER_THRESHOLD && status < Crowdedness.UPPER_THRESHOLD) {
                    img_status.setImageResource(R.drawable.crowded_medium);
                }

                if (status >= Crowdedness.UPPER_THRESHOLD) {
                    img_status.setImageResource(R.drawable.crowded_high);
                }

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
        textView_hours.setText(spaceCardItem.getHours());

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

    private void updateButtons(View view) {
        setButtonOutlinesGrey();
        Drawable selected_border = null;

        String id = view.getResources().getResourceEntryName(view.getId());

        if (id.equalsIgnoreCase("rate_low")) {
            selected_border = getResources().getDrawable(R.drawable.button_border_selected_low);
        }

        else if (id.equalsIgnoreCase("rate_medium")) {
            selected_border = getResources().getDrawable(R.drawable.button_border_selected_medium);
        }

        else if (id.equalsIgnoreCase("rate_high")) {
            selected_border = getResources().getDrawable(R.drawable.button_border_selected_high);
        }

        view.setBackground(selected_border);
//        toaster(view);
    }

    private void setButtonOutlinesGrey() {
        Drawable unselected_border_low = getResources().getDrawable(R.drawable.selector_button_outline_low);
        Drawable unselected_border_medium = getResources().getDrawable(R.drawable.selector_button_outline_medium);
        Drawable unselected_border_high = getResources().getDrawable(R.drawable.selector_button_outline_high);

        button_ratingLow.setBackground(unselected_border_low);
        button_ratingMedium.setBackground(unselected_border_medium);
        button_ratingHigh.setBackground(unselected_border_high);
    }

    private void toaster(View view) {
        Toast toast = Toast.makeText(view.getContext(), "       Your rating has been received.       ", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 108);
        toast.show();
    }
}
