package com.amazingtheme.colorcaller.callertheme.categoryui.linearCategory.kpopCategory;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.callertheme.categoryui.CategoryShowVideoActivity;
import com.amazingtheme.colorcaller.callertheme.categoryui.FullScreenVideoView;
import com.amazingtheme.colorcaller.callertheme.categoryui.Images;

import java.util.ArrayList;

public class LinearKpopVideoAdapter extends RecyclerView.Adapter<LinearKpopVideoAdapter.ViewHolder> {
    public static final String TAG = "LinearKpopVideoAdapter";
    private Context context;
    private ArrayList<Images> arrayList;

    public LinearKpopVideoAdapter(Context context, ArrayList<Images> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item_linear_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Images temp = arrayList.get(position);
        final Uri videoUri = Uri.parse(temp.getUrl());

        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
                mp.setLooping(true);
                mp.start();
            }
        });

        holder.videoView.setOnClickListener(new View.OnClickListener() {
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


        holder.videoView.post(new Runnable() {
            @Override
            public void run() {
                // Check if the current view holder is bound to the correct position
                if (holder.getAdapterPosition() == position) {
                    holder.videoView.setVideoURI(videoUri);
                    holder.videoView.start();
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return Math.min(arrayList.size(), 5);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FullScreenVideoView videoView;
        TextView tvApply;

        public ViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.imageView);
            tvApply = itemView.findViewById(R.id.tv_apply);
        }
    }
}

