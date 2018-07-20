package com.example.nhatphuong.contentprovinder_demo_getcontact;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_READ_CONTACT = 1;
    private static final int REQUEST_READ_PHONE = 1;
    ArrayList<Contact> mContactArrayList;
    ContactAdapter mContactAdapter;
    RecyclerView mRecyclerView;
    Button mButton;
    Intent callIntent;
    EditText mEditTextName;
    EditText mEditTextPhone;
    ImageButton mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycleview);

        //ShowContactRecycleView(ReadContactAtDevice());
        //  checkPermissionContact();
        checkPermissionContact();
        checkPermissionPhone();
        mButton = findViewById(R.id.button_check);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkPermissionContact();
                ShowContactRecycleView(ReadContactAtDevice());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void checkPermissionContact() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] { Manifest.permission.READ_CONTACTS },
                        REQUEST_READ_CONTACT);
            }
        } else {
            //  ShowContactRecycleView(ReadContactAtDevice());

        }
    }

    public void checkPermissionPhone() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] { Manifest.permission.CALL_PHONE },
                        REQUEST_READ_PHONE);
            }
        } else {

        }
    }

    public void Call(String i) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + i));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
            Toast.makeText(this, i, Toast.LENGTH_SHORT).show();
        }
        //        startActivity(callIntent);
        //        Toast.makeText(this, i, Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Contact> ReadContactAtDevice() {
        Uri uri = CommonDataKinds.Phone.CONTENT_URI;
        mContactArrayList = new ArrayList<>();
        String ContactName = CommonDataKinds.Phone.DISPLAY_NAME;
        String ContractPhone = CommonDataKinds.Phone.NUMBER;
        String ojContract[] = { ContactName, ContractPhone };
        Cursor mCursor = getContentResolver().query(uri, ojContract, null, null, null);
        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                String name = mCursor.getString(0);
                String phone = mCursor.getString(1);
                mContactArrayList.add(new Contact(name, phone));
            } while (mCursor.moveToNext());
            mCursor.close();
        }
        return mContactArrayList;
    }

    public void ShowContactRecycleView(ArrayList<Contact> MangContact) {

        mContactAdapter = new ContactAdapter(MangContact, this);
        //        System.out.println(MangContact.get(1).getPhone());
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mContactAdapter);
    }

    public void add(MenuItem item) {
        final Dialog mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.dialog_addcontact);
        mEditTextName = mDialog.findViewById(R.id.edit_name);
        mEditTextPhone = mDialog.findViewById(R.id.edit_phone);
        mButtonAdd = mDialog.findViewById(R.id.button_themdialog);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeContact( mEditTextName.getText().toString(),
                        mEditTextPhone.getText().toString());
              mDialog.dismiss();
                ShowContactRecycleView(ReadContactAtDevice());
            }
        });
        mDialog.show();
    }

    public void Edit(MenuItem item) {
    }



    private void writeContact(String displayName, String number) {
        ArrayList contentProviderOperations = new ArrayList();
        //insert raw contact using RawContacts.CONTENT_URI
        contentProviderOperations.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
                .withValue(RawContacts.ACCOUNT_TYPE, null)
                .withValue(RawContacts.ACCOUNT_NAME, null)
                .build());
        //insert contact display name using Data.CONTENT_URI
        contentProviderOperations.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                .withValueBackReference(Data.RAW_CONTACT_ID, 0)
                .withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
                .withValue(StructuredName.DISPLAY_NAME, displayName)
                .build());
        //insert mobile number using Data.CONTENT_URI
        contentProviderOperations.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                .withValue(Phone.NUMBER, number)
                .withValue(Phone.TYPE, Phone.TYPE_MOBILE)
                .build());
        try {
            getApplicationContext().getContentResolver().
                    applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }
}
