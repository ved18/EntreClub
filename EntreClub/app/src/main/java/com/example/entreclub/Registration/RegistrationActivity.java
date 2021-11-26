package com.example.entreclub.Registration;
//package com.example.deentreclub;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entreclub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private static final String key_firstname = "firstname";
    private static final String key_lastname = "lastname";
    private static final String key_emailid = "emailid";
    private static final String key_contact = "contact";
    private static final String key_city = "city";
    private static final String key_password = "password";
    private static final String key_dob = "dob";
    private static final String key_gender = "gender";
    private static final String key_companyname = "companyname";
    private static final String key_position = "position";
    private static final String key_description = "description";
    RadioGroup radioGroup;
    RadioButton radioButton;
    String selectedGender;
    private TextView t1;
    private DatePickerDialog datePicker;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private FirebaseFirestore db;
    private FirebaseAuth mauth;
    int year, mont, date;
    private Spinner spinner;

    private EditText editTextfirstname, editTextlastname, editTextemailid;
    private EditText editTextcontact, editTextcity, editTextpassword, editTextcpassword;
    private EditText editTextdob, editTextcompanyname, editTextposition, editTextdescription;
    private String firstname, lastname, emailid, contact, city, dob, password, cpassword, companyname, position, description,desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);


        FirebaseApp.initializeApp(this);
        mauth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        editTextfirstname = findViewById(R.id.editText12);
        editTextlastname = findViewById(R.id.editText13);
        editTextemailid = findViewById(R.id.editText5);
        editTextcontact = findViewById(R.id.editText6);
        editTextcity = findViewById(R.id.editText11);
        editTextpassword = findViewById(R.id.editText8);
        editTextcpassword = findViewById(R.id.editText9);
       editTextdob = findViewById(R.id.editText10);
        editTextcompanyname = findViewById(R.id.editText);
        editTextposition = findViewById(R.id.editText2);
        editTextdescription = findViewById(R.id.editText3);
        radioGroup = findViewById(R.id.rg);
        t1 = (TextView) findViewById(R.id.editText10);
        spinner=(Spinner)findViewById(R.id.s_categories);


        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        mont = cal.get(Calendar.MONTH);
        date = cal.get(Calendar.DAY_OF_MONTH);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int mont = cal.get(Calendar.MONTH);
                int date = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog d = new DatePickerDialog(
                        RegistrationActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, mont, date
                );
                d.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                d.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = dayOfMonth + "/" + month + "/" + year;
                t1.setText(date);
            }
        };


    }


    public boolean validate() {
        if (firstname.isEmpty()) {
            editTextfirstname.setError("Firstname required!");
            editTextfirstname.requestFocus();
            return false;
        }

        if (lastname.isEmpty()) {
            editTextlastname.setError("Lastname required!");
            editTextlastname.requestFocus();
            return false;
        }

        if (emailid.isEmpty()) {
            editTextemailid.setError("Email id is required!");
            editTextemailid.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            editTextemailid.setError("Please enter a valid mail id!");
            editTextemailid.requestFocus();
            return false;
        }

        if (contact.isEmpty()) {
            editTextcontact.setError("Contact required!");
            editTextcontact.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            editTextpassword.setError("Password required!");
            editTextpassword.requestFocus();
            return false;
        }

        if (password.length() < 7) {
            editTextpassword.setError("Password must be atleast 7 characters long!");
            editTextpassword.requestFocus();
            return false;
        }

        if (!password.equals(cpassword)) {
            editTextcpassword.setError("Passwords do not match!");
            editTextcpassword.requestFocus();
            return false;
        }
        if (dob.isEmpty()) {
            t1.setError("Birthdate is required!");
            t1.requestFocus();
            return false;
        }

        if (city.isEmpty()) {
            editTextcity.setError("City required!");
            editTextcity.requestFocus();
            return false;
        }

        if (companyname.isEmpty()) {
            editTextcompanyname.setError("Company required!");
            editTextcompanyname.requestFocus();
            return false;
        }

        if (position.isEmpty()) {
            editTextposition.setError("Position in the company required!");
            editTextposition.requestFocus();
            return false;
        }

        if (description.isEmpty()) {
            editTextdescription.setError("Please provide profile description!");
            editTextdescription.requestFocus();
            return false;
        }

        return true;

    }


    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        selectedGender = (String) radioButton.getText();
        // Toast.makeText(this, "Button : " + radioButton.getText(),Toast.LENGTH_SHORT).show();
    }



    public void registerEntrepreneur(View v) {


        String pw_hash;
        firstname = editTextfirstname.getText().toString().trim();
        lastname = editTextlastname.getText().toString().trim();
        emailid = editTextemailid.getText().toString().trim();
        contact = editTextcontact.getText().toString().trim();
        city = editTextcity.getText().toString().trim();
        password = editTextpassword.getText().toString().trim();
        cpassword = editTextcpassword.getText().toString().trim();
        dob = editTextdob.getText().toString().trim();
        companyname = editTextcompanyname.getText().toString().trim();
        position = editTextposition.getText().toString().trim();
        description = editTextdescription.getText().toString().trim();
        //  pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
        desc= spinner.getSelectedItem().toString();

        Log.d("spinner text mila",desc);

        if (validate()) {


            Map<String, Object> entrepreneur = new HashMap<>();
            entrepreneur.put(key_firstname, firstname);
            entrepreneur.put(key_lastname, lastname);
            entrepreneur.put(key_emailid, emailid);
            entrepreneur.put(key_contact, contact);
            entrepreneur.put(key_city, city);
            entrepreneur.put(key_password, password);
            entrepreneur.put(key_dob, dob);
            entrepreneur.put(key_gender, selectedGender);
            entrepreneur.put(key_companyname, companyname);
            entrepreneur.put(key_position, position);
            entrepreneur.put(key_description, description);
            entrepreneur.put("business_category",desc);
            entrepreneur.put("flag","0");

            mauth.createUserWithEmailAndPassword(emailid, password);

            db.collection("Users").document(emailid).set(entrepreneur)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            //  Toast.makeText(Registration.this,"Registration Successful!",Toast.LENGTH_SHORT).show();

                            sendEmailVerification();


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), "Registration Unsuccessful!", Toast.LENGTH_SHORT).show();

                        }
                    });


        }
    }



    public void sendEmailVerification() {

        FirebaseUser firebaseUser = mauth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Successfully registered, Verification mail sent", Toast.LENGTH_SHORT).show();
                        mauth.signOut();
                    } else {
                        Toast.makeText(getApplicationContext(), "Couldn't send verification mail, Try again!", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }
}

