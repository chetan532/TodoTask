package manoj.cs.niu.edu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class DeleteItemActivity extends AppCompatActivity {

    Toolbar mainToolbar;
    RecyclerView recyclerViewTodo;


    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        dbManager = new DBManager(this);

        mainToolbar = findViewById(R.id.mainToolbar);
        recyclerViewTodo = findViewById(R.id.recyclerViewTodo);

        setupEditData();
    }

    void setupEditData() {

        ArrayList<TodoModel> todoItems = dbManager.selectAll();

        if (todoItems.size() > 0) {

            recyclerViewTodo.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            recyclerViewTodo.setAdapter(new DeleteTodoAdapter(this, todoItems, dbManager));

        }

    }
}