package manoj.cs.niu.edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoViewHolder> {

    ArrayList<TodoModel> todoItems;

    public TodoListAdapter(ArrayList<TodoModel> todoItems) {
        this.todoItems = todoItems;
    }

    @NonNull
    @Override
    public TodoListAdapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.todo_text_item,
                        parent, false);

        TodoViewHolder viewHolder
                = new TodoViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListAdapter.TodoViewHolder holder, int position) {


        holder.textItem.setText(todoItems.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView textItem;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            textItem = itemView.findViewById(R.id.textItem);

        }
    }
}
