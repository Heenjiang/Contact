package com.example.zoey.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainUI extends AppCompatActivity implements View.OnClickListener {
    private Button mFindOrAdd;
    private Button mShowAllContacs;
    private String TAG = "mylog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        Log.d(TAG, "onCreate: ");

        mFindOrAdd = findViewById(R.id.find_or_add_contact_bt);
        mShowAllContacs = findViewById(R.id.show_all_contacts_bt);

        mShowAllContacs.setOnClickListener(this);
        mFindOrAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_all_contacts_bt:
                Intent intent = new Intent(this,ShowContact.class);
                startActivity(intent);
                break;
            case R.id.find_or_add_contact_bt:
                Intent intent1 = new Intent(this,MainActivity.class);
                startActivity(intent1);
                break;
                default:
                    break;
        }
    }
}
