package com.cc.mad.cheatseats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
                + KEY_RATING + " INT,"
                + "PRIMARY KEY (KEY_ID, KEY_LIBRARY)" + ")";

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
        values.put(KEY_ID, response.getID() + response.getLibraryName());
        values.put(KEY_RATING, response.getRating());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_DETAIL, null, values);
        db.close();
    }

    public Response findHandler(int rating) {
        String query = "Select * FROM " + TABLE_DETAIL + "WHERE" + KEY_RATING + " = " + "'" + rating + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Response response = new Response();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            response.setID(Integer.parseInt(cursor.getString(0)));
            response.setStudentName(cursor.getString(1));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        return student;
    }

    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select*FROM" + TABLE_DETAIL + "WHERE" + KEY_ID + "= '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            student.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_DETAIL, KEY_ID + "=?",
                    new String[] {
                String.valueOf(student.getID())
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateHandler(int ID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(KEY_ID, ID);
        args.put(KEY_NAME, name);
        return db.update(TABLE_DETAIL, args, KEY_ID + "=" + ID, null) > 0;
    }
}
