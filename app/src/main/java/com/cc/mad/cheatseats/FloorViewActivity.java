package com.cc.mad.cheatseats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        ImageView image = findViewById(R.id.imageView3);

        textView_spaceName.setText(spaceCardItem.getSpaceName());
        textView_spaceType.setText(spaceCardItem.getSpaceType());
        textView_floorName.setText(floorItem.getName());

        if (spaceCardItem.getFloors().size() == 1) {
            textView_floorName.setVisibility(View.GONE);
            textView_divider.setVisibility(View.GONE);
        }

    }
}
