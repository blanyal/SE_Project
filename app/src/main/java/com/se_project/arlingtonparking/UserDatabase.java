package com.se_project.arlingtonparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserDatabase";

    // Table name
    private static final String TABLE_USER = "UserTable";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_LASTN = "lastn";
    private static final String KEY_FIRSTN = "firstn";
    private static final String KEY_ROLE = "role";
    private static final String KEY_UTA_ID = "uta_id";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CITY = "city";
    private static final String KEY_STATE = "state";
    private static final String KEY_ZIP = "zip";
    private static final String KEY_LICENSE = "license";
    private static final String KEY_DOB = "dob";
    private static final String KEY_PERMIT = "permit";
    private static final String KEY_CAR = "car";

    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_LASTN + " TEXT,"
                + KEY_FIRSTN + " TEXT,"
                + KEY_ROLE + " INTEGER,"
                + KEY_UTA_ID + " INTEGER,"
                + KEY_PHONE + " INTEGER,"
                + KEY_EMAIL + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_STATE + " TEXT,"
                + KEY_ZIP + " INTEGER,"
                + KEY_LICENSE + " TEXT,"
                + KEY_DOB + " TEXT,"
                + KEY_PERMIT + " TEXT,"
                + KEY_CAR + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    // Adding new user
    public int addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_LASTN, user.getLastn());
        values.put(KEY_FIRSTN, user.getFirstn());
        values.put(KEY_ROLE, user.getRole());
        values.put(KEY_UTA_ID, user.getUta_id());
        values.put(KEY_PHONE, user.getPhone());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_ADDRESS, user.getAddress());
        values.put(KEY_CITY, user.getCity());
        values.put(KEY_STATE, user.getState());
        values.put(KEY_ZIP, user.getZip());
        values.put(KEY_LICENSE, user.getLicense());
        values.put(KEY_DOB, user.getDob());
        values.put(KEY_PERMIT, user.getPermit());
        values.put(KEY_CAR, user.getCar());

        // Inserting Row
        long ID = db.insert(TABLE_USER, null, values);
        db.close();
        return (int) ID;
    }

    // Getting single User
    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[]
                        {
                                KEY_ID,
                                KEY_USERNAME,
                                KEY_PASSWORD,
                                KEY_LASTN,
                                KEY_FIRSTN,
                                KEY_ROLE,
                                KEY_UTA_ID,
                                KEY_PHONE,
                                KEY_EMAIL,
                                KEY_ADDRESS,
                                KEY_CITY,
                                KEY_STATE,
                                KEY_ZIP,
                                KEY_LICENSE,
                                KEY_DOB,
                                KEY_PERMIT,
                                KEY_CAR
                        }, KEY_ID + "=?",

                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4),
                Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)),
                Integer.parseInt(cursor.getString(7)), cursor.getString(8),
                cursor.getString(9), cursor.getString(10),
                cursor.getString(11), Integer.parseInt(cursor.getString(12)),
                cursor.getString(13), cursor.getString(14), cursor.getString(15),
                cursor.getString(16));

        return user;
    }

    // Getting all Users
    public List<User> getAllUsers() {
        List<User> reminderList = new ArrayList<>();

        // Select all Query
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setLastn(cursor.getString(3));
                user.setFirstn(cursor.getString(4));
                user.setRole(Integer.parseInt(cursor.getString(5)));
                user.setUta_id(Integer.parseInt(cursor.getString(6)));
                user.setPhone(Integer.parseInt(cursor.getString(7)));
                user.setEmail(cursor.getString(1));
                user.setAddress(cursor.getString(2));
                user.setCity(cursor.getString(3));
                user.setState(cursor.getString(4));
                user.setZip(Integer.parseInt(cursor.getString(1)));
                user.setLicense(cursor.getString(2));
                user.setDob(cursor.getString(3));
                user.setPermit(cursor.getString(4));
                user.setCar(cursor.getString(4));

                // Adding users to list
                reminderList.add(user);
            } while (cursor.moveToNext());
        }
        return reminderList;
    }

    // Updating single User
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_LASTN, user.getLastn());
        values.put(KEY_FIRSTN, user.getFirstn());
        values.put(KEY_ROLE, user.getRole());
        values.put(KEY_UTA_ID, user.getUta_id());
        values.put(KEY_PHONE, user.getPhone());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_ADDRESS, user.getAddress());
        values.put(KEY_CITY, user.getCity());
        values.put(KEY_STATE, user.getState());
        values.put(KEY_ZIP, user.getZip());
        values.put(KEY_LICENSE, user.getLicense());
        values.put(KEY_DOB, user.getDob());
        values.put(KEY_PERMIT, user.getPermit());
        values.put(KEY_CAR, user.getCar());

        // Updating row
        return db.update(TABLE_USER, values, KEY_ID + "=?",
                new String[]{String.valueOf(user.getId())});
    }

    // Deleting single User
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + "=?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
}
