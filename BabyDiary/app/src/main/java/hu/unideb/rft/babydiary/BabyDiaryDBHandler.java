package hu.unideb.rft.babydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    // Eldob régi táblát, ha létezik és létrehoz ujra
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    // új User hozzáadása (regisztráció)
    public int addUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_USERROLE, user.getUserrole());
        values.put(KEY_FIRSTNAME, user.getFirstname());
        values.put(KEY_LASTNAME, user.getLastname());

        int updateRows = db.update(TABLE_USER, values, KEY_ID + " + ?", new String[]{String.valueOf(user.getId())});

        db.close();

        return updateRows;
    }

    // 1 User lekérése db-ből
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,
                                    new String[]{KEY_ID, KEY_USERNAME, KEY_PASSWORD, KEY_EMAIL, KEY_USERROLE, KEY_FIRSTNAME, KEY_LASTNAME},
                                    KEY_ID + "=?",
                                    new String[]{String.valueOf(id)},
                                    null,
                                    null,
                                    null,
                                    null);
        cursor.moveToFirst();
        User user = new User(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                cursor.getString(4), cursor.getString(5), cursor.getString(6));
       cursor.close();
       db.close();

       return user;
    }

    // összes User lekérdezése
    public List<User> getAllUser(){
        List<User> userLista = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);

        while (cursor.moveToNext()){
            User user = new User();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setUserrole(cursor.getString(4));
            user.setFirstname(cursor.getString(5));
            user.setLastname(cursor.getString(6));
            userLista.add(user);
        }
        cursor.close();
        db.close();

        return userLista;
    }

    





}
