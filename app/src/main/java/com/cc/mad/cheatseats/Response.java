package com.cc.mad.cheatseats;

public class Response {
    // fields
    // rating 0 = low | 1 = medium | 2 = high
    private int rating;
    private String libraryName;
    private String userID;

    // constructors
    public Response() {}

    public Response(int rating, String libraryName, String userID) {
        this.rating = rating;
        this.libraryName = libraryName;
        this.userID = userID;
    }

    // properties
    public void setID(String userID) { this.userID = userID; }
    public String getID() {
        return this.userID;
    }
    public void setLibraryName(String library) { this.libraryName = library; }
    public String getLibraryName() {
        return this.libraryName;
    }
    public int getRating() { return this.rating; }
    public void setRating(int newrating) { this.rating = newrating; }
}
