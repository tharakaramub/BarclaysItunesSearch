package com.apps.barclaysitunes.ui;

import com.apps.barclaysitunes.AppComponent;
import com.apps.barclaysitunes.AppScope;
import com.apps.barclaysitunes.tests.MainActivityTest;

import dagger.Component;

@AppScope
@Component(modules = TestAppModule.class)
public interface TestAppComponent extends AppComponent {

    void inject(MainActivityTest test);
}
