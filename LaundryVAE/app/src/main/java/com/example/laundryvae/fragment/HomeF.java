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
import android.widget.LinearLayout;

import com.example.laundryvae.R;
import com.example.laundryvae.adapter.HomeAdp;
import com.example.laundryvae.model.Item;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

public class HomeF extends Fragment {

    RecyclerView recyclerView;
    Context mContext;
    HomeAdp adp;
    List<Item> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        setAdapter();
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
    }

    private void setAdapter() {

        initModel();
        adp=new HomeAdp(mContext,list);
        recyclerView.setAdapter(adp);
    }

    private void initModel() {
        list=new ArrayList<>();
        list.add(new Item(R.drawable.washing,"Cleaning","€3,50"));
        list.add(new Item(R.drawable.panni,"Drying","€2,00"));
        list.add(new Item(R.drawable.ironing,"Ironing","€0,50"));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;

    }
}