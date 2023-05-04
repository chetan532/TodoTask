package asha.cs.niu.edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.TaskViewHolder> {

    ArrayList<TaskModel> todoItems;
    DatabaseManager dbManager;
    Context context;

    public UpdateAdapter(Context context, ArrayList<TaskModel> todoItems, DatabaseManager dbManager) {
        this.todoItems = todoItems;
        this.dbManager = dbManager;
        this.context = context;
    }

    @NonNull
    @Override
    public UpdateAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.update_item,
                        parent, false);

        TaskViewHolder viewHolder
                = new TaskViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateAdapter.TaskViewHolder holder, int position) {


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

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        EditText todoName;
        Button buttonAdd;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            todoName = itemView.findViewById(R.id.editTextTodoName);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);

        }
    }
}
