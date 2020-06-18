package com.andresvasquez.holamundoenclases.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andresvasquez.holamundoenclases.R;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout parentLinearLayout;
    public TextView nameTextView;
    public TextView detailsTextView;
    public TextView durationTextView;
    public ImageView iconImageView;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        parentLinearLayout = itemView.findViewById(R.id.parentLinearLayout);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        detailsTextView = itemView.findViewById(R.id.detailsTextView);
        durationTextView = itemView.findViewById(R.id.durationTextView);
        iconImageView = itemView.findViewById(R.id.iconImageView);
    }
}
