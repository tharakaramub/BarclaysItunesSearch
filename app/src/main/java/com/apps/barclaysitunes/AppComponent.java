package com.apps.barclaysitunes;

import android.content.Context;

import com.apps.barclaysitunes.activity.HomeActivity;
import com.apps.barclaysitunes.service.ItunesAPI;
import com.apps.barclaysitunes.service.ServiceConnection;

import dagger.Component;


@AppScope
@Component(modules = ServiceConnection.class)
public interface AppComponent {

    void inject(HomeActivity activity);

    @AppScope
    Context appContext();

    ItunesAPI iTunesApiClient();
}
