package hu.unideb.rft.babydiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by VozarPet on 2018. 10. 07..
 */

public class LoginActivity extends Activity{

    private String emailcim;
    private String jelszo;
    private String hibauzenet = "Hiba történt.";
    private EditText et_email, et_jelszo;
    private Button btn_login;
    private TextView tv_nemRegelt, tv_elfelejtettJelszo;
    private CheckBox chb_stayLoggedIn;
    private BabyDiaryDBHandler db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email = findViewById(R.id.login_username_et);
        et_jelszo = findViewById(R.id.login_password_et);
        tv_elfelejtettJelszo = findViewById(R.id.login_elfelejtettJelszo_tv);
        tv_nemRegelt = findViewById(R.id.login_notRegisteredYet_tv);
        chb_stayLoggedIn = findViewById(R.id.login_stayLoggedIn_chb);
        btn_login = findViewById(R.id.login_Login_btn);
        db = new BabyDiaryDBHandler(this);

    }

    public void onClick(View v){
        if (v.getId() == btn_login.getId()){
            emailcim = et_email.getText().toString();
            jelszo = et_jelszo.getText().toString();
            if (validalEmailAndPass()){
                // TODO adatbázisba beírni, hogy belépett
                Toast.makeText(this, "Sikeres bejelentkezés.", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                Toast.makeText(this, ""+hibauzenet, Toast.LENGTH_SHORT).show();
            }
        }
        else if (v.getId() == tv_elfelejtettJelszo.getId()){
            //TODO uj jelszót generálni, átírni DB-be és elküldeni a megadott emailcímre
            Toast.makeText(this, "Az új jelszót elküldtük a megadott emailcímre.", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == tv_nemRegelt.getId()){
            Intent intent = new Intent();
            intent.setClass(this, RegActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean validalEmailAndPass(){
        if (emailcim.length() < 5 ){
            hibauzenet = "Túlrövid emailcím.";
            return false;
        }
        else if(!db.existsUserByEmail(emailcim)){
            hibauzenet = "Hibás emailcím.";
            return false;
        }
        else if (!db.validPasswordToEmail(emailcim, jelszo)){
            hibauzenet = "Hibás jelszó.";
            et_jelszo.setText("");
            return false;
        }
        else{
            return true;
        }
    }
}
