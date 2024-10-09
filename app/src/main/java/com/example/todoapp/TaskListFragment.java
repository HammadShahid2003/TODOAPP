package com.example.todoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.todoapp.Adapter.TaskAdapter;
import com.example.todoapp.Model.Task;
import com.example.todoapp.Model.TaskManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ListView listView;
    private static TaskAdapter taskAdapter;
    private static FloatingActionButton fabAddTask;
    public TaskListFragment() {
        // Required empty public constructor
    }
    public static FloatingActionButton getFAB()
    {
        return fabAddTask;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskListFragment newInstance(String param1, String param2) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_task_list, container, false);
        listView = view.findViewById(R.id.listView);
        taskAdapter = new TaskAdapter(getContext(), TaskManager.getInstance());
        listView.setAdapter(taskAdapter);
        fabAddTask=view.findViewById(R.id.fabAddTask);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fabAddTask.show();
    }

    public FloatingActionButton getFabAddTask() {
        return fabAddTask;
    }

    public static TaskAdapter getTaskAdapter()
    {
        return taskAdapter;
    }
}