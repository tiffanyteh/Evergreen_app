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
public class CategoryActivityTest extends ActivityTestRule<category> {


    public CategoryActivityTest(){
        super(category.class);
    }


    @Rule
    public ActivityTestRule<category> rule =
            new ActivityTestRule<>(category.class, false, true);



    @Test
    public void navigation_click() {
        onView(withId(R.id.backpress)).perform(click());

    }
}
