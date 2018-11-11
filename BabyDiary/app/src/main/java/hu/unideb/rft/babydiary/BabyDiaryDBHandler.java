package hu.unideb.rft.babydiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bro on 2018. 11. 11..
 * DB kezelése, tábla műveletek
 */

public class BabyDiaryDBHandler extends SQLiteOpenHelper {

    // DB verzió
    private static final int DATABEASE_VERSION = 1;

    // DB neve
    private static final String DATABASE_NAME = "babydiarydb";

    // Felhasználók tábla neve
    private static final String TABLE_USER = "user";

    //Felhasználó tábla oszlop nevek
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERROLE = "userrole";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";


    public BabyDiaryDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABEASE_VERSION);
    }

    // Táblák éterehozása
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE" + TABLE_USER + "("
                + KEY_ID +" INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_USERROLE + " TEXT,"
                + KEY_FIRSTNAME + " TEXT,"
                + KEY_LASTNAME + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
