package com.amazingtheme.colorcaller.callertheme.categoryui;

import android.content.Context;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    public static final String TAG = "ImagesAdapter";
    private Context context;
    private ArrayList<Images> arrayList;


    public ImagesAdapter(Context context, ArrayList<Images> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item, parent, false);
        return new ViewHolder(view/*,recyclerInterface*/);
    }

    /*  public void setImageDownloaded(boolean isImageDownloaded) {
          this.isImageDownloaded = isImageDownloaded;
      }*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(arrayList.get(position).getUrl()).into(holder.imageView);
        final Images temp = arrayList.get(position);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, CategoryShowActivity.class);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvApply;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvApply = itemView.findViewById(R.id.tv_apply);
            int dimension = (int) itemView.getContext().getResources().getDimension(R.dimen.pa);
            int i = (itemView.getContext().getResources().getDisplayMetrics().widthPixels - (dimension * 3)) / 2;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, (i * 640) / 360);
            layoutParams.addRule(14);
            layoutParams.setMargins(0, dimension, 0, 0);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

        }

    }

}
