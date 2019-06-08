package com.example.rajat.voting_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.rajat.voting_app.MainActivity.prim;

public class Vote_enter_election_id extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference election_table;
    DatabaseReference voter_check;
    EditText h_name;
    EditText e_id;
    DataSnapshot dataSnapshot;
    public static String elec_id;
    public static String hostname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_enter_election_id);
        election_table=FirebaseDatabase.getInstance().getReference("elections");
        firebaseAuth=FirebaseAuth.getInstance();
        e_id=findViewById(R.id.election_id);
        h_name=findViewById(R.id.election_host);
        voter_check=FirebaseDatabase.getInstance().getReference().child("vote_check");
    }

    public void Goto_Election_Page(View view)
    {
         elec_id=e_id.getText().toString().trim();
         hostname=h_name.getText().toString().trim();
        if (TextUtils.isEmpty(elec_id)) {
            Toast.makeText(this, "Please enter election id!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(hostname)) {
            Toast.makeText(this, "Please enter hostname!", Toast.LENGTH_LONG).show();
            return;
        }
         final Election elec_check=new Election();

            election_table.child(elec_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    Election elec_check = dataSnapshot.getValue(Election.class);
                    if (elec_check.host_name.equals(hostname) && elec_check.getFlag().equals("1")) {
                        voter_check.child(prim).child(elec_id).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists())
                                {
                                    Toast.makeText(Vote_enter_election_id.this,"You have already voted!!!Cannot Vote again!!",Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(new Intent(Vote_enter_election_id.this,voter_login.class));
                                }
                                else {
                                    Toast.makeText(Vote_enter_election_id.this, "Hosted Election Exists!Transferring Control!", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(Vote_enter_election_id.this, Actual_election_page.class));
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                    else if (elec_check.host_name.equals(hostname) && elec_check.getFlag().equals("no")) {
                        Toast.makeText(getApplicationContext(), "Corresponding Election is not live!!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(Vote_enter_election_id.this, voter_login.class));
                    }

                        else if(elec_check.host_name.equals(hostname) && elec_check.getFlag().equals("0"))
                        {
                            Toast.makeText(getApplicationContext(), "Corresponding Election has been stopped!!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(Vote_enter_election_id.this, voter_login.class));
                        }

                    else if(!elec_check.host_name.equals(hostname))
                    {
                        Toast.makeText(getApplicationContext(), "This host has not conducted this election!!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(Vote_enter_election_id.this, voter_login.class));

                    }

                } else {
                    Toast.makeText(Vote_enter_election_id.this, "Hosted Election does not exist,enter valid election!!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(Vote_enter_election_id.this, Vote_enter_election_id.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                }
                });
    }
}

