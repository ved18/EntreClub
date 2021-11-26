package com.example.entreclub.Notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.entreclub.R;
import com.example.entreclub.utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NotifActivity extends AppCompatActivity {
    private static final String TAG = "searchactivity";
    private Context mContext = NotifActivity.this;
    private static final int ACTIVITY_NUM = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        Log.d(TAG, "onCreate: Started!");




        final Date today = new Date();
        final Calendar nextyear = Calendar.getInstance();

        final ArrayList dates = new ArrayList();
        for (int i = 0; i < 5; i++)
        {
            nextyear.add(Calendar.DAY_OF_MONTH, 3);
            dates.add(nextyear.getTime());
        }

        nextyear.add(Calendar.YEAR, 1);
        final CalendarPickerView datepicker = findViewById(R.id.calender);
        // datepicker.init(today,nextyear.getTime()).withSelectedDate(today);
        datepicker.init(today,nextyear.getTime()).inMode(CalendarPickerView.SelectionMode.MULTIPLE).withSelectedDates(dates);

        datepicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateUnselected(Date date) {

                String selectdate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                //Toast.makeText(NotifActivity.this,"EVENT : ENTHUSIA",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NotifActivity.this, Pop.class));

                datepicker.init(today,nextyear.getTime()).inMode(CalendarPickerView.SelectionMode.MULTIPLE).withSelectedDates(dates);

            }


            @Override
            public void onDateSelected(Date date) {
                String selectdate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                startActivity(new Intent(NotifActivity.this, Pop_no_event.class));
                datepicker.init(today,nextyear.getTime()).inMode(CalendarPickerView.SelectionMode.MULTIPLE).withSelectedDates(dates);

//                onDateUnselected(date);


            }
        });


        datepicker.getSelectedDates();

        setupbottomnavigationview();
    }


    //bottom navigation setup
    private void setupbottomnavigationview(){
        Log.d(TAG, "setupbottomnavigationview: Setting up Bottom Navigation View");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomnav);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enablenavigation(mContext,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = ((Menu) menu).getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}