//        private void sendEmailVerification(){


//
//}

//
//import android.app.DatePickerDialog;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//
//import com.example.entreclub.R;
//
//import org.mindrot.jbcrypt.BCrypt;
//
//import java.util.Calendar;
//
//public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{
//
//    private TextView t1;
//    private DatePickerDialog datePicker;
//    private DatePickerDialog.OnDateSetListener mDateSetListener;
//    //private Button b;
//    RadioGroup radioGroup;
//    RadioButton radioButton;
//    String selectedGender;
//    String firstname,lastname,emailid,password,contact,dob,city,cpassword,pw_hash;
//
//
//    private EditText editTextfirstname,editTextlastname,editTextemailid,editTextcontact,editTextpassword,editTextcity,editTextcpassword;
//    int year,mont,date;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.acitivity_personal_info);
//
//
//        editTextfirstname = findViewById(R.id.editText12);
//        editTextlastname = findViewById(R.id.editText13);
//        editTextemailid = findViewById(R.id.editText5);
//        editTextcontact = findViewById(R.id.editText6);
//        editTextpassword = findViewById(R.id.editText8);
//      //  editTextdob = findViewById(R.id.editText10);
//        editTextcity = findViewById(R.id.editText11);
//        editTextcpassword = findViewById(R.id.editText9);
//
//        findViewById(R.id.btnNext).setOnClickListener(this);
//        findViewById(R.id.editText10).setOnClickListener(this);
//       t1 = (TextView) findViewById(R.id.editText10);
//      t1.setOnClickListener(this);
//        radioGroup = findViewById(R.id.rg);
//
//     //  t1= ( TextView) findViewById(R.id.editText10);
//      //  b=(Button)findViewById(R.id.btnNext);
//
//        Calendar cal = Calendar.getInstance();
//         year=cal.get(Calendar.YEAR);
//         mont=cal.get(Calendar.MONTH);
//         date=cal.get(Calendar.DAY_OF_MONTH);
//
//    }
//
//        /*b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(RegistrationActivity.this, businessInfo.class);
//                startActivity(i);
//            }
//        });*/
//
//    /*    t1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar cal = Calendar.getInstance();
//                int year=cal.get(Calendar.YEAR);
//                int mont=cal.get(Calendar.MONTH);
//                int date=cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog d=new DatePickerDialog(
//                        RegistrationActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,mont,date
//                );
//                d.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
//                d.show();
//            }
//        });*/
//
//   /*     mDateSetListener =new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month=month+1;
//
//                String date=dayOfMonth+"/"+month+"/"+year;
//                t1.setText(date);
//            }
//        };
//
//*/
//
//        public void checkButton(View v){
//            int radioId = radioGroup.getCheckedRadioButtonId();
//            radioButton = findViewById(radioId);
//            selectedGender = (String) radioButton.getText();
//           // Toast.makeText(this, "Button : " + radioButton.getText(),Toast.LENGTH_SHORT).show();
//        }
//
//        private boolean userSignup(){
//
//            firstname = editTextfirstname.getText().toString().trim();
//            lastname = editTextlastname.getText().toString().trim();
//            emailid = editTextemailid.getText().toString().trim();
//            contact = editTextcontact.getText().toString().trim();
//            password = editTextpassword.getText().toString().trim();
//            dob = t1.getText().toString().trim();
//            city = editTextcity.getText().toString().trim();
//            cpassword = editTextcpassword.getText().toString().trim();
//
//
//             pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
//
//
//
////            if(firstname.isEmpty()){
////                editTextfirstname.setError("Firstname required!");
////                editTextfirstname.requestFocus();
////                return false;
////            }
////
////            if(lastname.isEmpty()){
////                editTextlastname.setError("Lastname required!");
////                editTextlastname.requestFocus();
////                return false;
////             }
////
////            if(emailid.isEmpty()){
////                editTextemailid.setError("Email id is required!");
////                editTextemailid.requestFocus();
////                return false;
////            }
////
////             if(!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()){
////                 editTextemailid.setError("Please enter a valid mail id!");
////                 editTextemailid.requestFocus();
////                 return false;
////             }
////
////            if(contact.isEmpty()){
////                editTextcontact.setError("Contact required!");
////                editTextcontact.requestFocus();
////                return false;
////            }
////
////            if(password.isEmpty()){
////                editTextpassword.setError("Password required!");
////                editTextpassword.requestFocus();
////                return false;
////            }
////
////            if(password.length() < 7){
////                editTextpassword.setError("Password must be atleast 7 characters long!");
////                editTextpassword.requestFocus();
////                return false;
////            }
////
////            if(!password.equals(cpassword)){
////                editTextcpassword.setError("Passwords do not match!");
////                editTextcpassword.requestFocus();
////                return false;
////            }
////            if(dob.isEmpty()){
////                t1.setError("Birthdate is required!");
////                t1.requestFocus();
////                return false;
////            }
////
////            if(city.isEmpty()){
////                editTextcity.setError("City required!");
////                editTextcity.requestFocus();
////                return false;
////            }
////
// return true;
//
//    }
//
//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()) {
//
//
//
//            case R.id.btnNext:
//               // if(userSignup()) {
//                    Intent i = new Intent(RegistrationActivity.this, businessInfo.class);
//
////                    Bundle extras = new Bundle();
////                    extras.putString("fname", firstname);
////                    extras.putString("lname", lastname);
////                    extras.putString("mail", emailid);
////                    extras.putString("phone", contact);
////                    extras.putString("pass", pw_hash);
////                    extras.putString("birthdate", dob);
////                    extras.putString("gender", selectedGender);
////                    extras.putString("residential", city);
////                    i.putExtras(extras);
//
//                    startActivity(i);
//              //  }
//                break;
//
//
//
//
//            case R.id.editText10:
//
//              /*  Calendar cal = Calendar.getInstance();
//                int year=cal.get(Calendar.YEAR);
//                int mont=cal.get(Calendar.MONTH);
//                int date=cal.get(Calendar.DAY_OF_MONTH);
//*/
//                DatePickerDialog d=new DatePickerDialog(
//                        RegistrationActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,mont,date
//                );
//                d.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
//                d.show();
//
//
//                mDateSetListener =new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        month = month + 1;
//
//                        String date = dayOfMonth + "-" + month + "-" + year;
//                        t1.setText(date);
//                    }
//                };
//                break;
//
//
//
//
//package com.example.deentreclub;
//
//import android.app.DatePickerDialog;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Patterns;
//import android.view.View;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import org.mindrot.jbcrypt.BCrypt;
//
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Registration extends AppCompatActivity {
//
//    private static final String key_firstname = "firstname";
//    private static final String key_lastname = "lastname";
//    private static final String key_emailid = "emailid";
//    private static final String key_contact = "contact";
//    private static final String key_city = "city";
//    private static final String key_password = "password";
//    private static final String key_dob = "dob";
//    private static final String key_gender = "gender";
//    private static final String key_companyname = "companyname";
//    private static final String key_position = "position";
//    private static final String key_description = "description";
//    RadioGroup radioGroup;
//    RadioButton radioButton;
//    String selectedGender;
//    private TextView t1;
//    private DatePickerDialog datePicker;
//    private DatePickerDialog.OnDateSetListener mDateSetListener;
//    private FirebaseFirestore db;
//    private FirebaseAuth mauth;
//    int year, mont, date;
//
//
//    private EditText editTextfirstname, editTextlastname, editTextemailid;
//    private EditText editTextcontact, editTextcity, editTextpassword, editTextcpassword;
//    private EditText editTextdob, editTextcompanyname, editTextposition, editTextdescription;
//    private String firstname, lastname, emailid, contact, city, dob, password, cpassword, companyname, position, description;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.registration_activity);
//
//
//        FirebaseApp.initializeApp(this);
//        mauth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
//
//        editTextfirstname = findViewById(R.id.editText12);
//        editTextlastname = findViewById(R.id.editText13);
//        editTextemailid = findViewById(R.id.editText5);
//        editTextcontact = findViewById(R.id.editText6);
//        editTextcity = findViewById(R.id.editText11);
//        editTextpassword = findViewById(R.id.editText8);
//        editTextcpassword = findViewById(R.id.editText9);
//        editTextdob = findViewById(R.id.editText10);
//        editTextcompanyname = findViewById(R.id.editText);
//        editTextposition = findViewById(R.id.editText2);
//        editTextdescription = findViewById(R.id.editText3);
//        radioGroup = findViewById(R.id.rg);
//        t1 = (TextView) findViewById(R.id.editText10);
//
//
//        Calendar cal = Calendar.getInstance();
//        year = cal.get(Calendar.YEAR);
//        mont = cal.get(Calendar.MONTH);
//        date = cal.get(Calendar.DAY_OF_MONTH);
//
//
//        t1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int mont = cal.get(Calendar.MONTH);
//                int date = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog d = new DatePickerDialog(
//                        Registration.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, mont, date
//                );
//                d.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
//                d.show();
//            }
//        });
//
//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month = month + 1;
//
//                String date = dayOfMonth + "/" + month + "/" + year;
//                t1.setText(date);
//            }
//        };
//
//
//    }
//
//
//    public boolean validate() {
//        if (firstname.isEmpty()) {
//            editTextfirstname.setError("Firstname required!");
//            editTextfirstname.requestFocus();
//            return false;
//        }
//
//        if (lastname.isEmpty()) {
//            editTextlastname.setError("Lastname required!");
//            editTextlastname.requestFocus();
//            return false;
//        }
//
//        if (emailid.isEmpty()) {
//            editTextemailid.setError("Email id is required!");
//            editTextemailid.requestFocus();
//            return false;
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
//            editTextemailid.setError("Please enter a valid mail id!");
//            editTextemailid.requestFocus();
//            return false;
//        }
//
//        if (contact.isEmpty()) {
//            editTextcontact.setError("Contact required!");
//            editTextcontact.requestFocus();
//            return false;
//        }
//
//        if (password.isEmpty()) {
//            editTextpassword.setError("Password required!");
//            editTextpassword.requestFocus();
//            return false;
//        }
//
//        if (password.length() < 7) {
//            editTextpassword.setError("Password must be atleast 7 characters long!");
//            editTextpassword.requestFocus();
//            return false;
//        }
//
//        if (!password.equals(cpassword)) {
//            editTextcpassword.setError("Passwords do not match!");
//            editTextcpassword.requestFocus();
//            return false;
//        }
//        if (dob.isEmpty()) {
//            t1.setError("Birthdate is required!");
//            t1.requestFocus();
//            return false;
//        }
//
//        if (city.isEmpty()) {
//            editTextcity.setError("City required!");
//            editTextcity.requestFocus();
//            return false;
//        }
//
//        if (companyname.isEmpty()) {
//            editTextcompanyname.setError("Company required!");
//            editTextcompanyname.requestFocus();
//            return false;
//        }
//
//        if (position.isEmpty()) {
//            editTextposition.setError("Position in the company required!");
//            editTextposition.requestFocus();
//            return false;
//        }
//
//        if (description.isEmpty()) {
//            editTextdescription.setError("Please provide profile description!");
//            editTextdescription.requestFocus();
//            return false;
//        }
//
//        return true;
//
//    }
//
//
//    public void checkButton(View v) {
//        int radioId = radioGroup.getCheckedRadioButtonId();
//        radioButton = findViewById(radioId);
//        selectedGender = (String) radioButton.getText();
//        // Toast.makeText(this, "Button : " + radioButton.getText(),Toast.LENGTH_SHORT).show();
//    }
//
//
//    public void registerEntrepreneur(View v) {
//
//
//        String pw_hash;
//        firstname = editTextfirstname.getText().toString().trim();
//        lastname = editTextlastname.getText().toString().trim();
//        emailid = editTextemailid.getText().toString().trim();
//        contact = editTextcontact.getText().toString().trim();
//        city = editTextcity.getText().toString().trim();
//        password = editTextpassword.getText().toString().trim();
//        cpassword = editTextcpassword.getText().toString().trim();
//        dob = editTextdob.getText().toString().trim();
//        companyname = editTextcompanyname.getText().toString().trim();
//        position = editTextposition.getText().toString().trim();
//        description = editTextdescription.getText().toString().trim();
//        //  pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
//
//
//        if (validate()) {
//
//
//            Map<String, Object> entrepreneur = new HashMap<>();
//            entrepreneur.put(key_firstname, firstname);
//            entrepreneur.put(key_lastname, lastname);
//            entrepreneur.put(key_emailid, emailid);
//            entrepreneur.put(key_contact, contact);
//            entrepreneur.put(key_city, city);
//            entrepreneur.put(key_password, password);
//            entrepreneur.put(key_dob, dob);
//            entrepreneur.put(key_gender, selectedGender);
//            entrepreneur.put(key_companyname, companyname);
//            entrepreneur.put(key_position, position);
//            entrepreneur.put(key_description, description);
//
//            db.collection("Users").document(emailid).set(entrepreneur)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//
//                            //  Toast.makeText(Registration.this,"Registration Successful!",Toast.LENGTH_SHORT).show();
//                            mauth.createUserWithEmailAndPassword(emailid, password);
////                        sendEmailVerification();
//                            FirebaseUser firebaseUser = mauth.getInstance().getCurrentUser();
//                            if (firebaseUser != null) {
//                                firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//
//                                        if (task.isSuccessful()) {
//                                            Toast.makeText(getApplicationContext(), "Successfully registered, Verification mail sent", Toast.LENGTH_SHORT).show();
//                                            mauth.signOut();
//                                        } else {
//                                            Toast.makeText(getApplicationContext(), "Couldn't send verification mail, Try again!", Toast.LENGTH_SHORT).show();
//                                        }
//
//                                    }
//                                });
//                            }
//                        }
//
//
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//
//                            Toast.makeText(Registration.this, "Registration Unsuccessful!", Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//
//
//        }
//    }
//}
////        private void sendEmailVerification(){
////
////}
//        }
//
//    }
//}