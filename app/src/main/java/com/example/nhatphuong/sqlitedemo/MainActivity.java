package com.example.nhatphuong.sqlitedemo;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataSqlite mDataSqlite;
    RecyclerView mRecyclerView;
    ImageButton mButtonAdd, mButtondialog;
    ArrayList<Contact> MangContact;
    ContactAdater mContactAdater;
    Button mSua;
    EditText mEditSua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        MangContact = new ArrayList<>();
        mDataSqlite = new DataSqlite(this, "danhba.sqlite", null, 1);
        //tao bang
        mDataSqlite.QueryData(
                "CREATE TABLE IF NOT EXISTS DanhBa(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name "
                        + "VARCHAR(200),Phone VARCHAR(200),Address VARCHAR(200))");
        mContactAdater = new ContactAdater(this, MangContact);
        //        mDataSqlite.QueryData("INSERT INTO DanhBa VALUES (null,'Phuong','09123123',
        // 'Quang Nam, Da Nang')");
        //        mDataSqlite.QueryData("INSERT INTO DanhBa VALUES (null,'Nam','090123123','Quang
        // Nam, Da Nang')");
        getCursor();
       // mContactAdater = new ContactAdater(this, MangContact);
       // mContactAdater.notifyDataSetChanged();

        mRecyclerView.setAdapter(mContactAdater);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogThem();
            }
        });
    }

    public void XoaContact(int i) {
        {
            //mDataSqlite = new DataSqlite(this, "danhba.sqlite", null, 1);
            mDataSqlite.QueryData("DELETE FROM DanhBa WHERE Id ='" + i + "'");
        }
    }
    public void SuaDialog(final int id){
        final Dialog mDialog1 = new Dialog(this);
        mDialog1.setContentView(R.layout.dialog_sua);
        mDialog1.show();
        mSua = mDialog1.findViewById(R.id.button_suadialog);
        mEditSua =mDialog1.findViewById(R.id.dialog_sua);
            mSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDataSqlite.QueryData("UPDATE DanhBa SET Phone ='" + mEditSua.getText() + "' WHERE Id='"+id+"'");
              Toast.makeText(getApplicationContext(), "ĐÃ CẬP NHẬT", Toast.LENGTH_SHORT).show();
              mDialog1.dismiss();
              getCursor();
            }
        });

    }

    public void DialogThem() {
        final Dialog mdialog = new Dialog(this);
        mdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mdialog.setContentView(R.layout.dialog_them);
        mdialog.show();
        final EditText mTname, mTphone, mTaddress;
        mTname = mdialog.findViewById(R.id.edit_name);
        mTphone = mdialog.findViewById(R.id.edit_phone);
        mTaddress = mdialog.findViewById(R.id.edit_adress);
        mButtondialog = mdialog.findViewById(R.id.button_themdialog);
        mButtondialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTname.getText() == null || mTphone == null) {
                    Toast.makeText(MainActivity.this, "vui long nhập tên và số điện thoại",
                            Toast.LENGTH_SHORT).show();
                } else {
                    mDataSqlite.QueryData("INSERT INTO DanhBa VALUES (null,"
                            + "'"
                            + mTname.getText()
                            + "',"
                            + "'"
                            + mTphone.getText()
                            + "',"
                            + "'"
                            + mTaddress.getText()
                            + "')");
                    getCursor();

                    mContactAdater.notifyDataSetChanged();
                    mdialog.dismiss();
                }
            }
        });
    }

    public void initView() {
        mRecyclerView = findViewById(R.id.recycleviewContact);
        mButtonAdd = findViewById(R.id.button_them);

    }

    public void getCursor() {
        Cursor getData = mDataSqlite.Getdata("SELECT * FROM Danhba ");
        MangContact.clear();
        while (getData.moveToNext()) {
            int ID = getData.getInt(0);
            String NAME = getData.getString(1);
            String Phone = getData.getString(2);
            String ADDRESS = getData.getString(3);
            MangContact.add(new Contact(ID, NAME, Phone, ADDRESS));
        }

     mContactAdater.notifyDataSetChanged();
    }
}
