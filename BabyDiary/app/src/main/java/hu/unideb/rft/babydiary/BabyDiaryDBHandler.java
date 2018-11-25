package hu.unideb.rft.babydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vozarpet on 2018. 11. 11..
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
//        letrehozTabla();
    }

    // Táblák éterehozása
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    // létrehozza a táblát, ha még nem létezik
    public void letrehozTabla(){
        SQLiteDatabase db = getWritableDatabase();
        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "("
                + KEY_ID +" INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_USERROLE + " TEXT,"
                + KEY_FIRSTNAME + " TEXT,"
                + KEY_LASTNAME + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
        db.close();
    }

    // Eldob régi táblát, ha létezik és létrehoz ujra
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    //Töröl tábla
    public void deleteTable(String tableName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.close();
    }

    // új User hozzáadása (regisztráció)
    public void addUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_USERROLE, user.getUserrole());
        values.put(KEY_FIRSTNAME, user.getFirstname());
        values.put(KEY_LASTNAME, user.getLastname());

        db.insert(TABLE_USER, null, values);
        db.close();

    }

    // validálja a felhasználó emailcíméhez tartozó jelszót
    public boolean validPasswordToEmail(String email, String password){
        User selectedUser = getUserByemail(email);
        if (password.equals(selectedUser.getPassword())){
            return true;
        }
        return false;
    }

    // 1 User lekérése db-ből ID alapján
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

    // 1 User lekérése db-ből felhasználónév alapján
    public User getUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,
                new String[]{KEY_ID, KEY_USERNAME, KEY_PASSWORD, KEY_EMAIL, KEY_USERROLE, KEY_FIRSTNAME, KEY_LASTNAME},
                KEY_USERNAME + "=?",
                new String[]{username},
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

    // 1 User lekérdezése db-ből emailcim apaján
    public User getUserByemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,
                new String[]{KEY_ID, KEY_USERNAME, KEY_PASSWORD, KEY_EMAIL, KEY_USERROLE, KEY_FIRSTNAME, KEY_LASTNAME},
                KEY_EMAIL + "=?",
                new String[]{email},
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

    // user szerepel-e táblába
    public boolean existsUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USERNAME +  " = '" + username + "'", null);
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    // user szerepel-e táblába emailcím alapján
    public boolean existsUserByEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAIL +  " = '" + email + "'", null);
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
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

    // Egy user törlése
    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID +" = ?", new String[]{Integer.toString(id)});
        db.close();
    }

    // Összes user törlése
    public void deleteAllUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.close();
    }

    // Userek számának lekérdezése
    public int getUsersCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
        int count = cursor.getCount();

        cursor.close();
        db.close();

        return count;
    }
}
