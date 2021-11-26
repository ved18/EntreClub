package com.example.entreclub.Home;





        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.entreclub.R;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.Timestamp;
        import com.google.firebase.firestore.CollectionReference;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.DocumentSnapshot;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.Query;
        import com.google.firebase.firestore.QueryDocumentSnapshot;
        import com.google.firebase.firestore.QuerySnapshot;
        import com.google.firebase.firestore.SetOptions;

        import java.util.List;
        import java.util.*;

public class AddLog extends AppCompatActivity {


    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private boolean flag=false;
    public boolean check_user_existence(String email)
    {
        Toast.makeText(AddLog.this,"IN check function" ,Toast.LENGTH_LONG).show();
        flag=false;
        DocumentReference rf = db.collection("Users").document(email);
        rf.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    flag=true;

                    //Toast.makeText(MainActivity.this,"firstname"+documentSnapshot.get("firstname").toString(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    //Toast.makeText(MainActivity.this,"In onsuccess else",Toast.LENGTH_LONG).show();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        //  Toast.makeText(MainActivity.this,"firstname failed",Toast.LENGTH_LONG).show();

                    }
                });
        return flag;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String event_id=extras.get("event_id").toString();
        //event_id = "1";

        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
                finish();
            }
        });

        DocumentReference rf = db.collection("Events").document(event_id);
        rf.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    TextView temp = (TextView) findViewById(R.id.event_name);
                    temp.setText(documentSnapshot.get("Title").toString());

                }
            }
        });
    }
    public void saveLogs(View v)
    {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String event_id = extras.get("event_id").toString();

        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
                finish();
            }
        });


        EditText edit_to_user= (EditText) findViewById(R.id.to_user);
        EditText edit_from_user= (EditText) findViewById(R.id.from_user);
        EditText edit_amount= (EditText) findViewById(R.id.amount);
        EditText edit_service_consumed= (EditText) findViewById(R.id.service_consumed);
        String from_user = edit_from_user.getText().toString();
        String to_user = edit_to_user.getText().toString();
        String service_consumed = edit_service_consumed.getText().toString();
        String amount = edit_amount.getText().toString();
        Toast.makeText(AddLog.this,from_user+ to_user ,Toast.LENGTH_LONG).show();
       // Integer amount = Integer.parseInt(edit_amount.getText().toString());
        Map<String,Object> l = new HashMap<>();
        l.put("from_user",from_user);
        l.put("to_user",to_user);
        l.put("amount",amount);
        l.put("service_consumed",service_consumed);
        //check_user_existence(from_user);
        /*if(!check_user_existence(from_user))
        {
            Toast.makeText(MainActivity.this,"From User is not a valid User",Toast.LENGTH_LONG).show();
        }
        else if(!check_user_existence(to_user))
        {
            Toast.makeText(MainActivity.this,"To User is not a valid User",Toast.LENGTH_LONG).show();
        }
        else
        {*/


        db.collection("Events").document(event_id).collection("Logs")
                //db.collection("Temp")
                .add(l)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddLog.this,"Log added successfully",Toast.LENGTH_LONG).show();
                        //Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddLog.this,"Error while adding Log",Toast.LENGTH_LONG).show();
                    }
                });
        TextView temp = (TextView) findViewById(R.id.from_user);
        temp.setText("");
        temp = (TextView) findViewById(R.id.to_user);
        temp.setText("");
        temp = (TextView) findViewById(R.id.amount);
        temp.setText("");
        temp = (TextView) findViewById(R.id.service_consumed);
        temp.setText("");
        // }

    }
}