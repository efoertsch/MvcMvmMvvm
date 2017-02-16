package com.fisincorporated.master;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.fisincorporated.mvc_mvp_mvvm.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MvpActivityTest {

    @Rule
    public ActivityTestRule<MasterActivity> mActivityTestRule = new ActivityTestRule<>(MasterActivity.class);

    @Test
    public void mvpActivityTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.activity_master_mvp_button), withText("MVP"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction switch_ = onView(
                allOf(withId(R.id.list_item_switch), isDisplayed()));
        switch_.perform(click());

        ViewInteraction switch_2 = onView(
                allOf(withId(R.id.list_item_switch), isDisplayed()));
        switch_2.perform(click());

        ViewInteraction switch_3 = onView(
                allOf(withId(R.id.list_item_switch), isDisplayed()));
        switch_3.perform(click());

        ViewInteraction switch_4 = onView(
                allOf(withId(R.id.list_item_switch),
                        childAtPosition(
                                childAtPosition(withId(R.id.activity_station_switches_recyclerview), 3),
                                1),
                        isDisplayed()));
        switch_4.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
