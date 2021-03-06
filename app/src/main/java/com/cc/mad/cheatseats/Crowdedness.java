package com.cc.mad.cheatseats;

/**
 * An enum to define the levels of crowdedness of study spaces.
 * LOW - almost empty, finding a seat should be no problem.
 * MEDIUM - busy, finding a seat should be doable
 * HIGH - very crowded, finding a seat could be impossible
 */
public class Crowdedness {
    protected static double LOWER_THRESHOLD = 20;
    protected static double UPPER_THRESHOLD = 80;
}
