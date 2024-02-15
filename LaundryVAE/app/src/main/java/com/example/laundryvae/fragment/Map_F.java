package com.example.laundryvae.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laundryvae.R;
import com.example.laundryvae.adapter.HomeAdp;
import com.example.laundryvae.adapter.SearchAdp;
import com.example.laundryvae.model.Item;
import com.example.laundryvae.model.Search;

import java.util.ArrayList;
import java.util.List;

public class Map_F extends Fragment {

    RecyclerView recyclerView;
    Context mContext;
    SearchAdp adp;
    List<Search> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        setAdapter();
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.location_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
    }

    private void setAdapter() {

        initModel();
        adp=new SearchAdp(mContext,list);
        recyclerView.setAdapter(adp);
    }

    private void initModel() {
        list=new ArrayList<>();
        list.add(new Search(R.drawable.brno1,"Laundry VAE 1","4,8","1,2km"));
        list.add(new Search(R.drawable.brno2,"Laundry VAE 2","3,4","0,2km"));
        list.add(new Search(R.drawable.brno3,"Laundry VAE 3","4,3","3,8km"));

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;

    }
}