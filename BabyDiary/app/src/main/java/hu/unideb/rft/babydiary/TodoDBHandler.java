package hu.unideb.rft.babydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Vozarpet on 2018. 11. 16..
 */

public class TodoDBHandler extends SQLiteOpenHelper{


    // DB verzió
    private static final int DATABASE_Version = 1;

    // DB neve
    private static final String DATABASE_NAME = "babydiarydb";

    // Tudúk  tábal neve
    private static final String TABLE_TODO = "todo";

    //Tudú tábla oszlopnevek
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_PRIORITY = "priority";


    public TodoDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }



    //Létrehoz táblát ha még nem létezik
    public void letrehozTodoTabla(){
        SQLiteDatabase db = getWritableDatabase();
        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_TODO + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT"
                + KEY_TITLE + " TEXT"
                + KEY_DESCRIPTION + " TEXT"
                + KEY_DATE + " TEXT"
                + KEY_TIME + " TEXT"
                + KEY_LOCATION + " TEXT"
                + KEY_PRIORITY + " INTEGER" + ")";
        db.execSQL(CREATE_USER_TABLE);
        db.close();
    }

    // Eldob tudú tábla ha már létezik
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_TODO);
        onCreate(db);
    }

    // Töröl tábla
    public void deletTable(String tableName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.close();
    }

    // uj tudú felvétele belső DB-be
    public void addTodo (Todo todo){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, todo.getFelhasznalonev());
        values.put(KEY_TITLE, todo.getCim());
        values.put(KEY_DESCRIPTION, todo.getLeiras());
        values.put(KEY_DATE, todo.getDateum());
        values.put(KEY_TIME, todo.getIdo());
        values.put(KEY_LOCATION, todo.getHelyszin());
        values.put(KEY_PRIORITY, todo.getPrioritas());

        db.insert(TABLE_TODO, null, values);
        db.close();
    }

    // Öszes a felhasználóhoz tartozó Tudú lekérése a db-böl felhasználónév alapján
    public ArrayList getTodo(String username){
        ArrayList<Todo> todos = new ArrayList<Todo>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_TODO, new String[]{KEY_ID, KEY_USERNAME, KEY_TITLE, KEY_DESCRIPTION, KEY_DATE, KEY_TIME, KEY_LOCATION, KEY_PRIORITY},
                KEY_USERNAME + "=?",
                new String[]{username},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Todo ujTodo = new Todo(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        Integer.parseInt(cursor.getString(7)));
                todos.add(ujTodo);


                cursor.moveToNext();
            }
            cursor.close();
            db.close();
            return todos;
        }
        else{
            return todos;
        }
    }

    // töröl Tudú DB-ből 
}








