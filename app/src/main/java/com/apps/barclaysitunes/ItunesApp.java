package com.apps.barclaysitunes;

import android.app.Application;

import com.apps.barclaysitunes.service.ServiceConnection;

/**
 * Created by Tharakaramu on 11/15/2016.
 */

public class ItunesApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        appComponent = DaggerAppComponent.builder()
//                .appModule(new ServiceConnection())
//                .build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }
}
