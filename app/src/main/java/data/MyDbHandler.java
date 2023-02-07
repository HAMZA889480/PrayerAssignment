package data;


import static params.Params.DATE;
import static params.Params.USERNAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Model.Activity;
import Model.users;
import params.Params;


public class MyDbHandler extends SQLiteOpenHelper{

    public MyDbHandler(Context context) {
        super(context, Params.DATABASE_NAME, null, Params.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE = "CREATE TABLE " + Params.USERS_TABLE + "("
                + Params.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USERNAME + " TEXT UNIQUE,"
                + Params.PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_ACTIVITY_TABLE = "CREATE TABLE " + Params.ACTIVITY_TABLE + "("
                + Params.ACTIVITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Params.USER_ID + " INTEGER,"
                + Params.DATE + " DATE,"
                + Params.FAJAR + " INTEGER,"
                + Params.ZOHAR + " INTEGER,"
                + Params.MAGHRIB + " INTEGER,"
                + Params.AISHA + " INTEGER,"
                + "FOREIGN KEY (" + Params.USER_ID + ") REFERENCES " + Params.USERS_TABLE + "(" + Params.USER_ID + ")" + ")";
        db.execSQL(CREATE_ACTIVITY_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Params.USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Params.ACTIVITY_TABLE);
        onCreate(db);

    }

    public void addUser(users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.USERNAME, user.getUsr_name());
        values.put(Params.PASSWORD, user.getPassword());
        db.insert(Params.USERS_TABLE, null, values);
        db.close();
    }





    public boolean verifyLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Params.USERS_TABLE, new String[] { Params.USER_ID, USERNAME, Params.PASSWORD },
                USERNAME + "=?", new String[] { username }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            if (password.equals(cursor.getString(2))) {
                cursor.close();
                return true;
            }
        }
        return false;
    }

    public int getUserId(String username, String password) {
        int id = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Params.USERS_TABLE, new String[] {Params.USER_ID}, "username=? and password=?",
                new String[] { username, password }, null, null, null, null);
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;
    }





    public Activity getActivity(int userId, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Params.ACTIVITY_TABLE, new String[] { Params.ACTIVITY_ID, Params.USER_ID, DATE, Params.FAJAR, Params.ZOHAR, Params.MAGHRIB, Params.AISHA },
                Params.USER_ID + "=? and " + DATE + "=?", new String[] { String.valueOf(userId), date }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            Activity activity = new Activity();
            activity.setId(cursor.getInt(0));
            activity.setUser_id(cursor.getInt(1));
            activity.setDate(cursor.getString(2));
            activity.setFajar(cursor.getInt(3));
            activity.setZohar(cursor.getInt(4));
            activity.setMaghrib(cursor.getInt(5));
            activity.setAisha(cursor.getInt(6));
            cursor.close();
            return activity;
        }
        return null;
    }

}

