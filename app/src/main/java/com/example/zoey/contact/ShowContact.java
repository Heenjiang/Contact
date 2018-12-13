package com.example.zoey.contact;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zoey.contact.adapter.ContactAdapter;
import com.example.zoey.contact.entity.Contact;
import com.example.zoey.contact.helper.ContactDBTable;
import com.example.zoey.contact.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ShowContact extends AppCompatActivity implements View.OnClickListener {
    private List<Contact> list ;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ContactAdapter mAdapter;
    private Button mBackBt;
    private String TAG = "mylog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper mDBHelper = new DBHelper(this);
        setContentView(R.layout.activity_show_contact);
        //初始化获得数据
        list = queryAll(mDBHelper.getWritableDatabase());

        mBackBt = findViewById(R.id.back_bt);
        mBackBt.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.contact_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ContactAdapter(ShowContact.this, list);
        mRecyclerView.setAdapter(mAdapter);
    }
    public List<Contact> queryAll(SQLiteDatabase db){
        List<Contact> person = new ArrayList<Contact>();
        Log.d(TAG, "queryAll: " + ContactDBTable.QUERY_ALL_CONTACT);
        Cursor cursor =  db.rawQuery(ContactDBTable.QUERY_ALL_CONTACT, null);
        if(cursor == null){
            return null;
        }
        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex(ContactDBTable.CONTACT_ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactDBTable.CONTACT_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(ContactDBTable.CONTACT_PHONE));
            person.add(new Contact(id,name,phone)) ;
        }
        cursor.close();
        return person;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_bt:
                finish();
                break;
                default:
                    break;
        }
    }
}
