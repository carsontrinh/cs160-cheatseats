package com.cc.mad.cheatseats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ScrollingActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SpaceCardItem> spaces = new ArrayList<>();
    private Context mContext;

    // For filtering
    private boolean cellularFilterEnabled;
    private boolean quietFilterEnabled;
    private boolean foodFilterEnabled;
    private boolean outletFilterEnabled;

    private Crowdedness crowdedness;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        mContext = ScrollingActivity.this;
        recyclerView = findViewById(R.id.study_space_recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fillSpaces();
        fillSpaceCards();
        myDialog = new Dialog(this);
    }

    public void switchToFloorViewActivity(FloorItem floorItem) {

        System.out.println("================");
        System.out.println(floorItem.getName());

        Intent intent = new Intent(this, FloorViewActivity.class);
        intent.putExtra("FloorItem", floorItem);
        startActivity(intent);
    }

    private void fillSpaces(){

        SpaceCardItem mainStacks, doe, moffitt, kresge, fsm;
        mainStacks = new SpaceCardItem("Main Stacks", "Library");
        doe = new SpaceCardItem("Doe", "Library");
        moffitt = new SpaceCardItem("Moffitt", "Library");
        kresge = new SpaceCardItem("Kresge", "Library");
        fsm = new SpaceCardItem("FSM", "Cafe");

        spaces.addAll(Arrays.asList(mainStacks, doe, moffitt, kresge, fsm));

        // ====================================
        // Main Stacks
        ArrayList<FloorItem> mainStacksFloors = new ArrayList<>(3);
        FloorItem mainStacksFloor1, mainStacksFloor2, mainStacksFloor3;
        mainStacks.setFloors(mainStacksFloors);

        mainStacksFloor1 = new FloorItem(mainStacks);
        mainStacksFloor1.setName("Level B");
        mainStacksFloor1.setHasGoodCellular(false);
        mainStacksFloor1.setQuiet(true);
        mainStacksFloor1.setAllowsFood(false);
        mainStacksFloor1.setHasOutlets(true);
        mainStacksFloor1.setCrowdedness(Crowdedness.LOW);  // TODO: currently hardcoded...will need to fix eventually :c

        mainStacksFloor2 = new FloorItem(mainStacks);
        mainStacksFloor2.setName("Level C");
        mainStacksFloor2.setHasGoodCellular(false);
        mainStacksFloor2.setQuiet(true);
        mainStacksFloor2.setAllowsFood(false);
        mainStacksFloor2.setHasOutlets(true);
        mainStacksFloor2.setCrowdedness(Crowdedness.MEDIUM);

        mainStacksFloor3 = new FloorItem(mainStacks);
        mainStacksFloor3.setName("Level D");
        mainStacksFloor3.setHasGoodCellular(false);
        mainStacksFloor3.setQuiet(true);
        mainStacksFloor3.setAllowsFood(false);
        mainStacksFloor3.setHasOutlets(true);
        mainStacksFloor3.setCrowdedness(Crowdedness.LOW);

        mainStacksFloors.add(mainStacksFloor1);
        mainStacksFloors.add(mainStacksFloor2);
        mainStacksFloors.add(mainStacksFloor3);

        // ====================================
        // Doe
        ArrayList<FloorItem> doeFloors = new ArrayList<>(3);
        FloorItem doeFloor1, doeFloor2, doeFloor3;
        doe.setFloors(doeFloors);

        doeFloor1 = new FloorItem(doe);
        doeFloor1.setName("North Reading Room");
        doeFloor1.setHasGoodCellular(true);
        doeFloor1.setQuiet(true);
        doeFloor1.setAllowsFood(false);
        doeFloor1.setHasOutlets(true);
        doeFloor1.setCrowdedness(Crowdedness.LOW);

        doeFloor2 = new FloorItem(doe);
        doeFloor2.setName("Rosberg Reading Room");
        doeFloor2.setHasGoodCellular(true);
        doeFloor2.setQuiet(true);
        doeFloor2.setAllowsFood(false);
        doeFloor2.setHasOutlets(true);
        doeFloor2.setCrowdedness(Crowdedness.LOW);

        doeFloor3 = new FloorItem(doe);
        doeFloor3.setName("Heyns Reading Room");
        doeFloor3.setHasGoodCellular(true);
        doeFloor3.setQuiet(true);
        doeFloor3.setAllowsFood(false);
        doeFloor3.setHasOutlets(true);
        doeFloor3.setCrowdedness(Crowdedness.LOW);

        doeFloors.add(doeFloor1);
        doeFloors.add(doeFloor2);
        doeFloors.add(doeFloor3);

        // ====================================
        // Moffitt
        ArrayList<FloorItem> moffitFloors = new ArrayList<>(4);
        FloorItem moffitFloor1, moffitFloor2, moffitFloor3, moffitFloor4;
        moffitt.setFloors(moffitFloors);

        moffitFloor1 = new FloorItem(moffitt);
        moffitFloor1.setName("Floor 1");
        moffitFloor1.setHasGoodCellular(false);
        moffitFloor1.setQuiet(false);
        moffitFloor1.setAllowsFood(true);
        moffitFloor1.setHasOutlets(true);
        moffitFloor1.setCrowdedness(Crowdedness.MEDIUM);

        moffitFloor2 = new FloorItem(moffitt);
        moffitFloor2.setName("Floor 3");
        moffitFloor2.setHasGoodCellular(true);
        moffitFloor2.setQuiet(true);
        moffitFloor2.setAllowsFood(true);
        moffitFloor2.setHasOutlets(false);
        moffitFloor2.setCrowdedness(Crowdedness.LOW);

        moffitFloor3 = new FloorItem(moffitt);
        moffitFloor3.setName("Floor 4");
        moffitFloor3.setHasGoodCellular(true);
        moffitFloor3.setQuiet(false);
        moffitFloor3.setAllowsFood(true);
        moffitFloor3.setHasOutlets(true);
        moffitFloor3.setCrowdedness(Crowdedness.HIGH);

        moffitFloor4 = new FloorItem(moffitt);
        moffitFloor4.setName("Floor 5");
        moffitFloor4.setHasGoodCellular(true);
        moffitFloor4.setQuiet(true);
        moffitFloor4.setAllowsFood(true);
        moffitFloor4.setHasOutlets(true);
        moffitFloor4.setCrowdedness(Crowdedness.MEDIUM);

        moffitFloors.add(moffitFloor1);
        moffitFloors.add(moffitFloor2);
        moffitFloors.add(moffitFloor3);
        moffitFloors.add(moffitFloor4);

        // ====================================
        // Kresge
        ArrayList<FloorItem> kresgeFloors = new ArrayList<>(1);
        FloorItem kresgeFloor1, kresgeFloor2;
        kresge.setFloors(kresgeFloors);

        kresgeFloor1 = new FloorItem(kresge);
        kresgeFloor1.setName("Floor 1");
        kresgeFloor1.setHasGoodCellular(false);
        kresgeFloor1.setQuiet(false);
        kresgeFloor1.setAllowsFood(false);
        kresgeFloor1.setHasOutlets(true);
        kresgeFloor1.setCrowdedness(Crowdedness.MEDIUM);

        kresgeFloor2 = new FloorItem(kresge);
        kresgeFloor2.setName("Floor 2");
        kresgeFloor2.setHasGoodCellular(false);
        kresgeFloor2.setQuiet(false);
        kresgeFloor2.setAllowsFood(false);
        kresgeFloor2.setHasOutlets(true);
        kresgeFloor2.setCrowdedness(Crowdedness.HIGH);

        kresgeFloors.add(kresgeFloor1);
        kresgeFloors.add(kresgeFloor2);

        // ====================================
        // Freedom Speech Movement Cafe
        ArrayList<FloorItem> fsmFloors = new ArrayList<>(1);
        FloorItem fsmFloor1;
        fsm.setFloors(fsmFloors);

        fsmFloor1 = new FloorItem(fsm);
        fsmFloor1.setName("Floor 1");
        fsmFloor1.setHasGoodCellular(true);
        fsmFloor1.setQuiet(false);
        fsmFloor1.setAllowsFood(true);
        fsmFloor1.setHasOutlets(true);
        fsmFloor1.setCrowdedness(Crowdedness.HIGH);

        fsmFloors.add(fsmFloor1);
    }

    private void fillSpaceCards() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new SpaceCardAdapter(spaces);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void ShowPopup(View v) {
        TextView txtclose;
        final ToggleButton cellular, quiet, food, outlets;
        Button filter, reset;
        myDialog.setContentView(R.layout.content_custompopup);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        cellular = (ToggleButton) myDialog.findViewById(R.id.cellular); // if we use buttons add them like this and add on onClickListener like below using helpers below
        cellular.setChecked(cellularFilterEnabled);
        cellular.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cellularFilterEnabled = true;
                } else {
                    cellularFilterEnabled = false;
                }
            }
        });

        quiet = (ToggleButton) myDialog.findViewById(R.id.quiet);
        quiet.setChecked(quietFilterEnabled);
        quiet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    quietFilterEnabled = true;
                } else {
                    quietFilterEnabled = false;
                }
            }
        });

        food = (ToggleButton) myDialog.findViewById(R.id.food);
        food.setChecked(foodFilterEnabled);
        food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    foodFilterEnabled = true;
                } else {
                    foodFilterEnabled = false;
                }
            }
        });

        outlets = (ToggleButton) myDialog.findViewById(R.id.outlets);
        outlets.setChecked(outletFilterEnabled);
        outlets.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    outletFilterEnabled = true;
                } else {
                    outletFilterEnabled = false;
                }
            }
        });

        filter = (Button) myDialog.findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFilteredspaces();
                myDialog.dismiss();
            }
        });
        reset = (Button) myDialog.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cellularFilterEnabled = quietFilterEnabled = foodFilterEnabled = outletFilterEnabled = false;
                fillSpaceCards();
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    private void setFilteredspaces () {
        ArrayList<SpaceCardItem> filteredspaces = new ArrayList<>();
        for (SpaceCardItem space : spaces) {
            SpaceCardItem filteredSpace = new SpaceCardItem(space.getSpaceName(), space.getSpaceType());
            ArrayList<FloorItem> filteredFloors = new ArrayList<>();
            filteredSpace.setFloors(filteredFloors);
            for (FloorItem floor : space.getFloors()) {
                if (passesFilter(floor)) {
                    filteredFloors.add(floor);
                }
            }
            if (filteredFloors.isEmpty()) {
                continue;
            } else {
                filteredspaces.add(filteredSpace);
            }
        }
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new SpaceCardAdapter(filteredspaces);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private boolean passesFilter (FloorItem floor) {

        Boolean passes = true;

        if (cellularFilterEnabled) {
            passes &= floor.hasGoodCellular();
        }

        if (quietFilterEnabled) {
            passes &= floor.isQuiet();
        }

        if (foodFilterEnabled) {
            passes &= floor.allowsFood();
        }

        if (outletFilterEnabled) {
            passes &= floor.hasOutlets();
        }

        return passes;
    }

    private void toggleCellularFilter () {
        cellularFilterEnabled = !cellularFilterEnabled;
    }

    private void toggleQuietFilter () {
        quietFilterEnabled = !quietFilterEnabled;
    }

    private void toggleFoodFilter () {
        foodFilterEnabled = !foodFilterEnabled;
    }

    private void toggleOutletFilter () {
        outletFilterEnabled = !outletFilterEnabled;
    }

    private void crowdedSetting () {
        // dependent on button setup for crowdedness
    } // depends on how the crowdedness button is created on the filter screen
}
