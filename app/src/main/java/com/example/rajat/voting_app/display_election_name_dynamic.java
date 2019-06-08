package com.example.rajat.voting_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class display_election_name_dynamic {
    private Context context;
    private TextView tv;
    private ToggleButton tb;
    private View view;
    public display_election_name_dynamic(Context context) {
        this.context = context;
        init();
    }
    private void init() {
        // TODO Auto-generated method stub
        LayoutInflater inflator= LayoutInflater.from(context);
        this.view= inflator.inflate(R.layout.display_election_names, null);
        this.tv=(TextView) view.findViewById(R.id.textView);
        this.tb=(ToggleButton) view.findViewById(R.id.toggleButton);

    }
    public Context getContext() {
        return context;
    }

    public TextView getTv() {
        return tv;
    }

    public ToggleButton getTb() {
        return tb;
    }

    public View getView() {
        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void settv(TextView textView) {
        this.tv = textView;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setTb(ToggleButton toggleButton) {
        this.tb = toggleButton;
    }
}
