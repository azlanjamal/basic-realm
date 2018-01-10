package com.azlanjamal.basicrealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by akmalazlan on 10/01/2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("realmBasic.realm")
                .build();

        Realm.setDefaultConfiguration(configuration);
    }
}
