package com.example.rajat.voting_app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.rajat.voting_app.Vote_enter_election_id.elec_id;
import static com.example.rajat.voting_app.Vote_enter_election_id.hostname;

public class Actual_election_page extends AppCompatActivity {

    private LinearLayout parentLinearLayout;
    FirebaseAuth firebaseAuth;
  //  FirebaseDatabase firebaseDatabase;
    DatabaseReference candidate_table;
    ArrayList<candidates> rep=new ArrayList<>();
    RadioButton can_name;
    ArrayList<RadioButton> sel_can=new ArrayList<>();
    public static String candidate_name_sel;
    Button but;
    int flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_election_page);
        parentLinearLayout = (LinearLayout) findViewById(R.id.CandidateLayout);
        candidate_table=FirebaseDatabase.getInstance().getReference().child("candidates");
        firebaseAuth=FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();
        final RadioGroup ll;

        ll=(RadioGroup) findViewById(R.id.candidate_name);
        but=(Button)findViewById(R.id.confirm_vote);
        candidate_table.child(elec_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RadioGroup.LayoutParams rprms;
                if (dataSnapshot.exists()) {


                    for (DataSnapshot can_parse : dataSnapshot.getChildren()) {

                        candidates can = can_parse.getValue(candidates.class);
                        rep.add(can);

                    }
                    RadioButton can_name=new RadioButton(Actual_election_page.this);
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    for (int i = 0; i < rep.size(); i++) {
                        candidate_dynamic_field new_can = new candidate_dynamic_field(getApplicationContext());
                        parentLinearLayout.addView(new_can.getView());
                        can_name = new_can.getTv();
                        can_name.setText(rep.get(i).getName());
                        can_name.setId(i);
                        sel_can.add(can_name);
                        rprms= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                        if(can_name.getParent()!=null)
                        {
                            ((ViewGroup)can_name.getParent()).removeView(can_name);
                        }
                        ll.addView(can_name,rprms);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void Confirm_Vote(View view)
    {
        for(int i=0;i<sel_can.size();i++)
        {
            if(sel_can.get(i).isChecked())
            {
                candidate_name_sel=sel_can.get(i).getText().toString().trim();
                flag=0;
                break;
            }
        }
        if(flag==1)
        {
            Toast.makeText(getApplicationContext(),"Select The candidate",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(Actual_election_page.this,Actual_election_page.class));
        }
        finish();
        startActivity(new Intent(Actual_election_page.this,Vote_confirm.class));

            }
}
