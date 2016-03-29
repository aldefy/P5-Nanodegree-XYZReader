package com.example.xyzreader.interfaces;

import android.view.View;

import com.example.xyzreader.network.model.DataModel;

/**
 * Created by aditlal on 09/12/15.
 */
public interface ItemClickListener {
    void itemClicked(DataModel model, View view);
}
