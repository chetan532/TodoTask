package asha.cs.niu.edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    ArrayList<TaskModel> todoItems;
    DatabaseManager dbManager;

    public HomeAdapter(ArrayList<TaskModel> todoItems, DatabaseManager dbManager) {
        this.todoItems = todoItems;
        this.dbManager = dbManager;
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.home_item,
                        parent, false);

        HomeViewHolder viewHolder
                = new HomeViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {


        holder.checkBox.setText(todoItems.get(position).getName());

        if (todoItems.get(position).getSelected().equals("0")) {
            holder.checkBox.setChecked(false);
        } else {
            holder.checkBox.setChecked(true);
        }


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                dbManager.updateByID(todoItems.get(position).getId(), todoItems.get(position).getName(), isChecked ? "1" : "0");
//                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBoxItem);

        }
    }
}
