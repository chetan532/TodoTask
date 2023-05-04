package manoj.cs.niu.edu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView todoDataTxt;
    FloatingActionButton fab;
    Toolbar mainToolbar;
    RecyclerView recyclerViewTodo;

    private DBManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the database
        databaseManager = new DBManager(this);

        mainToolbar = findViewById(R.id.mainToolbar);
        todoDataTxt = findViewById(R.id.todoDataTxt);
        fab = findViewById(R.id.fab);
        recyclerViewTodo = findViewById(R.id.recyclerViewTodo);

        setSupportActionBar(mainToolbar);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    void getDatabaseData() {

        //get the products from the database
        ArrayList<TodoModel> todoItems = databaseManager.selectAll();

        if (todoItems.size() > 0) {

            recyclerViewTodo.setVisibility(View.VISIBLE);
            todoDataTxt.setVisibility(View.GONE);

            recyclerViewTodo.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            recyclerViewTodo.setAdapter(new TodoAdapter(todoItems, databaseManager));

        } else {
            recyclerViewTodo.setVisibility(View.GONE);
            todoDataTxt.setVisibility(View.VISIBLE);

        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        getDatabaseData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menuAdd) {

            Intent intent = new Intent(this, InsertDataActivity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.menuEdit) {

            Intent intent = new Intent(this, UpdateDataActivity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.menuDelete) {

            Intent intent = new Intent(this, DeleteItemActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}