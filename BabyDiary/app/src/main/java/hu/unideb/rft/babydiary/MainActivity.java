package hu.unideb.rft.babydiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by VozarPet on 2018. 09. 18..
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        return super.onOptionsItemSelected(item);
    }
}
