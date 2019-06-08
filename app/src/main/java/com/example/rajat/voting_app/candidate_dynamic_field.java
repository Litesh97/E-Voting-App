package com.example.rajat.voting_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class candidate_dynamic_field {
        private Context context;
        private RadioButton tv;
        private View v;

        public candidate_dynamic_field(Context context) {

            // TODO Auto-generated constructor stub
            this.context= context;
            init();
        }

        private void init() {
            // TODO Auto-generated method stub
            LayoutInflater inflator= LayoutInflater.from(context);
            this.v= inflator.inflate(R.layout.candidate_dynamic_field, null);

            this.tv=(RadioButton) v.findViewById(R.id.candidate_name);

        }

        public View getView(){
            return v;
        }
        public void setTv(RadioButton tv) {
            this.tv = tv;
        }
        public RadioButton getTv() {
            return tv;
        }
}
