package com.example.nhatphuong.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<superman> mSupermanList = new ArrayList<>();
    RecyclerView recyclerView;
    RecycleviewAdater mAdater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
   LinearLayoutManager layout =new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        DividerItemDecoration divider = new DividerItemDecoration(this,layout.getOrientation());
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(layout);
        mSupermanList.add(new superman(R.drawable.superman,"superman"));
        mSupermanList.add(new superman(R.drawable.download,"woder woman"));
        mSupermanList.add(new superman(R.drawable.download,"woder woman"));
        mSupermanList.add(new superman(R.drawable.download,"woder woman"));
        mSupermanList.add(new superman(R.drawable.download,"woder woman"));
        mAdater = new RecycleviewAdater(getApplicationContext(), mSupermanList);
//        mAdater.updateData(mSupermanList);
        System.out.println(mSupermanList.get(1).getName());
        recyclerView.setAdapter(mAdater);



    }
}
