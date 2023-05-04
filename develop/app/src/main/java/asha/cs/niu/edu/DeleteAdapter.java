package asha.cs.niu.edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeleteAdapter extends RecyclerView.Adapter<DeleteAdapter.DeleteViewHolder> {

    ArrayList<TaskModel> todoItems;
    DatabaseManager dbManager;
    Context context;

    public DeleteAdapter(Context context, ArrayList<TaskModel> todoItems, DatabaseManager dbManager) {
        this.todoItems = todoItems;
        this.dbManager = dbManager;
        this.context = context;
    }

    @NonNull
    @Override
    public DeleteAdapter.DeleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.delete_item,
                        parent, false);

        DeleteViewHolder viewHolder
                = new DeleteViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteAdapter.DeleteViewHolder holder, int position) {


        holder.todoName.setText(todoItems.get(position).getName());

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbManager.deleteById(todoItems.get(position).getId());
                todoItems.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public class DeleteViewHolder extends RecyclerView.ViewHolder {

        TextView todoName;
        Button buttonAdd;

        public DeleteViewHolder(@NonNull View itemView) {
            super(itemView);

            todoName = itemView.findViewById(R.id.editTextTodoName);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);

        }
    }
}
