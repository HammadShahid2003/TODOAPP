package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private List<Task> tasks;
    private Context context;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_task_design, parent, false);
        }

        TextView taskTitle = convertView.findViewById(R.id.taskTitle);
        TextView taskDescription = convertView.findViewById(R.id.taskDescription);
        CheckBox taskCheckBox = convertView.findViewById(R.id.taskCheckBox);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        taskTitle.setText(task.getName());
        taskDescription.setText(task.getDescription());
        taskCheckBox.setChecked(task.isCompleted());

        taskCheckBox.setOnCheckedChangeListener(null);
        taskCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            TaskManager.getInstanceCompleted().add(task);
            tasks.remove(position);
            Toast.makeText(context, task.getName()+" Completed", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        });

        deleteButton.setOnClickListener(v -> {
            tasks.remove(position);
            Toast.makeText(context, task.getName()+" Deleted", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        });

        return convertView;
    }
}
