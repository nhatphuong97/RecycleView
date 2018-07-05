package com.example.nhatphuong.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecycleviewAdater extends RecyclerView.Adapter<RecycleviewAdater.holder> {
    Context mContext;
    List<superman> mList = new ArrayList<>();

    public RecycleviewAdater(Context context, List<superman> list) {
        mContext = context;
        mList = list;

    }

    public void updateData(List<superman> list) {
        if (list != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }


    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {
        superman supermanOBJ = mList.get(position);
        holder.sImage.setImageResource(supermanOBJ.getImage());
        holder.sTsextview.setText(supermanOBJ.getName());


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        public TextView sTsextview;
        public ImageView sImage;

        public holder(View itemView) {
            super(itemView);
            sImage = itemView.findViewById(R.id.image1);
            sTsextview = itemView.findViewById(R.id.txt);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,sTsextview.getText(),Toast.LENGTH_LONG).show();
            }
        });
        }
    }
}
