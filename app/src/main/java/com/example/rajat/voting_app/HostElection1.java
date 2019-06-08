package com.example.rajat.voting_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.rajat.voting_app.Host_election_confirm.el_id;

public class HostElection1 extends AppCompatActivity {
    private LinearLayout parentLinearLayout;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference election;
    private EditText name;
    ArrayList<EditText> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_election1);
        parentLinearLayout = (LinearLayout) findViewById(R.id.linear_layout_2);
        election=FirebaseDatabase.getInstance().getReference().child("candidates");
        firebaseAuth=FirebaseAuth.getInstance();
        name=findViewById(R.id.number_edit_text);
    }

    public void onAddField(View v) {
        String nam=name.getText().toString().trim();
        if (TextUtils.isEmpty(nam)) {
            Toast.makeText(this, "Please enter name in field before adding more candidates!!!", Toast.LENGTH_LONG).show();
            return;
        }
        list.add(name);
        user_dynamic_field user_dy_fi=new user_dynamic_field(getApplicationContext());
        parentLinearLayout.addView(user_dy_fi.getView());
        name=user_dy_fi.getEditText();
    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }

    public void Submit_Candidates(View view)
    {
        onAddField(view);
        candidates new_candidate;

        for(int i=0;i<list.size();i++)
        {
            new_candidate=new candidates(list.get(i).getText().toString().trim(),0,el_id);
            election.child(new_candidate.getElec_id()).child(new_candidate.getName()).setValue(new_candidate);
        }
        Toast.makeText(getApplicationContext(),"Election Successfully hosted!!",Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(HostElection1.this,voter_login.class));
    }
}

