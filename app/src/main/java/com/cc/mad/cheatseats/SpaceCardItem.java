package com.cc.mad.cheatseats;

import java.util.ArrayList;
import java.io.Serializable;

public class SpaceCardItem implements Serializable {

    private String spaceName;
    private String spaceType;
    private ArrayList<FloorItem> floors;

    SpaceCardItem(String spaceName, String spaceType){
        this.spaceName = spaceName;
        this.spaceType = spaceType;
    }


    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }

    public ArrayList<FloorItem> getFloors() {
        return floors;
    }

    public void setFloors(ArrayList<FloorItem> floors) {
        this.floors = floors;
    }
}
