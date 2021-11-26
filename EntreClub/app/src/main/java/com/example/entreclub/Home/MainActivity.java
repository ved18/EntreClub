package com.example.entreclub.Home;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.entreclub.R;
import com.example.entreclub.Registration.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.mindrot.jbcrypt.BCrypt;



public class MainActivity  extends AppCompatActivity{


    TextView tNewUser;
    LinearLayout l;
    private EditText editTextMail;
    private EditText editTextPass;
    private String emailid,password;
    //    ProgressBar progressBar;
    FirebaseAuth mAuth;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        db= FirebaseFirestore.getInstance();

        tNewUser = (TextView) findViewById(R.id.newUser);
        l = (LinearLayout) findViewById(R.id.LayoutInput);

        editTextMail = findViewById(R.id.mail);
        editTextPass = findViewById(R.id.pass);
//        progressBar = findViewById(R.id.progressbar);

        l.setBackgroundColor(Color.rgb(255, 255, 255));
        String text = "New User? Sign Up";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(i);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                String col = "#446fcd";
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor(col));
            }
        };

        ss.setSpan(clickableSpan, 10, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tNewUser.setText(ss);
        tNewUser.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public void forgotPassword(View v){
        Intent i = new Intent(getApplicationContext(), ResetPassword.class);
        startActivity(i);
    }

    public void userSignin(View v) {
        emailid = editTextMail.getText().toString().trim();
        password = editTextPass.getText().toString().trim();
//            if(emailid.equals("vedang.kapse@outlook.com")){
//                Intent intent = new Intent (getApplicationContext(), HomeActivity1.class);
//                Bundle extras = new Bundle();
//                extras.putString("emailid", emailid);
//                intent.putExtras(extras);
//                startActivity(intent);
//
//            }
////
//        emailid = editTextMail.getText().toString().trim();
//        String password = editTextPass.getText().toString().trim();



        //String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());

        if (emailid.isEmpty()) {
            editTextMail.setError("Email id is required!");
            editTextMail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            editTextMail.setError("Please enter a valid mail id!");
            editTextMail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPass.setError("Password required!");
            editTextPass.requestFocus();
            return;
        }

        if (password.length() < 7) {
            editTextPass.setError("Password must be atleast 7 characters long!");
            editTextPass.requestFocus();
            return;

        }


        db.collection("Users").document(emailid)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if( documentSnapshot.getString("flag").equals("1")){

                                mAuth.signInWithEmailAndPassword(emailid, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if(task.isSuccessful()){

                                            //  Toast.makeText(getApplicationContext(),"Sucessful!",Toast.LENGTH_SHORT).show();
                                            emailVerification();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),"Unsuccessful!",Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                        }

                        else{
                            Toast.makeText(getApplicationContext(),"Request is pennding with or rejected by Authority!",Toast.LENGTH_SHORT);
                        }
                    }
                });


//        progressBar.setVisibility(View.VISIBLE);

















    }

    private void emailVerification(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();


    if(emailflag) {

        if (firebaseUser.getEmail().equals("narwade1695@gmail.com") && password.equals("1234567")) {


            Intent intent = new Intent(getApplicationContext(), HomeActivity1.class);
            startActivity(intent);
        } else {
            //  Toast.makeText(getApplicationContext(),"Sucessful!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            Bundle extras = new Bundle();
            extras.putString("emailid", emailid);
            intent.putExtras(extras);
            startActivity(intent);
        }

    }

        else{
            Toast.makeText(getApplicationContext(),"Please verify your email!",Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }

    }



}
