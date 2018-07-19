package com.example.nhatphuong.sqlitedemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class ContactAdater extends RecyclerView.Adapter<ContactAdater.holder> {
    private MainActivity mContext;
    private ArrayList<Contact> MangContext;
    DataSqlite mDataSqlite;

    public ContactAdater(MainActivity context, ArrayList<Contact> mangContext) {
        mContext = context;
        MangContext = mangContext;
    }

    @NonNull
    @Override
    public ContactAdater.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contactlist, parent, false);
        return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactAdater.holder holder, int position) {
        final Contact contactOJB = MangContext.get(position);
        holder.mName.setText(contactOJB.getName());
        holder.mPhone.setText(contactOJB.getPhone());
        holder.mAddress.setText(contactOJB.getAddress());
        holder.mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.XoaContact(contactOJB.getID());
                mContext.getCursor();
            }
        });
        holder.mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mContext.SuaDialog(contactOJB.getID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return MangContext.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView mName, mPhone, mAddress;
        Button mButtonEdit, mButtonDelete;


        public holder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.text_name);
            mPhone = itemView.findViewById(R.id.text_phone);
            mAddress = itemView.findViewById(R.id.text_address);
            mButtonEdit = itemView.findViewById(R.id.button_edit);
            mButtonDelete = itemView.findViewById(R.id.button_Delete);

        }
    }
}
