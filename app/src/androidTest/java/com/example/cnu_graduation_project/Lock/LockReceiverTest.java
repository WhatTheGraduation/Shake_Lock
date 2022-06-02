package com.example.cnu_graduation_project.Lock;

import static androidx.test.InstrumentationRegistry.getContext;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;

import com.example.cnu_graduation_project.TaskTag;

import junit.framework.TestCase;



public class LockReceiverTest extends TestCase {

    private LockReceiver mReceiver;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        mReceiver = new LockReceiver();
    }

    public void testLockReceive()
    {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(Intent.ACTION_SCREEN_OFF);
        mReceiver.onReceive(context, intent);
        assertFalse(TaskTag.WINDOW_ON);


        intent = new Intent(Intent.ACTION_SCREEN_ON);
        mReceiver.onReceive(context, intent);
        assertTrue(TaskTag.WINDOW_ON);
    }
}