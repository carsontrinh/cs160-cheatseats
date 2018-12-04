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
                + KEY_RATING + " INT " + ")";

        db.execSQL(CREATE_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAIL);

        onCreate(db);
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

    public int getCount(int rating, String libraryID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  COUNT(*) FROM " + TABLE_DETAIL + " WHERE " + KEY_RATING +
                "=? AND " + KEY_LIBRARY + "=?", new String[]{String.valueOf(rating), libraryID});
        int count = 0;
        if (null != cursor) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        db.close();
        return count;
    }

    public long getTotalCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_DETAIL);
        db.close();
        return count;
    }

}
