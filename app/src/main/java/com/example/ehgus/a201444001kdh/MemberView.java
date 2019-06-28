package com.example.ehgus.a201444001kdh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MemberView extends LinearLayout {

    TextView viewName;
    TextView viewID;
    TextView viewEmail;

    public MemberView(Context context) {
        super(context);

        init(context);
    }

    public MemberView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.member_item, this, true);

        viewName = (TextView) findViewById(R.id.viewName);
        viewID = (TextView) findViewById(R.id.viewID);
        viewEmail = (TextView) findViewById(R.id.viewEmail);
    }

    public void setViewName(String name) {
        viewName.setText(name);
    }

    public void setViewID(String id) {
        viewID.setText(id);
    }

    public void setViewEmail(String email) {
        viewEmail.setText(email);
    }

}
