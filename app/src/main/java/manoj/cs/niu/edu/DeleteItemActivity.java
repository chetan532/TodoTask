package manoj.cs.niu.edu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DeleteItemActivity extends AppCompatActivity {

    Toolbar mainToolbar;
    RecyclerView recyclerViewTodo;
    Button buttonGoBack;

    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        dbManager = new DBManager(this);

        mainToolbar = findViewById(R.id.mainToolbar);
        recyclerViewTodo = findViewById(R.id.recyclerViewTodo);

        buttonGoBack = findViewById(R.id.buttonGoBack);

        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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