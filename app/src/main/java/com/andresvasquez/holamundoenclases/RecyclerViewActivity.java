package com.andresvasquez.holamundoenclases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.andresvasquez.holamundoenclases.adapter.TaskRecyclerViewAdapter;
import com.andresvasquez.holamundoenclases.callback.QuarantineClickCallback;
import com.andresvasquez.holamundoenclases.model.QuarantineTask;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    public static String LOG = RecyclerViewActivity.class.getName();

    private Context context;
    private List<QuarantineTask> items = new ArrayList<>();

    private RecyclerView tasksRecyclerView;
    private TaskRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_recycler_view);

        initViews();
        addEvents();
        fillQuarantineTasks();
        fillQuarantineTasks();
        fillQuarantineTasks();
    }

    private void initViews() {
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        adapter = new TaskRecyclerViewAdapter(context, items);
        tasksRecyclerView.setAdapter(adapter);
        //tasksRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        tasksRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        /*tasksRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));*/
    }

    private void addEvents() {
        adapter.setCallback(new QuarantineClickCallback() {
            @Override
            public void onTaskClicked(QuarantineTask task) {
                Log.e("onTaskClicked", task.getName());
                Toast.makeText(context, "Tarea: " + task.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillQuarantineTasks() {
        items.add(new QuarantineTask(items.size(), "Burpees",
                "15m",
                "4 series de 15", R.drawable.burpees));
        items.add(new QuarantineTask(items.size(), "Abdominales",
                "15m",
                "4 series de 12", R.drawable.abs));
        items.add(new QuarantineTask(items.size(), "Trotar",
                "30m", "Desde tu casa hasta la plaza, ida y vuelta", R.drawable.running));
        items.add(new QuarantineTask(items.size(), "Levantar pesas",
                "1h",
                "En el cuarto de pesas, si no tienes pesas, mete piedras a una mochila",
                R.drawable.pesas));
    }
}
