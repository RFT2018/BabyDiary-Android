package hu.unideb.rft.babydiary;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        myCalendar = Calendar.getInstance();
        et_datum = findViewById(R.id.todo_et_datum);
        et_ido = findViewById(R.id.todo_et_ido);

    }


    public void onClick(View v)
    {
        if (v.getId() == et_datum.getId()){ // datumra klikkelésnél kilövünk egy datepicker dialógust.
            new DatePickerDialog(this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
        else if(v.getId() == et_ido.getId()){
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


