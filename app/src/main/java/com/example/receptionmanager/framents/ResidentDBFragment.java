package com.example.receptionmanager.framents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.receptionmanager.MainActivity;
import com.example.receptionmanager.R;
import com.example.receptionmanager.rec.ResidentRV;


public class ResidentDBFragment extends Fragment {

    public static RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resident_d_b, container, false);

        recyclerView = view.findViewById(R.id.recycler_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new ResidentRV(MainActivity.list));

        return view;
    }
}