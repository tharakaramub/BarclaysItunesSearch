package com.apps.barclaysitunes.tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.KeyEvent;

import com.apps.barclaysitunes.R;
import com.apps.barclaysitunes.TestItunesApp;
import com.apps.barclaysitunes.activity.HomeActivity;
import com.apps.barclaysitunes.model.Response;
import com.apps.barclaysitunes.service.ItunesAPI;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String SEARCH_TERM = "Jackson";
    private static final String ENTITY = "musicVideo";


    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Inject
    ItunesAPI itunesAPIClient;

    @Before
    public void setUp() {
        ((TestItunesApp) activityTestRule.getActivity().getApplication()).appComponent().inject(this);
    }

    @Test
    public void correctItunesDataDisplayed() {
        Response response = itunesAPIClient.getAllCountriesGeoNames(SEARCH_TERM, ENTITY).toBlocking().first();

        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(android.support.v7.appcompat.R.id.search_src_text)).perform(replaceText(SEARCH_TERM));
        onView(withId(android.support.v7.appcompat.R.id.search_src_text)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
    }
}
