package com.example.entreclub.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entreclub.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;


public class ViewMom extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    FirebaseFirestore db;
    DocumentReference dr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mom);
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
                finish();
            }
        });
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String str=extras.get("event_id").toString();
        FirebaseApp.initializeApp(this);
        db=FirebaseFirestore.getInstance();

        dr=db.collection("Events").document(str);
        t1=(TextView)findViewById(R.id.title);
        t2=(TextView)findViewById(R.id.t_date);
        t3=(TextView)findViewById(R.id.t_decision);
        t4=(TextView)findViewById(R.id.t_ammendment);
        t5=(TextView)findViewById(R.id.t_scope);
        t6=(TextView)findViewById(R.id.t_link);
        t7=(TextView)findViewById(R.id.t_remarks);
       // t8=(TextView)findViewById(R.id.t_agenda);


        getEventDetails();

    }


    public void getEventDetails()
    {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String str=extras.get("event_id").toString();
        dr.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
               // String s1=documentSnapshot.getString("Agenda");
              //  t8.setText(s1);
                String s2=documentSnapshot.getString("Title");
                t1.setText(s2);
                //  Timestamp t=documentSnapshot.getTimestamp("Date");

                Timestamp s3=documentSnapshot.getTimestamp("Date");
//                int a=s3.toDate().getDate();
//                int b=s3.toDate().getMonth()+1;
//                int c=s3.toDate().getYear();
//                t2.setText(a+"/"+b+"/"+c);
                Date d =  s3.toDate();
                String st = d.toString();
                t2.setText(st);
                String s4=documentSnapshot.getString("Decisions");
                Log.d("lolo",st);
                t3.setText(s4);
                String s5=documentSnapshot.getString("Amendments");
                t4.setText(s5);
                String s6=documentSnapshot.getString("Futurescope");
                t5.setText(s6);
                String s7=documentSnapshot.getString("Link");
                t6.setText(s7);
                String s8=documentSnapshot.getString("Remarks");
                t7.setText(s8);




               // Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();
            }
        });
    }
}
