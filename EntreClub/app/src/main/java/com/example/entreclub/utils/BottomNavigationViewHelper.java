package com.example.entreclub.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.example.entreclub.Home.HomeActivity;
import com.example.entreclub.Notification.NotifActivity;
import com.example.entreclub.Profile.ProfileActivity;
import com.example.entreclub.R;
import com.example.entreclub.Search.SearchActivity;
import com.example.entreclub.Share.ShareActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: Setting Up Bottom Navigation view");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);

    }
    public static void enablenavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch(menuItem.getItemId()){
                   case R.id.ic_house:
                       Intent intent1 = new Intent(context, HomeActivity.class);
                       context.startActivity(intent1);                      //ACTIVITY_NUM=0
                   break;
                   case R.id.ic_alert:
                     Intent intent4 = new Intent(context, NotifActivity.class);
                    context.startActivity(intent4);                      //ACTIVITY_NUM=3
                      break;
                   case R.id.ic_group:
                       Intent intent5 = new Intent(context, ProfileActivity.class);
                       context.startActivity(intent5);                      //ACTIVITY_NUM=4
                       break;

               }



                return false;
            }
        });
    }
}
