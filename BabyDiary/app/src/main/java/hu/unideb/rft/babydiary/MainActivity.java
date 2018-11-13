package hu.unideb.rft.babydiary;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by VozarPet on 2018. 09. 18..
 */

public class MainActivity extends AppCompatActivity {

    BabyDiaryDBHandler mydb;
    boolean loggedIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new BabyDiaryDBHandler(this);
        if (!loggedIn){
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
        }


//        if(mydb.existsUser("Teszt")){
//            Toast.makeText(this, "Már létezik a felhasználó", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(this, "nem találtam a felhasználót " , Toast.LENGTH_SHORT).show();
//        }
//        mydb.addUser(new User(1,"Teszt", "Jelszo", "Emailcim", "Szülő", "Vezetéknév", "Keresztnév"));
//        User kiolvasottUser = mydb.getUser(1);
//        Toast.makeText(this, "" + kiolvasottUser.getUsername(), Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "" + mydb.getUsersCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if (id == R.id.itemLine2){
 //           Toast.makeText(this, "Ismerős meghívása", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(this, RegActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.itemLine1){
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.itemLine3){
            Intent intent = new Intent();
            intent.setClass(this, TodoActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


}
