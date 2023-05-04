package manoj.cs.niu.edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UpdateTodoAdapter extends RecyclerView.Adapter<UpdateTodoAdapter.TodoViewHolder> {

    ArrayList<TodoModel> todoItems;
    DBManager dbManager;
    Context context;

    public UpdateTodoAdapter(Context context,ArrayList<TodoModel> todoItems, DBManager dbManager) {
        this.todoItems = todoItems;
        this.dbManager = dbManager;
        this.context = context;
    }

    @NonNull
    @Override
    public UpdateTodoAdapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.update_todo_item,
                        parent, false);

        TodoViewHolder viewHolder
                = new TodoViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateTodoAdapter.TodoViewHolder holder, int position) {


        holder.todoName.setText(todoItems.get(position).getName());

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbManager.updateByID(todoItems.get(position).getId(), holder.todoName.getText().toString(), String.valueOf(todoItems.get(position).getSelected()));

                Toast.makeText(context, "Item Updated", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        EditText todoName;
        Button buttonAdd;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            todoName = itemView.findViewById(R.id.editTextTodoName);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);

        }
    }
}
