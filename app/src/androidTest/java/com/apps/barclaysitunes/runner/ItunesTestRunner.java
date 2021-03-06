package com.apps.barclaysitunes.runner;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.apps.barclaysitunes.TestItunesApp;

public class ItunesTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        String testApplicationClassName = TestItunesApp.class.getCanonicalName();
        return super.newApplication(cl, testApplicationClassName, context);
    }
}
