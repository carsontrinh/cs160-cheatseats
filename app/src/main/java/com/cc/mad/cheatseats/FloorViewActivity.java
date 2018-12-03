package com.cc.mad.cheatseats;

import android.content.Intent;
import android.net.Uri;
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
