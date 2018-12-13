package com.example.zoey.contact;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zoey.contact.entity.Contact;
import com.example.zoey.contact.helper.ContactDBTable;
import com.example.zoey.contact.helper.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mName;
    private EditText mPhoneNumber;
    private Button mFindBt;
    private Button mSaveBt;
    private Button mBackBt;
    private static String TAG = "mylog";
    private DBHelper mDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

        mDBHelper = new DBHelper(this);

        mFindBt = findViewById(R.id.find_contact);
        mFindBt.setOnClickListener(this);

        mBackBt = this.findViewById(R.id.to_main_bt);
//        mBackBt.setOnClickListener(this);

        mSaveBt = findViewById(R.id.save_contact);
        mSaveBt.setOnClickListener(this);

        mName = findViewById(R.id.text_name);
        mPhoneNumber = findViewById(R.id.text_phone_number);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void finish() {
        Log.d(TAG, "finish: ");
        super.finish();
    }

    @Override
    public void onClick(View v) {
        String name = mName.getText().toString();
        String phoneNumber = mPhoneNumber.getText().toString();
        switch (v.getId()){
            case R.id.find_contact:
                clean();
                if(name.equals("")){
                    Toast.makeText(this, "please input the name" + name + phoneNumber, Toast.LENGTH_SHORT).show();
                }
                else{
                    Contact contact = findContactByName(name);
                    if(contact == null){
                        Toast.makeText(this, "No such contact in SQLite database" + name + phoneNumber, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        setValue(contact);
                    }
                }

                break;
            case R.id.save_contact:
                clean();
                if(!name.equals("") && !phoneNumber.equals("")){
                    if(saveContactInDB(name, phoneNumber) < 0){
                        Toast.makeText(this, "save failed!" + name + phoneNumber, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "save success!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "name or phone number is null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back_bt:
                finish();
                break;
            default:
                break;
        }

    }

    private Contact findContactByName(String name) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        Cursor cursor =  db.rawQuery(ContactDBTable.QUERY_CONTACT, new String[]{name.toString()});
        Contact contact = null;
        //存在数据才返回true
        if(cursor.moveToFirst()) {
            int contactId = cursor.getInt(cursor.getColumnIndex(ContactDBTable.CONTACT_ID));
            String contactName = cursor.getString(cursor.getColumnIndex(ContactDBTable.CONTACT_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(ContactDBTable.CONTACT_PHONE));
            contact =  new Contact(contactId,contactName,phone);
        }
        cursor.close();
        return contact;
    }


    private long saveContactInDB(String name, String phoneNumber) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(ContactDBTable.CONTACT_NAME, name);
        values1.put(ContactDBTable.CONTACT_PHONE, phoneNumber);
        return (db.insert(ContactDBTable.TABLE_NAME,null, values1));
    }

    private void clean(){
        mName.setText("");
        mPhoneNumber.setText("");
    }
    private void setValue(Contact contact){
        mName.setText(contact.getName());
        mPhoneNumber.setText(contact.getPhoneNumber());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDBHelper.close();
    }
}
