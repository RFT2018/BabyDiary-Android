package hu.unideb.rft.babydiary;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Vozarpet on 2018. 10. 15..
 */

public class TodoActivity extends Activity{

    private EditText et_cim;
    private EditText et_leiras;
    private EditText et_datum;
    private EditText et_ido;
    private EditText et_helyszin;
    private RatingBar rb_prioritás;
    private Calendar myCalendar;
    private Button btn_todo_ok;
    private String hibauzenet = "Hiba történt";
    private String felhasznalo, cim, leiras, datum, ido, hely;
    private int prioritas;
    Todo newtodo = new Todo();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        myCalendar = Calendar.getInstance();
        et_cim = findViewById(R.id.todo_et_cim);
        et_leiras = findViewById(R.id.todo_et_leiras);
        et_datum = findViewById(R.id.todo_et_datum);
        et_ido = findViewById(R.id.todo_et_ido);
        et_helyszin = findViewById(R.id.todo_et_hely);
        rb_prioritás = findViewById(R.id.todo_rb_prioritas);
        btn_todo_ok = findViewById(R.id.todo_btn_ok);

    }


    public void onClick(View v)
    {
        if (v.getId() == et_datum.getId()){ // datumra klikkelésnél kilövünk egy datepicker dialógust.
            new DatePickerDialog(this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
        else if(v.getId() == et_ido.getId()){ // időt megbökve kilövünk egy time pickert
            int ora = myCalendar.get(Calendar.HOUR_OF_DAY);
            int perc = myCalendar.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int kivalasztottOra, int kivalasztottPerc) {
                    et_ido.setText(kivalasztottOra + ":" + kivalasztottPerc);
                }
            }, ora, perc, true);
            mTimePicker.setTitle("Válassz időpontot");
            mTimePicker.show();
        }
        else if (v.getId() == btn_todo_ok.getId()){
            inicializalAdatokat();
            if(!validalAdatokat()){
                Toast.makeText(this, hibauzenet,Toast.LENGTH_SHORT).show();

            }
            else{
                elmentTodo();
                Toast.makeText(this, "Feljegyzés elmentve.", Toast.LENGTH_SHORT).show();
                finish();
            }

        }


    }

    private void inicializalAdatokat() {
        cim = et_cim.getText().toString();
        leiras = et_leiras.getText().toString();
        datum = et_datum.getText().toString();
        ido = et_ido.getText().toString();
        hely = et_helyszin.getText().toString();
        prioritas = Integer.parseInt(String.valueOf(rb_prioritás.getRating()));
    }


    private boolean validalAdatokat(){
        if (cim.length()<1){    // Ha nincs címe
            hibauzenet = "cím megadása kötelező.";
            return false;
        }
        else if(datum.length()<1){ // Ha nincs dátum
            hibauzenet = "válassz dátumot";
            return false;
        }
        else if(ido.length()<1){    // Ha nincs időpont
            hibauzenet = "adj meg egy időpontot";
            return false;
        }
        else{   // Ha minden rendben
            return true;
        }

    }

    // FEltölti a Todot a db-be
    public void elmentTodo(){
        //TODO az id-t és a felhasználónevet hozzáadni a new todohoz a tárolás elött!
        newtodo.setCim(cim);
        newtodo.setLeiras(leiras);
        newtodo.setDateum(datum);
        newtodo.setIdo(ido);
        newtodo.setHelyszin(hely);
        newtodo.setPrioritas(prioritas);

        // TODO a todot benyomni db-be a todo táblába.

    }

    // A datepickerbe beállítjuk az aktuális dátumost
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

        // felűlírja a dátum mező értékét a beállított dátummal a megoadott formátum alapján.
        private void updateLabel() {
            String myFormat = "yy/MM/dd"; // dátum megjelenítési formátum
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

            et_datum.setText(sdf.format(myCalendar.getTime()));
        }

    };

}


