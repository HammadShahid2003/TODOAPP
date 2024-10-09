package com.example.todoapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabAddTask;
    private TaskAddFragment taskAddFragment;
    private TaskListFragment taskListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        taskAddFragment = new TaskAddFragment();
        taskListFragment=(TaskListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListTask);
        fabAddTask = taskListFragment.getFabAddTask();
        fabAddTask.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentListTask, taskAddFragment)
                    .addToBackStack(null)
                    .commit();
        });

    }
}