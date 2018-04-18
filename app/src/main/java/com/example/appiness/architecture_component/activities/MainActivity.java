package com.example.appiness.architecture_component.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.appiness.architecture_component.models.EntityModel;
import com.example.appiness.architecture_component.viewModels.EntityListViewModel;
import com.example.appiness.architecture_component.R;
import com.example.appiness.architecture_component.adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EntityListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<EntityModel>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(EntityListViewModel.class);

        viewModel.getItemAndPersonList().observe(MainActivity.this, new Observer<List<EntityModel>>() {
            @Override
            public void onChanged(@Nullable List<EntityModel> itemAndPeople) {
                recyclerViewAdapter.addItems(itemAndPeople);
            }
        });



}


    @Override
    public void onClick(View v) {
        EntityModel entityModel = (EntityModel) v.getTag();
        viewModel.deleteItem(entityModel);
    }
}
