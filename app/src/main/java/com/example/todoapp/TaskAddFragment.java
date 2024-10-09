package com.example.todoapp;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskAddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button buttonSave;
    private Button buttonCancel;

    public TaskAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskAddFragment newInstance(String param1, String param2) {
        TaskAddFragment fragment = new TaskAddFragment();
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
        View view=inflater.inflate(R.layout.fragment_task_add, container, false);
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        buttonSave = view.findViewById(R.id.buttonSave);
        buttonCancel = view.findViewById(R.id.buttonCancel);

        buttonSave.setOnClickListener(v -> saveTask());
        buttonCancel.setOnClickListener(v -> cancelTask());
        TaskListFragment.getFAB().hide();
        return view;
    }
    private void saveTask() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();

        if (title.isEmpty()) {
            editTextTitle.setError("Title is required");
            return;
        }

        editTextTitle.setText("");
        editTextDescription.setText("");
        Task task=new Task(title,description,false);
        TaskClass.addTask(task);
        TaskListFragment.getTaskAdapter().notifyDataSetChanged();
        Toast.makeText(getActivity(), "Task saved", Toast.LENGTH_SHORT).show();

        getActivity().getSupportFragmentManager().popBackStack();
        TaskListFragment.getFAB().show();
    }

    private void cancelTask() {
        editTextTitle.setText("");
        editTextDescription.setText("");

        getActivity().getSupportFragmentManager().popBackStack();
        TaskListFragment.getFAB().show();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TaskListFragment.getFAB().hide();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getParentFragmentManager().popBackStack();
                TaskListFragment.getFAB().show();
            }
        });
    }

    @Override
    public void onResume() {
        TaskListFragment.getFAB().hide();
        super.onResume();
    }
}