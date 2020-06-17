package com.andresvasquez.holamundoenclases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.andresvasquez.holamundoenclases.adapter.TaskAdapter;
import com.andresvasquez.holamundoenclases.model.QuarantineTask;
import com.andresvasquez.holamundoenclases.model.User;
import com.andresvasquez.holamundoenclases.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    public static String LOG = MenuActivity.class.getName();

    private Context context;
    private List<QuarantineTask> items = new ArrayList<>();

    private Button addButton;
    private TaskAdapter adapter;
    private ListView taskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        setContentView(R.layout.activity_menu);
        Log.d(LOG, "onCreate");

        receiveValues();
        initViews();
        addEvents();
        fillQuarantineTasks();
        fillQuarantineTasks();
        fillQuarantineTasks();
        fillQuarantineTasks();
        fillQuarantineTasks();
    }

    private void receiveValues() {
        //1. Obtenemos el canal
        Intent intent = getIntent();

        //2. Verificamos que el canal tenga el mensaje con la clave: "objUser"
        if (intent.hasExtra(Constants.INTENT_KEY_USER)) {

            //3. Obtener el valor del mensaje con la clave: "objUser"
            String userObj = intent.getStringExtra(Constants.INTENT_KEY_USER);

            //4. Convertir el String a un Objeto
            User user = new Gson().fromJson(userObj, User.class);

            Toast.makeText(MenuActivity.this, //Origen
                    "Bienvenid@: " + user.getName(), //Mensaje
                    Toast.LENGTH_SHORT) //Duracion
                    .show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG, "onDestroy");
    }

    private void initViews() {
        addButton = findViewById(R.id.addButton);
        taskListView = findViewById(R.id.taskListView);
        adapter = new TaskAdapter(context, items);
        taskListView.setAdapter(adapter);
    }

    private void addEvents() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(new QuarantineTask(items.size(), "Nuevo " + items.size(),
                        "20m", "Nuevo ejercicio creado", R.drawable.fitness));
                adapter.notifyDataSetChanged();
                taskListView.smoothScrollToPosition(items.size());
            }
        });

        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                QuarantineTask task = items.get(position);
                Log.e("onItemClick", task.getName());
            }
        });
    }

    private void fillQuarantineTasks() {
        items.add(new QuarantineTask(items.size(), "Trotar",
                "30m", "Desde tu casa hasta la plaza, ida y vuelta", R.drawable.running));
        items.add(new QuarantineTask(items.size(), "Levantar pesas",
                "1h",
                "En el cuarto de pesas, si no tienes pesas, mete piedras a una mochila",
                R.drawable.pesas));
        items.add(new QuarantineTask(items.size(), "Burpees",
                "15m",
                "4 series de 15", R.drawable.burpees));
        items.add(new QuarantineTask(items.size(), "Abdominales",
                "15m",
                "4 series de 12", R.drawable.abs));
    }
}
