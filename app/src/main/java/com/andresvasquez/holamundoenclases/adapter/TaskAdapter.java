package com.andresvasquez.holamundoenclases.adapter;

import android.content.Context;

import com.andresvasquez.holamundoenclases.model.QuarantineTask;

import java.util.List;

public class TaskAdapter {

    private Context context;
    private List<QuarantineTask> items;

    public TaskAdapter(Context context, List<QuarantineTask> items) {
        this.context = context;
        this.items = items;
    }
}
