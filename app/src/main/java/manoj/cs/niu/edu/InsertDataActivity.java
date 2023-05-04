package manoj.cs.niu.edu;

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

public class InsertDataActivity extends AppCompatActivity {

    Button buttonAdd, buttonGoBack;
    EditText editTextTodoName;
    Toolbar mainToolbar;

    DBManager dbManager;
    RecyclerView recyclerViewTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        dbManager = new DBManager(this);

        mainToolbar = findViewById(R.id.mainToolbar);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonGoBack = findViewById(R.id.buttonGoBack);
        editTextTodoName = findViewById(R.id.editTextTodoName);
        recyclerViewTodo = findViewById(R.id.recyclerViewTodo);

        setSupportActionBar(mainToolbar);

        getDatabaseData();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddItem();


            }
        });

        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void AddItem() {

        try {

            TodoModel todoModel = new TodoModel(0, editTextTodoName.getText().toString(), "0");

            editTextTodoName.getText().clear();
            dbManager.insertTodo(todoModel);

            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();

            getDatabaseData();

        } catch (Exception e) {

            Toast.makeText(this, "Exception", Toast.LENGTH_SHORT).show();

        }

    }


    void getDatabaseData() {

        //get the products from the database
        ArrayList<TodoModel> todoItems = dbManager.selectAll();

        if (todoItems.size() > 0) {

            recyclerViewTodo.setVisibility(View.VISIBLE);

            recyclerViewTodo.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            recyclerViewTodo.setAdapter(new TodoListAdapter(todoItems));

        }


    }
}