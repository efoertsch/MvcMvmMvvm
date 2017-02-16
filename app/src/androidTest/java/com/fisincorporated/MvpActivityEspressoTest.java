package com.fisincorporated;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fisincorporated.mvc_mvp_mvvm.R;
import com.fisincorporated.mvp.MvpActivity;
import com.fisincorporated.utility.RecyclerViewMatcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MvpActivityEspressoTest {
    @Rule
    public ActivityTestRule<MvpActivity> mActivityRule = new ActivityTestRule(MvpActivity.class);

    @Test
    public void checkForTitle() {
        onView(withText("Engineering")).check(matches(isDisplayed()));
    }

    @Test
    public void checkForDockingClampsText() {
        // works if row is on screen. Need more complicated check if need to scroll
        onView(withId(R.id.activity_station_switches_recyclerview)).check(matches(hasDescendant(withText("Docking Clamps Released"))));
    }

    @Test
    public void turnOnWeaponSystems(){
        // Get row 3 of recycler view
        ViewInteraction viewInteraction = getStationControl(3);
        // Check that row has field with text "Weapon Systems Online"
        viewInteraction.check(matches(hasDescendant(withText("Weapon Systems Online"))));
        // Check that row has field with a switch
        viewInteraction.check(matches(hasDescendant(withId(R.id.list_item_switch))));
        // Check that row has switch not set
        onView(withRecyclerView(R.id.activity_station_switches_recyclerview).atPositionOnView(3,R.id.list_item_switch)).check(matches(isNotChecked()));
        // Click the switch on row 3
        onView(withRecyclerView(R.id.activity_station_switches_recyclerview).atPositionOnView(3,R.id.list_item_switch)).perform(click());
        // Check that switch is set on row 3
        onView(withRecyclerView(R.id.activity_station_switches_recyclerview).atPositionOnView(3,R.id.list_item_switch)).check(matches(isChecked()));

        // Could have set switch and check immediately, rather than having 2 statement to click then check using
        //onView(withRecyclerView(R.id.activity_station_switches_recyclerview).atPositionOnView(3,R.id.list_item_switch)).perform(click()).check(matches(isChecked()));

    }

    public ViewInteraction getStationControl(int position) {
        return onView(withRecyclerView(R.id.activity_station_switches_recyclerview).atPosition(position));
    }

    // Convenience helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }



}
