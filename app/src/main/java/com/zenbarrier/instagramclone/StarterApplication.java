package com.zenbarrier.instagramclone;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Anthony on 10/25/2016.
 * This file is the fragment that holds all the preferences
 */

public class StarterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(getString(R.string.parseAppId))
                .clientKey(null)
                .server(getString(R.string.parseServerUrl))
                .build());
    }
}
