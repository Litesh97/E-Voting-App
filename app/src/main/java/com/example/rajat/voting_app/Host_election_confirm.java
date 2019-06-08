package com.example.rajat.voting_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.rajat.voting_app.MainActivity.prim;
import static com.example.rajat.voting_app.MainActivity.str;

public class Host_election_confirm extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference election_id;
    EditText elid;
    public static String el_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_election_confirm);
        election_id=FirebaseDatabase.getInstance().getReference().child("elections");
        firebaseAuth=FirebaseAuth.getInstance();
        elid=findViewById(R.id.elec_id);


    }

    public void Done(View view)
    {

        el_id=elid.getText().toString().trim();
        final Election new_election=new Election(el_id,"no",prim);
        election_id.child(el_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!dataSnapshot.exists()) {
                    election_id.child(el_id).setValue(new_election);
                    finish();
                    startActivity(new Intent(Host_election_confirm.this,HostElection1.class));
                    }
                    else
                {
                    Toast.makeText(getApplicationContext(),"This ID already exists!!Enter new unique id!!",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(Host_election_confirm.this,Host_election_confirm.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                }

        });
    }

}
