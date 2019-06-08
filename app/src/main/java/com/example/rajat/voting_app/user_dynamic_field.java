package com.example.rajat.voting_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class user_dynamic_field {
    private Context context;
    private TextView textView;
    private EditText editText;
    private View view;

    public user_dynamic_field(Context context)
    {
        this.context=context;
        init();
    }
    private void init()
    {
        LayoutInflater inflator= LayoutInflater.from(context);
        this.view= inflator.inflate(R.layout.user_dynamic_field, null);
        this.textView=(TextView)view.findViewById(R.id.can_nam);
        this.editText=(EditText)view.findViewById(R.id.myet);

    }

    public Context getContext() {
        return context;
    }

    public TextView getTextView() {
        return textView;
    }

    public EditText getEditText() {
        return editText;
    }

    public View getView() {
        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public void setView(View view) {
        this.view = view;
    }
}
