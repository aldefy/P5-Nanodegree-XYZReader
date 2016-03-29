package com.example.xyzreader.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;

import com.example.xyzreader.R;
import com.example.xyzreader.fragments.ListFragment;
import com.example.xyzreader.interfaces.ItemClickListener;
import com.example.xyzreader.network.model.DataModel;
import com.example.xyzreader.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by aditlal on 26/03/16.
 */
public class MainActivity extends AppCompatActivity implements ItemClickListener {
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.coordinator)
    CoordinatorLayout coordinator;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ActivityOptions options;
    private FragmentTransaction transaction;
    private ListFragment listFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        init();
    }

    private void setupToolbar() {
        toolbar.setTitle("XYZReader");
        setSupportActionBar(toolbar);

    }

    private void init() {
        transaction = getSupportFragmentManager().beginTransaction();
        listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag("fragGrid");
        if (listFragment == null)
            listFragment = new ListFragment();
        transaction.replace(R.id.frameContainer, listFragment, "fragGrid");
        transaction.commit();
    }


    @Override
    public void itemClicked(DataModel model, View view) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(Utils.DATA_MODEL, model);
        intent.putExtra(Utils.IMAGE_URL, model.getPhoto());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, view, "toolbarImage");
            options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, Pair.create(view, "toolbarImage"), Pair.create(view, "date"),Pair.create(view,"author"));
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
