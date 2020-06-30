package com.andresvasquez.holamundoenclases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

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
import com.andresvasquez.holamundoenclases.repository.UserRepository;
import com.andresvasquez.holamundoenclases.repository.local.TasksRepository;
import com.andresvasquez.holamundoenclases.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MenuActivity extends AppCompatActivity {

    public static String LOG = MenuActivity.class.getName();

    private Context context;
    private List<QuarantineTask> items = new ArrayList<>();

    private Button addButton;
    private Button logoutButton;
    private TaskAdapter adapter;
    private ListView taskListView;

    //Paso 0: Definir la variable de repository
    private TasksRepository tasksRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        //Paso 1: Instanciar el repository
        tasksRepository = new TasksRepository(getApplication());

        setContentView(R.layout.activity_menu);
        Log.d(LOG, "onCreate");

        receiveValues();
        initViews();
        addEvents();
        //fillQuarantineTasks();

        //Paso 2: Observar los cambios en la db
        tasksRepository.getAll().observe(this, new Observer<List<QuarantineTask>>() {
            @Override
            public void onChanged(List<QuarantineTask> quarantineTasks) {
                Log.e("onChanged.quarantine", "" + quarantineTasks.size());
                //Paso 2a: Actualizamos nuestra lista en el activity
                items = quarantineTasks;

                //Paso 2b: Actualizamos nuestra lista en el adapter
                adapter.setItems(quarantineTasks);
                adapter.notifyDataSetChanged();
            }
        });

        tasksRepository.getFinishedCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("Finished Tasks", "" + integer.toString());
            }
        });
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
        logoutButton = findViewById(R.id.logoutButton);
        taskListView = findViewById(R.id.taskListView);
        adapter = new TaskAdapter(context, items);
        taskListView.setAdapter(adapter);
    }

    private void addEvents() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
                fillQuarantineTasks(randomNum);

                /*items.add(new QuarantineTask(items.size(), "Nuevo " + items.size(),
                        "20m", "Nuevo ejercicio creado", R.drawable.fitness));
                adapter.notifyDataSetChanged();
                taskListView.smoothScrollToPosition(items.size());*/
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasksRepository.deleteAll();
                UserRepository userRepository = new UserRepository(context);
                userRepository.deleteUserLogged();
                finish();
            }
        });

        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                QuarantineTask task = items.get(position);
                Intent detailsIntent = new Intent(context, TaskDetails.class);
                detailsIntent.putExtra("id", task.getId());
                startActivity(detailsIntent);

                /*items.clear();
                fillFitnessTasks();
                adapter.notifyDataSetChanged();*/
            }
        });
    }

    private void fillQuarantineTasks(int random) {
        QuarantineTask newTask = null;
        switch (random) {
            case 1:
                newTask = new QuarantineTask("Fitness",
                        "30m - 1h", "Ejercitate desde casa con Zumba", R.drawable.fitness);
                break;
            case 2:
                newTask = new QuarantineTask("Arbol",
                        "15m", "Ejercitate desde casa trepando al arbol", R.drawable.arbol);
                break;
            case 3:
                newTask = new QuarantineTask("Running",
                        "45m - 1h", "Ejercitate desde casa corriendo", R.drawable.running);
                break;
            case 4:
                newTask = new QuarantineTask("Trotar",
                        "30m", "Desde tu casa hasta la plaza, ida y vuelta", R.drawable.running);
                break;
            case 5:
                newTask = new QuarantineTask("Levantar pesas",
                        "1h",
                        "En el cuarto de pesas, si no tienes pesas, mete piedras a una mochila",
                        R.drawable.pesas);
                break;
            case 6:
                newTask = new QuarantineTask("Burpees",
                        "15m",
                        "4 series de 15", R.drawable.burpees);
                break;
        }

        if (newTask != null) {
            //Paso 3: Insertar nueva Tarea.
            tasksRepository.insert(newTask);
        }
    }

    private void fillFitnessTasks() {
        items.add(new QuarantineTask("Trotar",
                "30m", "Desde tu casa hasta la plaza, ida y vuelta", R.drawable.running));
        items.add(new QuarantineTask("Levantar pesas",
                "1h",
                "En el cuarto de pesas, si no tienes pesas, mete piedras a una mochila",
                R.drawable.pesas));
        items.add(new QuarantineTask("Burpees",
                "15m",
                "4 series de 15", R.drawable.burpees));
        items.add(new QuarantineTask("Abdominales",
                "15m",
                "4 series de 12", R.drawable.abs));
    }
}
