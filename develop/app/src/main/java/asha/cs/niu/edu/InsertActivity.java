package asha.cs.niu.edu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {


    EditText editText;

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        databaseManager = new DatabaseManager(this);

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editText = findViewById(R.id.editText);

        findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    TaskModel todoModel = new TaskModel(0, editText.getText().toString(), "0");

                    editText.getText().clear();
                    databaseManager.insertTodo(todoModel);

                    Toast.makeText(InsertActivity.this, "Item Added", Toast.LENGTH_SHORT).show();

                    getData();

                } catch (Exception e) {

                    Toast.makeText(InsertActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void getData() {

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //get the products from the database
        ArrayList<TaskModel> tasks = databaseManager.getAllTasks();

        if (tasks.size() > 0) {

            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(new InsertListAdapter(tasks));

        }


    }
}