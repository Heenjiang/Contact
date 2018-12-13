package com.example.zoey.contact.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zoey.contact.R;
import com.example.zoey.contact.entity.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private String TAG = "mylog";
    private Context context;
    private List<Contact> list;

    public ContactAdapter(Context context, List<Contact> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_item, viewGroup, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: ");
        Contact contact = list.get(i);
        contactViewHolder.mContactName.setText(contact.getName());
        contactViewHolder.mContactPhone.setText(contact.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        private TextView mContactName;
        private TextView mContactPhone;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            mContactName = itemView.findViewById(R.id.contact_name);
            mContactPhone = itemView.findViewById(R.id.contact_phone);
            //可以在这里设置点击方法
        }
    }
}
