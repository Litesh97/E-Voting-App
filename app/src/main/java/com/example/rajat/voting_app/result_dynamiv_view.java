package com.example.rajat.voting_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class result_dynamiv_view {
    private Context context;
    private TextView tv;
    private View view;

    public result_dynamiv_view(Context context) {
        this.context = context;
        init();
    }
    private void init() {
        LayoutInflater inflator= LayoutInflater.from(context);
        this.view= inflator.inflate(R.layout.result_dynamic_view, null);

        this.tv=(TextView) view.findViewById(R.id.result_view);

    }

    public Context getContext() {
        return context;
    }

    public TextView getTv() {
        return tv;
    }

    public View getView() {
        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public void setView(View view) {
        this.view = view;
    }
}
