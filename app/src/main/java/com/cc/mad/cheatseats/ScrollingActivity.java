package com.cc.mad.cheatseats;

import android.content.Context;
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
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SpaceCardItem> spaces = new ArrayList<>();
    private Context mContext;
    // For filtering
    private boolean hasGoodCellular;
    private boolean isQuiet;
    private boolean allowsFood;
    private boolean hasOutlets;
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

        mainStacksFloor1 = new FloorItem();
        mainStacksFloor1.setName("Level B");
        mainStacksFloor1.setHasGoodCellular(true);
        mainStacksFloor1.setQuiet(true);
        mainStacksFloor1.setAllowsFood(false);
        mainStacksFloor1.setHasOutlets(true);
        mainStacksFloor1.setCrowdedness(Crowdedness.LOW);  // TODO: currently hardcoded...will need to fix eventually :c

        mainStacksFloor2 = new FloorItem();
        mainStacksFloor2.setName("Level C");
        mainStacksFloor2.setHasGoodCellular(false);
        mainStacksFloor2.setQuiet(true);
        mainStacksFloor2.setAllowsFood(false);
        mainStacksFloor2.setHasOutlets(true);
        mainStacksFloor2.setCrowdedness(Crowdedness.MEDIUM);

        mainStacksFloor3 = new FloorItem();
        mainStacksFloor3.setName("Level D");
        mainStacksFloor3.setHasGoodCellular(false);
        mainStacksFloor3.setQuiet(true);
        mainStacksFloor3.setAllowsFood(false);
        mainStacksFloor3.setHasOutlets(true);
        mainStacksFloor3.setCrowdedness(Crowdedness.LOW);

        mainStacksFloors.add(mainStacksFloor1);
        mainStacksFloors.add(mainStacksFloor2);
        mainStacksFloors.add(mainStacksFloor3);

        spaces.add(mainStacks);

        // ====================================
        // Doe
        ArrayList<FloorItem> doeFloors = new ArrayList<>(3);
        FloorItem doeFloor1, doeFloor2, doeFloor3;
        doe.setFloors(doeFloors);

        doeFloor1 = new FloorItem();
        doeFloor1.setName("North Reading Room");
        doeFloor1.setHasGoodCellular(true);
        doeFloor1.setQuiet(true);
        doeFloor1.setAllowsFood(false);
        doeFloor1.setHasOutlets(true);
        doeFloor1.setCrowdedness(Crowdedness.LOW);

        doeFloor2 = new FloorItem();
        doeFloor2.setName("Rosberg Reading Room");
        doeFloor2.setHasGoodCellular(true);
        doeFloor2.setQuiet(true);
        doeFloor2.setAllowsFood(false);
        doeFloor2.setHasOutlets(true);
        doeFloor2.setCrowdedness(Crowdedness.LOW);

        doeFloor3 = new FloorItem();
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

        moffitFloor1 = new FloorItem();
        moffitFloor1.setName("Floor 5");
        moffitFloor1.setHasGoodCellular(true);
        moffitFloor1.setQuiet(true);
        moffitFloor1.setAllowsFood(true);
        moffitFloor1.setHasOutlets(true);
        moffitFloor1.setCrowdedness(Crowdedness.MEDIUM);

        moffitFloor2 = new FloorItem();
        moffitFloor2.setName("Floor 4");
        moffitFloor2.setHasGoodCellular(true);
        moffitFloor2.setQuiet(false);
        moffitFloor2.setAllowsFood(true);
        moffitFloor2.setHasOutlets(true);
        moffitFloor2.setCrowdedness(Crowdedness.HIGH);

        moffitFloor3 = new FloorItem();
        moffitFloor3.setName("Floor 3");
        moffitFloor3.setHasGoodCellular(true);
        moffitFloor3.setQuiet(true);
        moffitFloor3.setAllowsFood(true);
        moffitFloor3.setHasOutlets(false);
        moffitFloor3.setCrowdedness(Crowdedness.LOW);

        moffitFloor4 = new FloorItem();
        moffitFloor4.setName("Floor 1");
        moffitFloor4.setHasGoodCellular(false);
        moffitFloor4.setQuiet(false);
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
        FloorItem kresgeFloor1;
        kresge.setFloors(kresgeFloors);

        kresgeFloor1 = new FloorItem();
        kresgeFloor1.setName("Floor 1");
        kresgeFloor1.setHasGoodCellular(false);
        kresgeFloor1.setQuiet(false);
        kresgeFloor1.setAllowsFood(false);
        kresgeFloor1.setHasOutlets(true);
        kresgeFloor1.setCrowdedness(Crowdedness.MEDIUM);

        kresgeFloors.add(kresgeFloor1);

        // ====================================
        // Freedom Speech Movement Cafe
        ArrayList<FloorItem> fsmFloors = new ArrayList<>(1);
        FloorItem fsmFloor1;
        fsm.setFloors(fsmFloors);

        fsmFloor1 = new FloorItem();
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
        Button filter, food, cancel;
        myDialog.setContentView(R.layout.content_custompopup);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        food = (Button) myDialog.findViewById(R.id.food); // if we use buttons add them like this and add on onClickListener like below using helpers below
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodSetting();
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
        cancel = (Button) myDialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                if (this.allowsFood == floor.allowsFood()) { //replace inside of if statement with the filter function below @303
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

    private boolean filter (FloorItem floor) { // filter function that should replace the if statement @286
        if (this.allowsFood == floor.allowsFood() && this.hasGoodCellular == floor.hasGoodCellular()
                && this.isQuiet == floor.isQuiet() && this.hasOutlets == floor.hasOutlets()) {
            return true;
        }
        return false;
    }

    private void foodSetting () {
        allowsFood = !allowsFood;
    }

    private void cellularSetting () {
        hasGoodCellular = !hasGoodCellular;
    }

    private void noiseSetting () {
        isQuiet = !isQuiet;
    }

    private void outletSetting () {
        hasOutlets = !hasOutlets;
    }

    private void crowdedSetting () {
        // dependent on button setup for crowdedness
    } // depends on how the crowdedness button is created on the filter screen
}
