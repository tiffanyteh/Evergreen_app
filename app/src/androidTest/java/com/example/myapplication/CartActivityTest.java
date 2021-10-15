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
public class CartActivityTest extends ActivityTestRule<Cart> {


    public CartActivityTest(){
        super(Cart.class);
    }


    @Rule
    public ActivityTestRule<Cart> rule =
            new ActivityTestRule<>(Cart.class, false, true);


    @Test
    public void test_click() {
        onView(withId(R.id.btn_place_order)).perform(click());

    }

}

