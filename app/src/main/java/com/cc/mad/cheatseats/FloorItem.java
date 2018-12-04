package com.cc.mad.cheatseats;

import android.widget.ImageView;

import java.io.Serializable;

public class FloorItem implements Serializable {

    private SpaceCardItem spaceCardItem;
    private String name;
    private boolean hasGoodCellular;
    private boolean isQuiet;  // consider changing to isNoisy?
    private boolean allowsFood;
    private boolean hasOutlets;
    private double crowdedness;

    private ImageView statusImage;

    private String linkFloorPlan;
    private String linkBooking;
    private String phoneNumber;

    public FloorItem(SpaceCardItem spaceCardItem) {
        this.spaceCardItem = spaceCardItem;
        //crowdedness = 0;
    }

    public SpaceCardItem getSpaceCardItem() {
        return spaceCardItem;
    }

    public void setSpaceCardItem(SpaceCardItem spaceCardItem) {
        this.spaceCardItem = spaceCardItem;
    }

    public String getName() {
        return name;
    }

    public ImageView getStatusImage(){return statusImage;}

    public void setStatusImage(ImageView sI){this.statusImage=sI;}

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasGoodCellular() {
        return hasGoodCellular;
    }

    public void setHasGoodCellular(boolean hasGoodCellular) {
        this.hasGoodCellular = hasGoodCellular;
    }

    public boolean isQuiet() {
        return isQuiet;
    }

    public void setQuiet(boolean quiet) {
        isQuiet = quiet;
    }

    public boolean allowsFood() {
        return allowsFood;
    }

    public void setAllowsFood(boolean allowsFood) {
        this.allowsFood = allowsFood;
    }

    public boolean hasOutlets() {
        return hasOutlets;
    }

    public void setHasOutlets(boolean hasOutlets) {
        this.hasOutlets = hasOutlets;
    }

    public double getCrowdedness() {
        return crowdedness;
    }

    public void setCrowdedness(double crowdedness) {
        this.crowdedness = crowdedness;
    }

    public String getLinkFloorPlan() {
        return linkFloorPlan;
    }

    public void setLinkFloorPlan(String linkFloorPlan) {
        this.linkFloorPlan = linkFloorPlan;
    }

    public String getLinkBooking() {
        return linkBooking;
    }

    public void setLinkBooking(String linkBooking) {
        this.linkBooking = linkBooking;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
