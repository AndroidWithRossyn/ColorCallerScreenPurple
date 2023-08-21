package com.amazingtheme.colorcaller.callertheme.categoryui;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amazingtheme.colorcaller.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavouritesLiveAdapter extends RecyclerView.Adapter<FavouritesLiveAdapter.ViewHolder> {
    private ArrayList<Images> favoriteDataList;
    private Context context;

    public FavouritesLiveAdapter(Context context, ArrayList<Images> favoriteDataList) {
        this.context = context;
        this.favoriteDataList = favoriteDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Images data = favoriteDataList.get(position);
        Uri videoUri = Uri.parse(data.getUrl());
        holder.favoriteImage.setVideoURI(videoUri);
        holder.favoriteImage.start();
        holder.favoriteImage.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0f, 0f);
            }
        });

        holder.favoriteImage.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                holder.favoriteImage.setVisibility(View.VISIBLE);
                holder.favoriteImage.start();
            }
        });


    }

    @Override
    public int getItemCount() {
        return favoriteDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FullScreenVideoView favoriteImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favoriteImage = itemView.findViewById(R.id.videoView);
        }
    }
}
