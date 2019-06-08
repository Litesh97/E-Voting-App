package com.example.rajat.voting_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static com.example.rajat.voting_app.R.id.email;
import static com.example.rajat.voting_app.R.id.email_sign_in_button;
import static com.example.rajat.voting_app.R.id.password;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView emailid;
    private TextView pass;
    private Button signin;
    private Button newuser;
    public static String str;
    public static String prim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailid = (TextView) findViewById(R.id.email);
        pass = (TextView) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.email_sign_in_button);
        newuser = (Button) findViewById(R.id.button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == signin) {
                    Voter_login();
                }
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == newuser) {
                    New_SignUp();
                }
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed()
    {
        return;
    }

    public void New_SignUp() {
        startActivity(new Intent(MainActivity.this, Admin_signUp.class));
    }

    public void Voter_login() {
        int i;
        int flag = 1;
         str = emailid.getText().toString();
        String word = pass.getText().toString();
        prim=str.split("@",2)[0];
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(word)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //TODO validate login details and call respective client function
        firebaseAuth.signInWithEmailAndPassword(str,word).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Logged In!!",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),voter_login.class));

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid Credentials!",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        });
    }
}
