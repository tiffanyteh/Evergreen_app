package com.example.myapplication;

import android.widget.EditText;
import android.widget.TextView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.espresso.Espresso;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

// @RunWith is required only if you use a mix of JUnit3 and JUnit4.
@RunWith(AndroidJUnit4.class)
@SmallTest
public class HomeActivityTest extends ActivityTestRule<home> {


    public HomeActivityTest(){
        super(home.class);
    }


    @Rule
    public ActivityTestRule<home> rule =
            new ActivityTestRule<>(home.class, false, true);


    @Test
    public void search_click() {
        String search = "acacia";
        onView(withId(R.id.searchplant)).perform(typeText(search), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.searchicon)).perform(click());

    }

    @Test
    public void navigation_click() {
        onView(withId(R.id.imageView4)).perform(click());
        onView(withId(R.id.imageView5)).perform(click());
        onView(withId(R.id.camera)).perform(click());

    }
}
