package com.example.cnu_graduation_project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.gms.location.ActivityTransition;
import com.google.android.gms.location.DetectedActivity;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DrivingRecognitionActivityTest {
    @Rule
    public ActivityScenarioRule<DrivingRecognitionActivity> activityRule =
            new ActivityScenarioRule(DrivingRecognitionActivity.class);
    @Test
    public void toActivityStringTest() {
        final String[] str = new String[4];
        activityRule.getScenario().onActivity(activity -> {
            Method method = null;

            try {
                method = activity.getClass().getDeclaredMethod("toActivityString", int.class);
                method.setAccessible(true);
                str[0] = (String) method.invoke(activity, DetectedActivity.IN_VEHICLE);
                assertTrue(TaskTag.ACTIVITY_TAG);
                str[1] = (String) method.invoke(activity, DetectedActivity.WALKING);
                assertTrue(TaskTag.ACTIVITY_TAG);
                str[2] = (String) method.invoke(activity, DetectedActivity.STILL);
                assertFalse(TaskTag.ACTIVITY_TAG);
                str[3] = (String) method.invoke(activity, DetectedActivity.UNKNOWN);
                assertFalse(TaskTag.ACTIVITY_TAG);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        assertEquals("IN_VEHICLE", str[0]);
        assertEquals("WALKING", str[1]);
        assertEquals("STILL", str[2]);
        assertEquals("UNKNOWN", str[3]);
    }

    @Test
    public void toTransitionTypeTest(){
        final String[] str= new String[2];
        activityRule.getScenario().onActivity(activity -> {
            Method method = null;
            try {
                method = activity.getClass().getDeclaredMethod("toTransitionType", int.class);
                method.setAccessible(true);
                str[0] = (String) method.invoke(activity, ActivityTransition.ACTIVITY_TRANSITION_ENTER);
                str[1] = (String) method.invoke(activity, ActivityTransition.ACTIVITY_TRANSITION_EXIT);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        assertEquals("ENTER", str[0]);
        assertTrue(TaskTag.ACTIVITY_TAG);
        assertEquals("EXIT", str[1]);
        assertFalse(TaskTag.ACTIVITY_TAG);
    }

    @Test
    public void enableActivityTransitionsTest(){
        activityRule.getScenario().moveToState(Lifecycle.State.CREATED);
        activityRule.getScenario().onActivity(activity -> {
            Field field = null;
            try {
                field = activity.getClass().getDeclaredField("activityTrackingEnabled");
                field.setAccessible(true);
                boolean value = (boolean)field.get(activity);
                assertTrue(value);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
    }
}