package asha.cs.niu.edu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager = new DatabaseManager(this);

        setSupportActionBar(findViewById(R.id.toolbar));

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManager.deleteAll();
                getData();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        getData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.addItem) {

            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.updateItem) {

            Intent intent = new Intent(this, UpdateActivity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.deleteItem) {

            Intent intent = new Intent(this, DeleteActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    void getData() {

        ArrayList<TaskModel> taskData = databaseManager.getAllTasks();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView textView = findViewById(R.id.textView);

        if (taskData.size() > 0) {

            textView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);


            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(new HomeAdapter(taskData, databaseManager));

        } else {

            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }

    }
}