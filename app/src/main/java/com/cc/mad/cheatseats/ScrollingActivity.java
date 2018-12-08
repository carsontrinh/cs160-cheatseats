package com.cc.mad.cheatseats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class ScrollingActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SpaceCardItem> spaces = new ArrayList<>();
    private Context mContext;
    private EditText searchbar;

    // For filtering
    private boolean cellularFilterEnabled;
    private boolean quietFilterEnabled;
    private boolean foodFilterEnabled;
    private boolean outletFilterEnabled;

    private Crowdedness crowdedness;

    Dialog myDialog;

    @Override
    protected void onResume(){
        super.onResume();
        spaces = new ArrayList<>();
        fillSpaces();
        fillSpaceCards();
        setFilteredspaces();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        mContext = ScrollingActivity.this;
        recyclerView = findViewById(R.id.study_space_recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchbar = findViewById(R.id.search_bar);
        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterByText(searchbar.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /*toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterByText();
            }
        });*/
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

        DBHandler dbHandler = new DBHandler(mContext);

        String libraryID;
        double status;

        SpaceCardItem mainStacks, doe, moffitt, kresge, fsm, anthropology, artHistory, bampfa, bancroft;
        SpaceCardItem bioscience, business;
        //SpaceCardItem career, ced, chemistry, data, earth, earthquake, eastasian;
        //SpaceCardItem environmental, ethnic, graduate, graduatetheological, institute, law, lawrence;
        //SpaceCardItem math, media, morrison, music, newspaper, northern, optometry, philosophy;
        //SpaceCardItem physics, robbins, social, southeast, transportation;
        mainStacks = new SpaceCardItem("Main Stacks", "Library");
        doe = new SpaceCardItem("Doe", "Library");
        moffitt = new SpaceCardItem("Moffitt", "Library");
        kresge = new SpaceCardItem("Kresge", "Library");
        fsm = new SpaceCardItem("FSM", "Cafe");
        anthropology = new SpaceCardItem("Anthropology", "Library");
        artHistory = new SpaceCardItem("Art History/Classics", "Library");
        bampfa = new SpaceCardItem("BAMPFA", "Library");
        bancroft = new SpaceCardItem("Bancroft", "Library");
        bioscience = new SpaceCardItem("Bioscience", "Library");
        business = new SpaceCardItem("Business", "Library");
        /*career = new SpaceCardItem("Career Counseling", "Library");
        ced = new SpaceCardItem("CED Visual Resources", "Library");
        chemistry = new SpaceCardItem("Chemistry", "Library");
        data = new SpaceCardItem("Data", "Library");
        earth = new SpaceCardItem("Earth Sciences & Map", "Library");
        earthquake = new SpaceCardItem("Earthquake Engineering", "Library");
        eastasian = new SpaceCardItem("East Asian", "Library");
        environmental = new SpaceCardItem("Environmental Design", "Library");
        ethnic = new SpaceCardItem("Ethnic Studies", "Library");
        graduate = new SpaceCardItem("Graduate Services", "Library");
        graduatetheological = new SpaceCardItem("Graduate Theological Union", "Library");
        institute = new SpaceCardItem("Institute Governmental Studies", "Library");
        law = new SpaceCardItem("Law", "Library");
        lawrence = new SpaceCardItem("Lawrence Berkeley National", "Library");
        math = new SpaceCardItem("Mathematics Statistics", "Library");
        media = new SpaceCardItem("Media Resources", "Library");
        morrison = new SpaceCardItem("Morrison", "Library");
        music = new SpaceCardItem("Music", "Library");
        newspaper = new SpaceCardItem("Newspapers & Microforms", "Library");
        northern = new SpaceCardItem("Northern Regional", "Library");
        optometry = new SpaceCardItem("Optometry", "Library");
        philosophy = new SpaceCardItem("Philosophy", "Library");
        physics = new SpaceCardItem("Physics-Astronomy", "Library");
        robbins = new SpaceCardItem("Robbins Collection", "Library");
        social = new SpaceCardItem("Social Research", "Library");
        southeast = new SpaceCardItem("Southeast Asia", "Library");
        transportation = new SpaceCardItem("Transportation Studies", "Library");*/

        spaces.addAll(Arrays.asList(mainStacks, doe, moffitt, kresge, fsm, anthropology, artHistory,
                bampfa, bancroft, business, bioscience));
                /*career, ced, chemistry, data, earth, earthquake, eastasian)); /*,
                environmental, ethnic, graduate, graduatetheological, institute, law, lawrence, math, media,
                morrison, music, newspaper, northern, optometry, philosophy, physics, robbins, social,
                southeast, transportation));*/

        // ====================================
        // Main Stacks
        ArrayList<FloorItem> mainStacksFloors = new ArrayList<>(3);
        FloorItem mainStacksFloor1, mainStacksFloor2, mainStacksFloor3;
        mainStacks.setFloors(mainStacksFloors);
        mainStacks.setHours("Hours: Wed 12am-12am");
        double mainStacksC = 0;

        mainStacksFloor1 = new FloorItem(mainStacks);
        mainStacksFloor1.setName("Level B");
        mainStacksFloor1.setHasGoodCellular(false);
        mainStacksFloor1.setQuiet(true);
        mainStacksFloor1.setAllowsFood(false);
        mainStacksFloor1.setHasOutlets(true);
        //mainStacksFloor1.setCrowdedness(0);
        mainStacksFloor1.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/stacks_b.pdf");
        mainStacksFloor1.setLinkBooking("http://berkeley.libcal.com/booking/gardner");
        mainStacksFloor1.setPhoneNumber("510-643-4331");
        libraryID = String.valueOf(mainStacks.getSpaceName()) + String.valueOf(mainStacksFloor1.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        if(Double.isNaN(status)){
            status = 0;
        }
        mainStacksC += status;
        mainStacksFloor1.setCrowdedness(status);

        mainStacksFloor2 = new FloorItem(mainStacks);
        mainStacksFloor2.setName("Level C");
        mainStacksFloor2.setHasGoodCellular(false);
        mainStacksFloor2.setQuiet(true);
        mainStacksFloor2.setAllowsFood(false);
        mainStacksFloor2.setHasOutlets(true);
        //mainStacksFloor2.setCrowdedness(0);
        mainStacksFloor2.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/stacks_c.pdf");
        mainStacksFloor2.setLinkBooking("http://berkeley.libcal.com/booking/gardner");
        mainStacksFloor2.setPhoneNumber("510-643-4331");
        libraryID = String.valueOf(mainStacks.getSpaceName()) + String.valueOf(mainStacksFloor2.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        if(Double.isNaN(status)){
            status = 0;
        }
        mainStacksC += status;
        mainStacksFloor2.setCrowdedness(status);

        mainStacksFloor3 = new FloorItem(mainStacks);
        mainStacksFloor3.setName("Level D");
        mainStacksFloor3.setHasGoodCellular(false);
        mainStacksFloor3.setQuiet(true);
        mainStacksFloor3.setAllowsFood(false);
        mainStacksFloor3.setHasOutlets(true);
        //mainStacksFloor3.setCrowdedness(0);
        mainStacksFloor3.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/stacks_d.pdf");
        mainStacksFloor3.setLinkBooking("http://berkeley.libcal.com/booking/gardner");
        mainStacksFloor3.setPhoneNumber("510-643-4331");
        libraryID = String.valueOf(mainStacks.getSpaceName()) + String.valueOf(mainStacksFloor3.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        if(Double.isNaN(status)){
            status = 0;
        }
        mainStacksC += status;
        mainStacksFloor3.setCrowdedness(status);

        mainStacksC /= 3;

        mainStacksFloors.add(mainStacksFloor1);
        mainStacksFloors.add(mainStacksFloor2);
        mainStacksFloors.add(mainStacksFloor3);

        mainStacks.setCrowdedness(mainStacksC);

        // ====================================
        // Doe
        ArrayList<FloorItem> doeFloors = new ArrayList<>(3);
        FloorItem doeFloor1, doeFloor2, doeFloor3;
        doe.setFloors(doeFloors);
        doe.setHours("Hours: Wed 8am-9pm");
        double doeC = 0;

        doeFloor1 = new FloorItem(doe);
        doeFloor1.setName("North Reading Room");
        doeFloor1.setHasGoodCellular(true);
        doeFloor1.setQuiet(true);
        doeFloor1.setAllowsFood(false);
        doeFloor1.setHasOutlets(true);
        //doeFloor1.setCrowdedness(0);
        doeFloor1.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/doe_2.pdf");
        doeFloor1.setLinkBooking("");
        doeFloor1.setPhoneNumber("510-642-6657");
        libraryID = String.valueOf(doe.getSpaceName()) + String.valueOf(doeFloor1.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        doeFloor1.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        doeC += status;

        doeFloor2 = new FloorItem(doe);
        doeFloor2.setName("Rosberg Reading Room");
        doeFloor2.setHasGoodCellular(true);
        doeFloor2.setQuiet(true);
        doeFloor2.setAllowsFood(false);
        doeFloor2.setHasOutlets(true);
        //doeFloor2.setCrowdedness(0);
        doeFloor2.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/rosberg.pdf");
        doeFloor2.setLinkBooking("");
        doeFloor2.setPhoneNumber("510-642-6657");
        libraryID = String.valueOf(doe.getSpaceName()) + String.valueOf(doeFloor2.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        doeFloor2.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        doeC += status;

        doeFloor3 = new FloorItem(doe);
        doeFloor3.setName("Heyns Reading Room");
        doeFloor3.setHasGoodCellular(true);
        doeFloor3.setQuiet(true);
        doeFloor3.setAllowsFood(false);
        doeFloor3.setHasOutlets(true);
        //doeFloor3.setCrowdedness(0);
        doeFloor3.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/heyns.pdf");
        doeFloor3.setLinkBooking("");
        doeFloor3.setPhoneNumber("510-642-6657");
        libraryID = String.valueOf(doe.getSpaceName()) + String.valueOf(doeFloor3.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        doeFloor3.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        doeC += status;

        doeC /= 3;

        doeFloors.add(doeFloor1);
        doeFloors.add(doeFloor2);
        doeFloors.add(doeFloor3);
        doe.setCrowdedness(doeC);

        // ====================================
        // Moffitt
        ArrayList<FloorItem> moffitFloors = new ArrayList<>(4);
        FloorItem moffitFloor1, moffitFloor2, moffitFloor3, moffitFloor4;
        moffitt.setFloors(moffitFloors);
        moffitt.setHours("Hours: Wed 12am-12am");
        double moffitC = 0;

        moffitFloor1 = new FloorItem(moffitt);
        moffitFloor1.setName("Floor 1");
        moffitFloor1.setHasGoodCellular(false);
        moffitFloor1.setQuiet(false);
        moffitFloor1.setAllowsFood(true);
        moffitFloor1.setHasOutlets(true);
        //moffitFloor1.setCrowdedness(0);
        moffitFloor1.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/moff-floorplan-1.pdf");
        moffitFloor1.setLinkBooking("");
        moffitFloor1.setPhoneNumber("510-642-5072");
        libraryID = String.valueOf(moffitt.getSpaceName()) + String.valueOf(moffitFloor1.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        moffitFloor1.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        moffitC += status;

        moffitFloor2 = new FloorItem(moffitt);
        moffitFloor2.setName("Floor 3");
        moffitFloor2.setHasGoodCellular(true);
        moffitFloor2.setQuiet(true);
        moffitFloor2.setAllowsFood(true);
        moffitFloor2.setHasOutlets(false);
        //moffitFloor2.setCrowdedness(0);
        moffitFloor2.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/moff-floorplan-3.pdf");
        moffitFloor2.setLinkBooking("");
        moffitFloor2.setPhoneNumber("510-642-5072");
        libraryID = String.valueOf(moffitt.getSpaceName()) + String.valueOf(moffitFloor2.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        moffitFloor2.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        moffitC += status;

        moffitFloor3 = new FloorItem(moffitt);
        moffitFloor3.setName("Floor 4");
        moffitFloor3.setHasGoodCellular(true);
        moffitFloor3.setQuiet(false);
        moffitFloor3.setAllowsFood(true);
        moffitFloor3.setHasOutlets(true);
        //moffitFloor3.setCrowdedness(0);
        moffitFloor3.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/moff-floorplan-4.pdf");
        moffitFloor3.setLinkBooking("http://berkeley.libcal.com/booking/moffitt-4");
        moffitFloor3.setPhoneNumber("510-642-5072");
        libraryID = String.valueOf(moffitt.getSpaceName()) + String.valueOf(moffitFloor3.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        moffitFloor3.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        moffitC += status;

        moffitFloor4 = new FloorItem(moffitt);
        moffitFloor4.setName("Floor 5");
        moffitFloor4.setHasGoodCellular(true);
        moffitFloor4.setQuiet(true);
        moffitFloor4.setAllowsFood(true);
        moffitFloor4.setHasOutlets(true);
        //moffitFloor4.setCrowdedness(0);
        moffitFloor4.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/moff-floorplan-5.pdf");
        moffitFloor4.setLinkBooking("http://berkeley.libcal.com/booking/moffitt-5");
        moffitFloor4.setPhoneNumber("510-642-5072");
        libraryID = String.valueOf(moffitt.getSpaceName()) + String.valueOf(moffitFloor4.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        moffitFloor4.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        moffitC += status;

        moffitC /= 4;

        moffitFloors.add(moffitFloor1);
        moffitFloors.add(moffitFloor2);
        moffitFloors.add(moffitFloor3);
        moffitFloors.add(moffitFloor4);

        moffitt.setCrowdedness(moffitC);

        // ====================================
        // Kresge
        ArrayList<FloorItem> kresgeFloors = new ArrayList<>(1);
        FloorItem kresgeFloor1, kresgeFloor2;
        kresge.setFloors(kresgeFloors);
        kresge.setHours("Hours: Wed 9am-12am");
        double kresgeC = 0;

        kresgeFloor1 = new FloorItem(kresge);
        kresgeFloor1.setName("Floor 1");
        kresgeFloor1.setHasGoodCellular(false);
        kresgeFloor1.setQuiet(false);
        kresgeFloor1.setAllowsFood(false);
        kresgeFloor1.setHasOutlets(true);
        //kresgeFloor1.setCrowdedness(0);
        kresgeFloor1.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/engi-floorplan.pdf");
        kresgeFloor1.setLinkBooking("http://berkeley.libcal.com/booking/engi");
        kresgeFloor1.setPhoneNumber("510-642-3366");
        libraryID = String.valueOf(kresge.getSpaceName()) + String.valueOf(kresgeFloor1.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        kresgeFloor1.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        kresgeC += status;

        kresgeFloor2 = new FloorItem(kresge);
        kresgeFloor2.setName("Floor 2");
        kresgeFloor2.setHasGoodCellular(false);
        kresgeFloor2.setQuiet(false);
        kresgeFloor2.setAllowsFood(false);
        kresgeFloor2.setHasOutlets(true);
        kresgeFloor2.setCrowdedness(90);
        kresgeFloor2.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/engi-floorplan.pdf");
        kresgeFloor2.setLinkBooking("http://berkeley.libcal.com/booking/engi");
        kresgeFloor2.setPhoneNumber("510-642-3366");
        libraryID = String.valueOf(kresge.getSpaceName()) + String.valueOf(kresgeFloor2.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        kresgeFloor2.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        kresgeC += status;

        kresgeFloors.add(kresgeFloor1);
        kresgeFloors.add(kresgeFloor2);

        kresgeC /= 2;

        kresge.setCrowdedness(kresgeC);

        // ====================================
        // Freedom Speech Movement Cafe
        ArrayList<FloorItem> fsmFloors = new ArrayList<>(1);
        FloorItem fsmFloor1;
        fsm.setFloors(fsmFloors);
        fsm.setHours("Hours: Wed 6:30am-2am");

        fsmFloor1 = new FloorItem(fsm);
        fsmFloor1.setName("Floor 1");
        fsmFloor1.setHasGoodCellular(true);
        fsmFloor1.setQuiet(false);
        fsmFloor1.setAllowsFood(true);
        fsmFloor1.setHasOutlets(true);
        //fsmFloor1.setCrowdedness(0);
        fsmFloor1.setLinkFloorPlan("");
        fsmFloor1.setLinkBooking("");
        fsmFloor1.setPhoneNumber("510-666-0805");
        libraryID = String.valueOf(fsm.getSpaceName()) + String.valueOf(fsmFloor1.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        if(Double.isNaN(status)){
            status = 0;
        }
        fsmFloor1.setCrowdedness(status);

        fsm.setCrowdedness(status);

        fsmFloors.add(fsmFloor1);

        // ====================================
        // Anthropology
        ArrayList<FloorItem> anthropologyFloors = new ArrayList<>(3);
        FloorItem anthropologyFloor1;
        anthropology.setFloors(anthropologyFloors);
        anthropology.setHours("Hours: Wed 9am-8pm");

        anthropologyFloor1 = new FloorItem(anthropology);
        anthropologyFloor1.setName("Floor 1");
        anthropologyFloor1.setHasGoodCellular(true);
        anthropologyFloor1.setQuiet(true);
        anthropologyFloor1.setAllowsFood(false);
        anthropologyFloor1.setHasOutlets(true);
        anthropologyFloor1.setLinkFloorPlan("");
        anthropologyFloor1.setLinkBooking("");
        anthropologyFloor1.setPhoneNumber("510-642-2400");
        libraryID = String.valueOf(anthropology.getSpaceName()) + String.valueOf(anthropologyFloor1.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        if(Double.isNaN(status)){
            status = 0;
        }
        anthropologyFloor1.setCrowdedness(status);
        anthropology.setCrowdedness(status);

        anthropologyFloors.add(anthropologyFloor1);

        // ====================================
        // Art History/Classic
        ArrayList<FloorItem> artHistoryFloors = new ArrayList<>(3);
        FloorItem artHistoryFloor1;
        artHistory.setFloors(artHistoryFloors);
        artHistory.setHours("Hours: Wed 9am-9pm");

        artHistoryFloor1 = new FloorItem(artHistory);
        artHistoryFloor1.setName("Floor 1");
        artHistoryFloor1.setHasGoodCellular(true);
        artHistoryFloor1.setQuiet(true);
        artHistoryFloor1.setAllowsFood(false);
        artHistoryFloor1.setHasOutlets(true);
        artHistoryFloor1.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/doe_3.pdf");
        artHistoryFloor1.setLinkBooking("");
        artHistoryFloor1.setPhoneNumber("510-642-7361");
        libraryID = String.valueOf(artHistory.getSpaceName()) + String.valueOf(artHistoryFloor1.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        if(Double.isNaN(status)){
            status = 0;
        }
        artHistoryFloor1.setCrowdedness(status);
        artHistory.setCrowdedness(status);

        artHistoryFloors.add(artHistoryFloor1);

        // ====================================
        // BAMPFA
        ArrayList<FloorItem> bampfaFloors = new ArrayList<>(3);
        FloorItem bampfaFloor1, bampfaFloor2, bampfaFloor3;
        bampfa.setFloors(bampfaFloors);
        bampfa.setHours("Hours: Wed 1pm-5pm");
        double bampfaC = 0;

        bampfaFloor1 = new FloorItem(bampfa);
        bampfaFloor1.setName("Ground Level");
        bampfaFloor1.setHasGoodCellular(true);
        bampfaFloor1.setQuiet(true);
        bampfaFloor1.setAllowsFood(false);
        bampfaFloor1.setHasOutlets(false);
        bampfaFloor1.setLinkFloorPlan("https://www.archdaily.com/779090/uc-berkeley-art-" +
                "museum-and-pacific-film-archive-diller-scofidio-plus-renfro/5674a6f0e58ece4176000071-" +
                "uc-berkeley-art-museum-and-pacific-film-archive-diller-scofidio-plus-renfro-ground-floor-plan");
        bampfaFloor1.setLinkBooking("https://bampfa.org/about/facility-rental");
        bampfaFloor1.setPhoneNumber("510-642-1437");
        libraryID = String.valueOf(bampfa.getSpaceName()) + String.valueOf(bampfaFloor1.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        bampfaFloor1.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        bampfaC += status;

        bampfaFloor2 = new FloorItem(bampfa);
        bampfaFloor2.setName("Lower Floor");
        bampfaFloor2.setHasGoodCellular(true);
        bampfaFloor2.setQuiet(false);
        bampfaFloor2.setAllowsFood(false);
        bampfaFloor2.setHasOutlets(false);
        bampfaFloor2.setCrowdedness(90);
        bampfaFloor2.setLinkFloorPlan("https://www.archdaily.com/779090/uc-berkeley-art" +
                "-museum-and-pacific-film-archive-diller-scofidio-plus-renfro/" +
                "5674a700e58ece4176000072-uc-berkeley-art-museum-and-pacific-film-archive" +
                "-diller-scofidio-plus-renfro-lower-floor-plan");
        bampfaFloor2.setLinkBooking("https://bampfa.org/about/facility-rental");
        bampfaFloor2.setPhoneNumber("510-642-1437");
        libraryID = String.valueOf(bampfa.getSpaceName()) + String.valueOf(bampfaFloor2.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        bampfaFloor2.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        bampfaC += status;

        bampfaFloor3 = new FloorItem(bampfa);
        bampfaFloor3.setName("Upper Floor");
        bampfaFloor3.setHasGoodCellular(true);
        bampfaFloor3.setQuiet(false);
        bampfaFloor3.setAllowsFood(true);
        bampfaFloor3.setHasOutlets(true);
        bampfaFloor3.setCrowdedness(90);
        bampfaFloor3.setLinkFloorPlan("https://www.archdaily.com/779090/uc-berkeley-art" +
                "-museum-and-pacific-film-archive-diller-scofidio-plus-renfro/" +
                "5674a70ee58ece4176000073-uc-berkeley-art-museum-and-pacific-film-archive" +
                "-diller-scofidio-plus-renfro-second-floor-plan");
        bampfaFloor3.setLinkBooking("https://bampfa.org/about/facility-rental");
        bampfaFloor3.setPhoneNumber("510-642-1437");
        libraryID = String.valueOf(bampfa.getSpaceName()) + String.valueOf(bampfaFloor3.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        bampfaFloor3.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        bampfaC += status;

        bampfaFloors.add(bampfaFloor1);
        bampfaFloors.add(bampfaFloor2);
        bampfaFloors.add(bampfaFloor3);

        bampfaC /= 3;

        bampfa.setCrowdedness(bampfaC);

        // ====================================
        // Bancroft
        ArrayList<FloorItem> bancroftFloors = new ArrayList<>(3);
        FloorItem bancroftFloor1;
        bancroft.setFloors(bancroftFloors);
        bancroft.setHours("Hours: Wed 10am-5pm");

        bancroftFloor1 = new FloorItem(bancroft);
        bancroftFloor1.setName("Floor 1");
        bancroftFloor1.setHasGoodCellular(true);
        bancroftFloor1.setQuiet(true);
        bancroftFloor1.setAllowsFood(false);
        bancroftFloor1.setHasOutlets(true);
        bancroftFloor1.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/doe_2.pdf");
        bancroftFloor1.setLinkBooking("");
        bancroftFloor1.setPhoneNumber("510-642-6481");
        libraryID = String.valueOf(bancroft.getSpaceName()) + String.valueOf(bancroftFloor1.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        if(Double.isNaN(status)){
            status = 0;
        }
        bancroftFloor1.setCrowdedness(status);
        bancroft.setCrowdedness(status);

        bancroftFloors.add(bancroftFloor1);

        // ====================================
        // Bioscience
        ArrayList<FloorItem> bioscienceFloors = new ArrayList<>(3);
        FloorItem bioscienceFloor1, bioscienceFloor2, bioscienceFloor3;
        bioscience.setFloors(bioscienceFloors);
        bioscience.setHours("Hours: Wed 9am-10pm");
        double bioscienceC = 0;

        bioscienceFloor1 = new FloorItem(bioscience);
        bioscienceFloor1.setName("Entry Floor");
        bioscienceFloor1.setHasGoodCellular(true);
        bioscienceFloor1.setQuiet(false);
        bioscienceFloor1.setAllowsFood(false);
        bioscienceFloor1.setHasOutlets(true);
        bioscienceFloor1.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/biosplan.pdf");
        bioscienceFloor1.setLinkBooking("https://bampfa.org/about/facility-rental");
        bioscienceFloor1.setPhoneNumber("510-642-2531");
        libraryID = String.valueOf(bioscience.getSpaceName()) + String.valueOf(bioscienceFloor1.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        bioscienceFloor1.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        bioscienceC += status;

        bioscienceFloor2 = new FloorItem(bioscience);
        bioscienceFloor2.setName("Lower Mezzanine");
        bioscienceFloor2.setHasGoodCellular(true);
        bioscienceFloor2.setQuiet(true);
        bioscienceFloor2.setAllowsFood(false);
        bioscienceFloor2.setHasOutlets(true);
        bioscienceFloor2.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/biosplan.pdf");
        bioscienceFloor2.setLinkBooking("https://bampfa.org/about/facility-rental");
        bioscienceFloor2.setPhoneNumber("510-642-2531");
        libraryID = String.valueOf(bioscience.getSpaceName()) + String.valueOf(bioscienceFloor2.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        bioscienceFloor2.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        bioscienceC += status;

        bioscienceFloor3 = new FloorItem(bioscience);
        bioscienceFloor3.setName("Upper Mezzanine");
        bioscienceFloor3.setHasGoodCellular(true);
        bioscienceFloor3.setQuiet(true);
        bioscienceFloor3.setAllowsFood(false);
        bioscienceFloor3.setHasOutlets(true);
        bioscienceFloor3.setLinkFloorPlan("http://www.lib.berkeley.edu/sites/default/files/biosplan.pdf");
        bioscienceFloor3.setLinkBooking("https://bampfa.org/about/facility-rental");
        bioscienceFloor3.setPhoneNumber("510-642-2531");
        libraryID = String.valueOf(bioscience.getSpaceName()) + String.valueOf(bioscienceFloor3.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        bioscienceFloor3.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        bioscienceC += status;

        bioscienceFloors.add(bioscienceFloor1);
        bioscienceFloors.add(bioscienceFloor2);
        bioscienceFloors.add(bioscienceFloor3);

        bioscienceC /= 3;

        bioscience.setCrowdedness(bioscienceC);

        // ====================================
        // Business
        ArrayList<FloorItem> businessFloors = new ArrayList<>(1);
        FloorItem businessFloor1, businessFloor2;
        business.setFloors(businessFloors);
        business.setHours("Hours: Wed 9am-10pm");
        double businessC = 0;

        businessFloor1 = new FloorItem(business);
        businessFloor1.setName("Level 2");
        businessFloor1.setHasGoodCellular(true);
        businessFloor1.setQuiet(true);
        businessFloor1.setAllowsFood(false);
        businessFloor1.setHasOutlets(true);
        businessFloor1.setLinkFloorPlan("http://www.haas.berkeley.edu/facilities/campus/Level2.html");
        businessFloor1.setLinkBooking("https://ems.haas.berkeley.edu/");
        businessFloor1.setPhoneNumber("510-642-0370");
        libraryID = String.valueOf(business.getSpaceName()) + String.valueOf(businessFloor1.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        businessFloor1.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        businessC += status;

        businessFloor2 = new FloorItem(business);
        businessFloor2.setName("Level 3");
        businessFloor2.setHasGoodCellular(true);
        businessFloor2.setQuiet(true);
        businessFloor2.setAllowsFood(false);
        businessFloor2.setHasOutlets(true);
        businessFloor2.setCrowdedness(90);
        businessFloor2.setLinkFloorPlan("http://www.haas.berkeley.edu/facilities/campus/Level3.html");
        businessFloor2.setLinkBooking("https://ems.haas.berkeley.edu/");
        businessFloor2.setPhoneNumber("510-642-0370");
        libraryID = String.valueOf(business.getSpaceName()) + String.valueOf(businessFloor2.getName());
        status = dbHandler.getCount(1, libraryID) * 50  + dbHandler.getCount(2, libraryID) * 100;
        status = status/dbHandler.getTotalCount(libraryID);
        businessFloor2.setCrowdedness(status);
        if(Double.isNaN(status)){
            status = 0;
        }
        businessC += status;

        businessFloors.add(businessFloor1);
        businessFloors.add(businessFloor2);

        businessC /= 2;

        business.setCrowdedness(businessC);

    }

    private void fillSpaceCards() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        Collections.sort(spaces, new Comparator<SpaceCardItem>() {
            @Override
            public int compare(SpaceCardItem o1, SpaceCardItem o2) {
                return o1.getSpaceName().compareTo(o2.getSpaceName());
            }
        });
        adapter = new SpaceCardAdapter(spaces);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    public void ShowPopup(View v) {
        ImageButton button_close;
        final ToggleButton cellular, quiet, food, outlets;
        Button filter, reset;
        myDialog.setContentView(R.layout.content_custompopup);
        button_close = (ImageButton) myDialog.findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slide_down);
                myDialog.findViewById(R.id.popup_frame_layout).startAnimation(slide_down);
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

        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);
        myDialog.findViewById(R.id.popup_frame_layout).startAnimation(slide_up);
        myDialog.show();
    }

    public void filterByText (String text) {
        String lowerCaseText = text.toLowerCase();
        ArrayList<SpaceCardItem> filteredSpaces = new ArrayList<>();
        for (SpaceCardItem space : spaces) {
            String name = space.getSpaceName().toLowerCase();
            if (name.contains(lowerCaseText)) {
                filteredSpaces.add (space);
            }
        }
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new SpaceCardAdapter(filteredSpaces);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setFilteredspaces () {
        ArrayList<SpaceCardItem> filteredspaces = new ArrayList<>();
        for (SpaceCardItem space : spaces) {
            SpaceCardItem filteredSpace = new SpaceCardItem(space.getSpaceName(), space.getSpaceType());
            ArrayList<FloorItem> filteredFloors = new ArrayList<>();
            filteredSpace.setFloors(filteredFloors);
            filteredSpace.setCrowdedness(space.getCrowdedness());
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
