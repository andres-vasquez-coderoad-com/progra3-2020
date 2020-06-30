package com.andresvasquez.holamundoenclases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andresvasquez.holamundoenclases.model.QuarantineTask;
import com.andresvasquez.holamundoenclases.repository.local.TasksRepository;

import org.w3c.dom.Text;

public class TaskDetails extends AppCompatActivity {

    private Context context;
    private ImageView imageView;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private Button finishButton;

    private QuarantineTask taskSelected;
    private TasksRepository tasksRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_task_details);
        tasksRepository = new TasksRepository(getApplication());

        initViews();
        addEvents();

        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            long id = intent.getLongExtra("id", 0);
            tasksRepository.getById(id).observe(this, new Observer<QuarantineTask>() {
                @Override
                public void onChanged(QuarantineTask task) {
                    taskSelected = task;
                    imageView.setImageResource(task.getImage());
                    titleTextView.setText(task.getName());
                    descriptionTextView.setText(task.getDetails());

                    if (task.isFinished()) {
                        finishButton.setText("Re-open");
                    } else {
                        finishButton.setText("Finish");
                    }
                }
            });
        }
    }

    private void initViews() {
        imageView = findViewById(R.id.imageView);
        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        finishButton = findViewById(R.id.finishButton);
    }

    private void addEvents() {
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskSelected.setFinished(!taskSelected.isFinished());
                tasksRepository.updateEntry(taskSelected);
            }
        });
    }
}
