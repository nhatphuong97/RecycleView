package com.example.nhatphuong.contentprovinder_demo_getcontact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.holder> {
    ArrayList<Contact> mContactArrayList;
    MainActivity mContext;
    Contact mContactoBJ;

    public ContactAdapter(ArrayList<Contact> contactArrayList, MainActivity context) {
        mContactArrayList = contactArrayList;
        mContext = context;
    }

    @NonNull
    @Override
    public ContactAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactAdapter.holder holder, final int position) {
        holder.BindData(mContactArrayList.get(position));
        holder.mButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(mContext, "asdasd asdd", Toast.LENGTH_SHORT).show();
                holder.CallPhone(mContactArrayList.get(position).getPhone());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactArrayList.size();
    }

    public class holder extends ViewHolder {
        TextView mTextName, mTextPhone;
        ImageButton mButtonPhone;

        public holder(View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextPhone = itemView.findViewById(R.id.text_phone);
            mButtonPhone = itemView.findViewById(R.id.button_phone);
        }

        public void BindData(Contact contact) {
            if (contact != null) {
                mTextName.setText(contact.getName());
                mTextPhone.setText(contact.getPhone());
            }
        }

        public void CallPhone(String i) {
            mContext.Call(i);

        }
    }
}
