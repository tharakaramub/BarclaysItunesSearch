package com.apps.barclaysitunes;

import com.apps.barclaysitunes.ui.TestAppComponent;
import com.apps.barclaysitunes.ui.TestAppModule;

public class TestItunesApp extends ItunesApp {

    private TestAppComponent testAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        testAppComponent = DaggerTestAppComponent.builder()
//                .testAppModule(new TestAppModule(this))
//                .build();
    }

    @Override
    public TestAppComponent appComponent() {
        return testAppComponent;
    }
}
