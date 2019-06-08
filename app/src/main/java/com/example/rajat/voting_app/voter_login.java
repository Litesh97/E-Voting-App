package com.example.rajat.voting_app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.rajat.voting_app.MainActivity.prim;

public class voter_login extends AppCompatActivity {
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_login);
        tv=findViewById(R.id.Username);
        tv.setText("Hello,"+prim);

    }

    @Override
    public void onBackPressed()
    {
        return;
    }

    public void Manage_Elections(View view)
    {
        startActivity(new Intent(voter_login.this,Manage_hosted_elections.class));
    }

    public void Host_Election(View view)
    {
        startActivity(new Intent(voter_login.this,Host_election_confirm.class) );
    }

    public void Vote_For_Election(View view)
    {
        startActivity(new Intent(voter_login.this,Vote_enter_election_id.class) );
    }

    public void LogOut(View view)
    {
        startActivity(new Intent(voter_login.this,MainActivity.class) );
        Toast.makeText(getApplicationContext(),"Successfully Logged Out",Toast.LENGTH_SHORT).show();
    }
    public void result_dis(View view)
    {
        startActivity(new Intent(voter_login.this,result_display.class) );
    }
}

