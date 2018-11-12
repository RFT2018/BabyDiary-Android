package hu.unideb.rft.babydiary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by VozarPet on 2018. 09. 22...
 * Reg Activity
 */


public class RegActivity extends Activity{

    private EditText et_felhasznalonev, et_jelszo, et_email, et_vezeteknev, et_keresztnev;
    private String felhasznalonev, jelszo, email, vezeteknev, keresztnev;
    Button regButton;
    private String hibauzenet = "hiba történt";
    BabyDiaryDBHandler dbHandler = new BabyDiaryDBHandler(this);
    User newUser = new User();

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
            if (!validal()){
                Toast.makeText(this, hibauzenet, Toast.LENGTH_SHORT).show();
            }
            else{
                if(szabadUsername()){
                    newUser.setId(dbHandler.getUsersCount()+1);
                    dbHandler.addUser(newUser);
                    Toast.makeText(this, "Sikeres regisztráció.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, hibauzenet, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // beolvassa az adatokat a UI-rol
    private void inicializal(){
        felhasznalonev = et_felhasznalonev.getText().toString();
        jelszo = et_jelszo.getText().toString();
        email = et_email.getText().toString();
        vezeteknev = et_vezeteknev.getText().toString();
        keresztnev = et_keresztnev.getText().toString();
    }

    // Validálja a beolvasott adatokat
    private boolean validal(){
        if (felhasznalonev.isEmpty() || felhasznalonev.length() >32){
            hibauzenet = "hibás felhasználónév";
            return false;
        }
        else if(jelszo.isEmpty() || jelszo.length() < 4 || jelszo.length() > 32){
            hibauzenet = "Hibás jelszó. Min 4 max 30 karakter.";
            return false;
        }
        else if (email.isEmpty() || email.length() > 32){
            hibauzenet = "Hibás vagy túl hosszú emailcím.";
            return false;
        }
        else if (vezeteknev.isEmpty() || vezeteknev.length() > 32){
            hibauzenet = "Hibás vezetéknév.";
            return false;
        }
        else if (keresztnev.isEmpty() || keresztnev.length() > 32){
            hibauzenet = "Hibás keresztnév.";
            return false;
        }
        return true;
    }

    // Regisztrál
    private boolean szabadUsername(){
      if(dbHandler.existsUser(felhasznalonev)){
          hibauzenet = "A felhasználónév már foglalt!";
          et_felhasznalonev.setText("");
          return false;
      }
     return true;
    }

    //Létrheozza a User objektumot adatokból
    public void letrehozUser(){
        newUser.setId(dbHandler.getUsersCount()+1);
        newUser.setUsername(felhasznalonev);
        newUser.setPassword(jelszo);
        newUser.setEmail(email);
        newUser.setUserrole("Parent");
        newUser.setFirstname(vezeteknev);
        newUser.setLastname(keresztnev);
    }
}
