package com.example.rajat.voting_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Collections.sort;
public class result_display extends AppCompatActivity
{
    public LinearLayout parentLinearLayout;
    DatabaseReference election_display,winner_display;
    public ArrayList<candidates> candidates=new ArrayList<>();
    public ArrayList<String> elec=new ArrayList<>();
    public ArrayList<String> winner=new ArrayList<>();
    public TextView name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_display);
        parentLinearLayout=findViewById(R.id.linear_layout_3);
        election_display= FirebaseDatabase.getInstance().getReference().child("elections");
        winner_display=FirebaseDatabase.getInstance().getReference().child("candidates");
        name =findViewById(R.id.winner);
        display_elections();
    }
    public void display_elections()
 {

        election_display.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot elec_parse:dataSnapshot.getChildren())
                    {

                        final Election new_elec=elec_parse.getValue(Election.class);
                        if(new_elec.getFlag().equals("0"))
                        {
                            String elec_name=new_elec.getId();
                            winner_display.child(elec_name).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                {
                                    if(dataSnapshot.exists())
                                    {
                                        //Input of candidates working
                                        for(DataSnapshot election_winner:dataSnapshot.getChildren())
                                        {
                                            candidates new_can=election_winner.getValue(candidates.class);
                                            candidates.add(new_can);
                                        }
                                        //
                                        //Sorting part done and working
                                        ArrayList<Integer> arr=new ArrayList<>();
                                        for(int i=0;i<candidates.size();i++)
                                        {
                                            int value=candidates.get(i).count;
                                            arr.add(value);
                                        }
                                        sort(arr,Collections.<Integer>reverseOrder());
                                        //

                                        int i;
                                        for(i=0;i<candidates.size();i++)
                                        {

                                            if(candidates.get(i).count==arr.get(0))
                                            {
                                                elec.add(candidates.get(i).name);
                                            }
                                        }
                                        for(int j=0;j<elec.size();j++) {
                                            name.setVisibility(View.VISIBLE);
                                            String statement;
                                            statement = new_elec.getId() + "        " + ": " + elec.get(j);
                                            name.setText(statement);
                                            result_dynamiv_view win_res = new result_dynamiv_view(getApplicationContext());
                                            parentLinearLayout.addView(win_res.getView());
                                            name = win_res.getTv();
                                            name.setVisibility(View.INVISIBLE);
                                        }
                                        elec.clear();
                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                    }
                }
                else
                {
                    name.setText("No Results Displayed!!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

