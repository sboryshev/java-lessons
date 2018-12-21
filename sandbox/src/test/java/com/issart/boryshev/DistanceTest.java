package com.issart.boryshev;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {

    @Test
    public void testAreaPositive() {

        Point p1 = new Point(4, 0);
        Point p2 = new Point(9, 0);

        double distance = p2.distance(p1);
        Assert.assertEquals(distance, 5.0);

        Point p3 = new Point(-9, 0);
        Point p4 = new Point(0,  5);

        double distance1 = p4.distance(p3);
        Assert.assertEquals(distance1, 10.295630140987, "Distance is wrong");
    }
}
