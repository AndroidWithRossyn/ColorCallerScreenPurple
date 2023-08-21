package com.amazingtheme.colorcaller.callertheme.categoryui;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amazingtheme.colorcaller.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import pl.droidsonroids.gif.GifImageView;

public class ImagesVideoAdapter extends RecyclerView.Adapter<ImagesVideoAdapter.ViewHolder> {
    //    private final RecyclerInterface recyclerInterface;
    public static final String TAG = "ImagesVideoAdapter";
    private Context context;
    private ArrayList<Images> arrayList;

    public ImagesVideoAdapter(Context context, ArrayList<Images> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ImagesVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item_video, parent, false);
        return new ImagesVideoAdapter.ViewHolder(view/*,recyclerInterface*/);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesVideoAdapter.ViewHolder holder, int position) {
        final Images temp = arrayList.get(position);
        Uri videoUri = Uri.parse(temp.getUrl());
        holder.imageView.setVideoURI(videoUri);
        holder.imageView.start();

// Mute the audio
        holder.imageView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0f, 0f);
            }
        });

        holder.imageView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                holder.imageView.setVisibility(View.VISIBLE);
                holder.imageView.start();
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoryShowVideoActivity.class);
                ArrayList<? extends Parcelable> parcelableList = new ArrayList<>(arrayList);
                intent.putParcelableArrayListExtra("imageUrl", parcelableList);
                intent.putExtra("position", position);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FullScreenVideoView imageView;
        TextView tvApply;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.videoView);
            tvApply = itemView.findViewById(R.id.tv_apply);
        }

    }
}

