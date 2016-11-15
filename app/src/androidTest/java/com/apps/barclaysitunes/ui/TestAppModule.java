package com.apps.barclaysitunes.ui;

import android.content.Context;

import com.apps.barclaysitunes.AppScope;
import com.apps.barclaysitunes.rest.MockItunesApi;
import com.apps.barclaysitunes.service.ItunesAPI;

import dagger.Module;
import dagger.Provides;

@Module
public class TestAppModule {

    private final Context context;

    public TestAppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @AppScope
    public Context provideAppContext() {
        return context;
    }

    @Provides
    public ItunesAPI provideWeatherApiClient() {
        return new MockItunesApi();
    }
}
