package hu.unideb.rft.babydiary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by VozarPet on 2018. 09. 22...
 * Reg Activity
 */


public class RegActivity extends Activity{

    private EditText et_felhasznalonev, et_jelszo, et_email, et_vezeteknev, et_keresztnev;
    private String felhasznalonev, jelszo, email, vezeteknev, keresztnev;
    Button regButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        et_felhasznalonev = findViewById(R.id.registration_username_edittext);
        et_jelszo = findViewById(R.id.registration_password_edittext);
        et_email = findViewById(R.id.registration_email_edittext);
        et_vezeteknev = findViewById(R.id.registration_fitst_name_edittext);
        et_keresztnev = findViewById(R.id.registration_last_name_edittext);
        regButton = findViewById(R.id.registration_button);
    }

    public void onClick(View v){
        if (v.getId() == R.id.registration_button){
            inicializal();
            validal();
            regisztral();
        }
    }

    private void inicializal(){
        felhasznalonev = et_felhasznalonev.getText().toString();
        jelszo = et_jelszo.getText().toString();
        email = et_email.getText().toString();
        vezeteknev = et_vezeteknev.getText().toString();
        keresztnev = et_keresztnev.getText().toString();
    }

}
