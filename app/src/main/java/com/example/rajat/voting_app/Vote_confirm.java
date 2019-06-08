package com.example.rajat.voting_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.rajat.voting_app.Actual_election_page.candidate_name_sel;
import static com.example.rajat.voting_app.MainActivity.prim;
import static com.example.rajat.voting_app.Vote_enter_election_id.elec_id;

public class Vote_confirm extends AppCompatActivity {
    DatabaseReference count,pa_check,elec_check;
    TextView name_display;
    EditText pass_Check;
    public static int prev_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_confirm);
        count= FirebaseDatabase.getInstance().getReference().child("candidates");
        pa_check=FirebaseDatabase.getInstance().getReference().child("users");
        name_display=findViewById(R.id.sel_can_name);
        pass_Check=findViewById(R.id.editText);
        elec_check=FirebaseDatabase.getInstance().getReference().child("vote_check");

    }

    @Override
    protected void onStart() {
        super.onStart();
        name_display.setText(candidate_name_sel);
    }

    public void Confirm_Password(View view)
    {

        final String check=pass_Check.getText().toString().trim();
        if (TextUtils.isEmpty(check)) {
            Toast.makeText(this, "Please enter password to confirm vote!!!!", Toast.LENGTH_LONG).show();
            return;
        }
        pa_check.child(prim).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    user password_user = dataSnapshot.getValue(user.class);
                    if (password_user.password.equals(check)) {
                        Toast.makeText(getApplicationContext(), "Your Vote has registered !", Toast.LENGTH_SHORT).show();
                        count.child(elec_id).child(candidate_name_sel).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists())
                                {
                                    candidates can=dataSnapshot.getValue(candidates.class);
                                    prev_count=can.count;
                                    candidates cann=new candidates(candidate_name_sel,prev_count+1,elec_id);
                                    count.child(elec_id).child(candidate_name_sel).setValue(cann);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        elec_check.child(prim).child(elec_id).setValue("voted");
                        finish();
                        startActivity(new Intent(Vote_confirm.this, voter_login.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Your password is wrong!!!Please enter correct password!!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
