package com.cc.mad.cheatseats;

import java.io.Serializable;

public class FloorItem implements Serializable {

    private SpaceCardItem spaceCardItem;
    private String name;
    private boolean hasGoodCellular;
    private boolean isQuiet;  // consider changing to isNoisy?
    private boolean allowsFood;
    private boolean hasOutlets;
    private Crowdedness crowdedness;

    public FloorItem(SpaceCardItem spaceCardItem) {
        this.spaceCardItem = spaceCardItem;
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

    public Crowdedness getCrowdedness() {
        return crowdedness;
    }

    public void setCrowdedness(Crowdedness crowdedness) {
        this.crowdedness = crowdedness;
    }
}
