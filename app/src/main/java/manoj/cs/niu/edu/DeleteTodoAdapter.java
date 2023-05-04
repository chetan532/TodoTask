package manoj.cs.niu.edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeleteTodoAdapter extends RecyclerView.Adapter<DeleteTodoAdapter.TodoViewHolder> {

    ArrayList<TodoModel> todoItems;
    DBManager dbManager;
    Context context;

    public DeleteTodoAdapter(Context context, ArrayList<TodoModel> todoItems, DBManager dbManager) {
        this.todoItems = todoItems;
        this.dbManager = dbManager;
        this.context = context;
    }

    @NonNull
    @Override
    public DeleteTodoAdapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.delete_todo_item,
                        parent, false);

        TodoViewHolder viewHolder
                = new TodoViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteTodoAdapter.TodoViewHolder holder, int position) {


        holder.todoName.setText(todoItems.get(position).getName());

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
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

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView todoName;
        Button buttonDelete;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            todoName = itemView.findViewById(R.id.editTextTodoName);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

        }
    }
}
