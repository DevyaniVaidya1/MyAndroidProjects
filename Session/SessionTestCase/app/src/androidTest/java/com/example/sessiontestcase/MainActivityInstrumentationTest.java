package com.example.sessiontestcase;

import androidx.test.espresso.ViewAction;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityInstrumentationTest {
    // Preferred JUnit 4 mechanism of specifying the activity to be launched before each test
    @Rule
    //ActivityTestRule<launcheractivity>
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    // Looks for an EditText with id = "R.id.etInput"
    // Types the text "Hello" into the EditText
    // Verifies the EditText has text "Hello"
    @Test
    public void validateEditText() {
        //view matcher
        onView(withId(R.id.et_text_activitymain)).perform(typeText("Hello"));
        onView(withId(R.id.btn_submit)).perform(click());
        onView(withId(R.id.tv_diplaytext)).check(matches(withText("Hello")));
    }




}

