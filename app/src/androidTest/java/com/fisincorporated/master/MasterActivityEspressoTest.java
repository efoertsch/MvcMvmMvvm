package com.fisincorporated.master;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MasterActivityEspressoTest {
    @Rule
    public ActivityTestRule<MasterActivity> mActivityRule = new ActivityTestRule(MasterActivity.class);

    @Test
    public void checkForMVCButton() {
        onView(withText("MVC")).check(matches(isDisplayed()));
    }

    @Test
    public void checkForMVPButton() {
        onView(withText("MVP")).check(matches(isDisplayed()));
    }
}
