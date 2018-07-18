package com.example.nhatphuong.appmusicservice;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import java.util.ArrayList;

public class SongAdater extends RecyclerView.Adapter<SongAdater.holder> {
    ArrayList<song> ArrayListSong;
    Context mContext;

    public SongAdater(Context context, ArrayList<song> arrayListSong) {
        mContext = context;
        ArrayListSong = arrayListSong;

    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext() ).inflate(R.layout.song,viewGroup,false);

        return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {
        final song songOBJ = ArrayListSong.get(position);
    holder.mTitle.setText(songOBJ.getTitle());
    holder.mArtist.setText(songOBJ.getArtist());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent= new Intent(mContext,Songservice.class);
//            intent.putExtra("idasd",songOBJ.getId());
//            System.out.println("ccc" +songOBJ.getId());
//            mContext.startService(intent);
            Intent mIntent = new Intent(mContext,SongPlay.class);
            mIntent.putExtra("data",songOBJ.getId());
            mContext.startActivity(mIntent);
        }
    });

    }

    @Override
    public int getItemCount() {
        return ArrayListSong.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        TextView mTitle,mArtist;

        public holder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.song_title);
            mArtist = itemView.findViewById(R.id.song_artist);

        }
    }

}
