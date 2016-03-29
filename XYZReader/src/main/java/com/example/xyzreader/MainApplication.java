package com.example.xyzreader;

import android.app.Application;

import com.example.xyzreader.network.model.DataModel;
import com.example.xyzreader.utils.LogLevel;
import com.example.xyzreader.utils.Logger;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import co.uk.rushorm.android.AndroidInitializeConfig;
import co.uk.rushorm.core.Rush;
import co.uk.rushorm.core.RushCore;

/**
 * Created by aditlal on 26/03/16.
 */
public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        Logger.init("XYZ")
                .setMethodCount(3)
                .setLogLevel(LogLevel.FULL);
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
        setupDB();
    }

    private void setupDB() {
        AndroidInitializeConfig config = new AndroidInitializeConfig(getApplicationContext());
        List<Class<? extends Rush>> classes = new ArrayList<>();
        classes.add(DataModel.class);
        config.setClasses(classes);
        RushCore.initialize(config);
    }


}
