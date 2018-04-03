package com.parker.svgpractice;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
<<<<<<< HEAD
    public void useAppContext() throws Exception {
=======
    public void useAppContext() {
>>>>>>> bef403b2ff7a7d853d80273458d802b5699a3623
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.parker.svgpractice", appContext.getPackageName());
    }
}
