package asha.cs.niu.edu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {


    RecyclerView recyclerViewDelete;
    Button buttonGoBack;

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        recyclerViewDelete = findViewById(R.id.recyclerViewDelete);
        buttonGoBack = findViewById(R.id.buttonGoBack);

        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        databaseManager = new DatabaseManager(this);

        ArrayList<TaskModel> todoItems = databaseManager.getAllTasks();

        if (todoItems.size() > 0) {

            recyclerViewDelete.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            recyclerViewDelete.setAdapter(new DeleteAdapter(this, todoItems, databaseManager));

        }
    }
}