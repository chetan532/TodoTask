package asha.cs.niu.edu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    RecyclerView recyclerViewUpdate;
    Button buttonGoBack;

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        databaseManager = new DatabaseManager(this);

        buttonGoBack = findViewById(R.id.buttonGoBack);

        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerViewUpdate = findViewById(R.id.recyclerViewUpdate);

        ArrayList<TaskModel> todoItems = databaseManager.getAllTasks();

        if (todoItems.size() > 0) {

            recyclerViewUpdate.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            recyclerViewUpdate.setAdapter(new UpdateAdapter(this, todoItems, databaseManager));

        }

    }
}