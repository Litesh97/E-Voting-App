package com.example.rajat.voting_app;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.rajat.voting_app.MainActivity.prim;

public class Manage_hosted_elections extends AppCompatActivity {
    DatabaseReference election_display;
    private ToggleButton btn;
    ArrayList<Election> election_name = new ArrayList<>();
    ArrayList<ToggleButton> manage = new ArrayList<>();
    public LinearLayout parentLinearLayout;
    TextView name;
    ArrayList<TextView> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_hosted_elections);
        name = (TextView) findViewById(R.id.textView);
        btn = (ToggleButton) findViewById(R.id.toggleButton);
        election_display = FirebaseDatabase.getInstance().getReference().child("elections");
        parentLinearLayout = findViewById(R.id.linear_layout_1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        election_display.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot elec_name : dataSnapshot.getChildren()) {

                        Election elec = elec_name.getValue(Election.class);
                        if (elec.getHost_name().equals(prim) && (elec.getFlag().equals("no") || elec.getFlag().equals("1"))) {

                            election_name.add(elec);
                        }
                    }
                    if (election_name.size() == 0) {
                        Toast.makeText(Manage_hosted_elections.this, "You Have no Hosted Elections!!!", Toast.LENGTH_LONG).show();
                        name.setVisibility(View.INVISIBLE);
                        btn.setVisibility(View.INVISIBLE);
                        return;
                    }
                    name.setText(election_name.get(0).id);
                    manage.add(btn);
                    arrayList.add(name);
                    for (int i = 1; i < election_name.size(); i++) {
                        display_election_name_dynamic dis_elec_name = new display_election_name_dynamic(getApplicationContext());
                        parentLinearLayout.addView(dis_elec_name.getView());
                        name = dis_elec_name.getTv();
                        name.setText(election_name.get(i).id);
                        btn = dis_elec_name.getTb();
                        manage.add(btn);
                        arrayList.add(name);
                    }

                }
                for (int i = 0; i < manage.size(); i++) {
                    manage.get(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (int i = 0; i < manage.size(); i++) {
                                if (manage.get(i).isChecked() && election_name.get(i).getFlag().equals("no")) {
                                    String elec = election_name.get(i).id;
                                    Election election = new Election(elec, "1", prim);
                                    election_display.child(elec).setValue(election);
                                    Toast.makeText(Manage_hosted_elections.this,elec+" has been hosted!!!",Toast.LENGTH_LONG).show();
                                } else {
                                    if (manage.get(i).isChecked() && (election_name.get(i).getFlag().equals("0") || election_name.get(i).getFlag().equals("1"))||!(manage.get(i).isChecked())) {
                                        ToggleButton tg = findViewById(R.id.toggleButton);
                                        if (election_name.get(i).getFlag().equals("1"))
                                            tg.setText("OFF");
                                        else if(election_name.get(i).getFlag().equals("0"))
                                            tg.setText("ON`");
                                        manage.get(i).setVisibility(view.INVISIBLE);
                                        arrayList.get(i).setVisibility(view.INVISIBLE);
                                        String elec = election_name.get(i).id;
                                        Election election = new Election(elec, "0", prim);
                                        election_display.child(elec).setValue(election);
                                        Toast.makeText(Manage_hosted_elections.this,elec+" has been stopped!!!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

