package com.example.cnu_graduation_project;

import static org.junit.Assert.assertEquals;

import android.util.Log;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.gms.location.DetectedActivity;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DrivingRecognitionActivityTest {
//    @Rule
    public ActivityScenarioRule rule;
    @Before
    public void setUp(){
        rule = new ActivityScenarioRule<>(DrivingRecognitionActivity.class);

    }
    @Rule
    public ActivityScenarioRule<DrivingRecognitionActivity> mActivityRule =
            new ActivityScenarioRule(DrivingRecognitionActivity.class);
    @Test
    public void myTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        int expected = 3;

        Method method = DrivingRecognitionActivity.class.getDeclaredMethod("toActivityString", int.class);
        method.setAccessible(true);
        int actual = (int) method.invoke(mActivityRule.getScenario(), DetectedActivity.IN_VEHICLE);

        assertEquals(expected, actual);
    }
}