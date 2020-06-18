package com.andresvasquez.holamundoenclases.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andresvasquez.holamundoenclases.R;
import com.andresvasquez.holamundoenclases.callback.QuarantineClickCallback;
import com.andresvasquez.holamundoenclases.model.QuarantineTask;

import java.util.List;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    private Context context;
    private List<QuarantineTask> items;
    private LayoutInflater inflater;
    private QuarantineClickCallback callback;

    public TaskRecyclerViewAdapter(Context context, List<QuarantineTask> items) {
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.quantine_task_layout, null);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        final QuarantineTask task = this.items.get(position);
        holder.nameTextView.setText(task.getName());
        holder.detailsTextView.setText(task.getDetails());
        holder.durationTextView.setText(task.getDuration());
        holder.iconImageView.setImageResource(task.getImage());

        holder.parentLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onTaskClicked(task);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void setCallback(QuarantineClickCallback callback) {
        this.callback = callback;
    }
}
