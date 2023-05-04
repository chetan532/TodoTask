package asha.cs.niu.edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InsertListAdapter extends RecyclerView.Adapter<InsertListAdapter.ListViewHolder> {

    ArrayList<TaskModel> todoItems;

    public InsertListAdapter(ArrayList<TaskModel> todoItems) {
        this.todoItems = todoItems;
    }

    @NonNull
    @Override
    public InsertListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.insert_list_item,
                        parent, false);

        ListViewHolder viewHolder
                = new ListViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InsertListAdapter.ListViewHolder holder, int position) {


        holder.textItem.setText(todoItems.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView textItem;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            textItem = itemView.findViewById(R.id.textItem);

        }
    }
}
