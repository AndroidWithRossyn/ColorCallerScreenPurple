package com.amazingtheme.colorcaller.callertheme.categoryui.linearCategory.kpopCategory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.callertheme.categoryui.PreviewActivity;
import com.amazingtheme.colorcaller.models.CallReceiveRejectCall;

import java.util.ArrayList;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {
    ArrayList<CallReceiveRejectCall> callReceiveRejectCalls;
    Context context;


    public IconAdapter(ArrayList<CallReceiveRejectCall> arrayList, Context context2) {
        this.callReceiveRejectCalls = arrayList;
        this.context = context2;

    }


    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.icon_layout_design, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CallReceiveRejectCall item = callReceiveRejectCalls.get(position);

        // Set the appropriate images for the ImageView elements
        holder.reccive_call.setImageResource(item.getReceive());
        holder.reject_call.setImageResource(item.getReject());

        // Set click listeners for the ImageView elements
        holder.reccive_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle receive call button click
                Log.d("IconAdapter", "Receive call button clicked at position: " + position);

                // Store the receive icon in SharedPreferences
                SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("receiveIcon", item.getReceive());
                editor.apply();
                SharedPreferences anotherSharedPreferences = context.getSharedPreferences("AnotherPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor anotherEditor = anotherSharedPreferences.edit();

                // Store the receive icon in another SharedPreferences
                anotherEditor.putInt("receiveIcon", item.getReceive());
                anotherEditor.apply();
            }
        });

        holder.reject_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle reject call button click
                Log.d("IconAdapter", "Reject call button clicked at position: " + position);

                // Store the reject icon in SharedPreferences
                SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("rejectIcon", item.getReject());
                editor.apply();
                SharedPreferences anotherSharedPreferences = context.getSharedPreferences("AnotherPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor anotherEditor = anotherSharedPreferences.edit();

                // Store the reject icon in another SharedPreferences
                anotherEditor.putInt("rejectIcon", item.getReject());
                anotherEditor.apply();
            }
        });

        // Set click listener for rl_icon button
        holder.rl_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences anotherSharedPreferences = context.getSharedPreferences("AnotherPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor anotherEditor = anotherSharedPreferences.edit();
                // Store the values of rejectIcon and receiveIcon into another SharedPreferences

                anotherEditor.putInt("rejectIcon", item.getReject());
                anotherEditor.putInt("receiveIcon", item.getReceive());
                anotherEditor.apply();
                Intent intent = new Intent(context, PreviewActivity.class);
                context.startActivity(intent);

            }
        });
    }


    public int getItemCount() {
        return this.callReceiveRejectCalls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rl_icon;
        ImageView reccive_call;
        ImageView reject_call;

        public ViewHolder(View view) {
            super(view);
            this.reccive_call = (ImageView) view.findViewById(R.id.reccive_call);
            this.reject_call = (ImageView) view.findViewById(R.id.reject_call);
            this.rl_icon = (LinearLayout) view.findViewById(R.id.liner_main);

        }
    }
}
