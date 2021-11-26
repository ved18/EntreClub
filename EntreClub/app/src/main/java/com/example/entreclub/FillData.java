package com.example.entreclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FillData extends AppCompatActivity {

//    Intent i;
//    EditText e1,e2;
//    Button btn;
//    String type;
//    RadioGroup radioGroup;
//    RadioButton rb;
//    FirebaseFirestore db;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fill_data);
//        db= FirebaseFirestore.getInstance();
//        i=getIntent();
//        final String eventId=i.getStringExtra("ID");
//        e1=(EditText) findViewById(R.id.email);
//        btn=(Button)findViewById(R.id.btn_submit);
//        e2=(EditText)findViewById(R.id.description);
//        radioGroup=findViewById(R.id.rbtn);
//        Toast.makeText(FillData.this,eventId,Toast.LENGTH_LONG).show();
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int radioid=radioGroup.getCheckedRadioButtonId();
//                rb=findViewById(radioid);
//                type= (String) rb.getText();
//                Toast.makeText(FillData.this,type,Toast.LENGTH_LONG).show();
//
//                String desc=e2.getText().toString();
//                String mail=e1.getText().toString();
//                        AddNeeds a=new AddNeeds(type,desc,mail,eventId);
//
//
//                CollectionReference dbProducts = db.collection("UpdateEventInfo");
//
//                dbProducts.add(a).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(FillData.this,"Data addded succesfully",Toast.LENGTH_LONG).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                });
//
//            }
//        });
//
//    }

//
}
