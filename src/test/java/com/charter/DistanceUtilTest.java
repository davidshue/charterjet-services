package com.charter;

import com.charter.model.Plane;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

/**
 * Created by david on 3/21/17.
 */
public class DistanceUtilTest {
    @Test
    public void getDistance() throws Exception {
        Point from = new Point(4, 10);
        Point to = new Point(7, 14);
        int distance = DistanceUtil.getDistance(from, to);
        assertEquals(5, distance);
    }

    @Test
    public void getDuration() throws Exception {
        Plane plane = new Plane();
        plane.setSpeed(200);
        plane.setRange(500);
        int distance = 400;
        Duration expected = Duration.ofMinutes((long) 400 * 60 / 200);
        assertEquals(expected, DistanceUtil.getDuration(plane, distance));
    }

}