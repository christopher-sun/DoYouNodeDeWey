package com.example.mohammed.doyounodedewey;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class vacateJunit {
    //    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }
    private Shelter testShelter;
    @Before
    public void setUp() {
        testShelter = new Shelter("Name", "5", "3", "none", 1, 1, "address", "none", "phone");
    }

    @Test
    public void testVacate() {
        testShelter.vacate(2);
        assertEquals("5", testShelter.getCapacity());
    }
    @Test
    public void testVacateFalse() {
        testShelter.vacate(4);
        assertEquals("3", testShelter.getCapacity());
    }
}