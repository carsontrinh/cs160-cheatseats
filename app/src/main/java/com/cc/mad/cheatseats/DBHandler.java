package com.cc.mad.cheatseats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "responses";
    private static final String TABLE_DETAIL = "responseInfo";

    private static final String KEY_ID = "id";
    private static final String KEY_LIBRARY = "library";
    private static final String KEY_RATING = "rating";

    public DBHandler(Context contex) { super(contex, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DETAIL_TABLE = "CREATE TABLE " + TABLE_DETAIL + "("
                + KEY_ID + " TEXT,"
                + KEY_LIBRARY + " TEXT,"
                + KEY_RATING + " INT" + ")";

        db.execSQL(CREATE_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAIL);

        onCreate(db);
    }

    public String loadHandler() {
        String result = "";
        String query = "SELECT * FROM " + TABLE_DETAIL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            int result_1 = cursor.getInt(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(Response response) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, response.getID());
        values.put(KEY_LIBRARY, response.getLibraryName());
        values.put(KEY_RATING, response.getRating());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_DETAIL, null, values);
        db.close();
    }

    public boolean updateHandler(String userID, String library, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(KEY_ID, userID);
        args.put(KEY_LIBRARY, library);
        args.put(KEY_RATING, rating);
        return db.update(TABLE_DETAIL, args, KEY_ID + " = ? AND " + KEY_LIBRARY + " = ?", new String[]{userID, library}) > 0;
    }

    public Response findHandler(String userID, String library) {
        String query = "Select * FROM " + TABLE_DETAIL + "WHERE" + KEY_ID + " = " + "'" + userID + "'" + "AND" + KEY_LIBRARY + " = " + "'" + library + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Response response = new Response();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            response.setID(cursor.getString(0));
            response.setLibraryName(cursor.getString(1));
            response.setRating(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            response = null;
        }
        db.close();
        return response;
    }

    public long getCount(int rating, String library) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(
                "SELECT COUNT (*) FROM " + TABLE_DETAIL + " WHERE " + KEY_RATING + "= ? AND " + KEY_LIBRARY + "= ?",
                new String[] { String.valueOf(rating), library}
        );
        int count = 0;
        if(null != cursor) {
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
        cursor.close();
        }

        db.close();
        return count;
    }
}
