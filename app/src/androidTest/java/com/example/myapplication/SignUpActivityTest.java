package com.example.myapplication;

import android.widget.EditText;

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
public class SignUpActivityTest extends ActivityTestRule<signup> {

    signup msignup;

    public SignUpActivityTest(){
        super(signup.class);
    }


    @Rule
    public ActivityTestRule<signup> rule =
            new ActivityTestRule<>(signup.class, false, true);


    @Test
    public void clickSignUpButtonAfterFillingForm() {
        String name = "tiffany";
        String email = "tiffanyteh00@gmail.com";
        String password = "tiffany";
        onView(withId(R.id.username1)).perform(typeText(name), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.email1)).perform(typeText(email), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password1)).perform(typeText(password), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signupbtn1)).perform(click());

    }
}