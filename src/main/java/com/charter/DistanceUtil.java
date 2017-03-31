package com.charter;

import com.charter.model.Plane;

import java.time.Duration;

/**
 * Created by david on 3/21/17.
 */
public class DistanceUtil {
    private DistanceUtil() {}

    public static int getDistance(Point from, Point to) {
        return (int)Math.sqrt((to.getY() - from.getY()) * (to.getY() - from.getY()) +
                (to.getX() - from.getX()) * (to.getX() - from.getX())
        );
    }

    public static Duration getDuration(Plane plane, int distance) {
        int intervalMiutes = _getDuration(plane, 0, distance);
        return Duration.ofMinutes((long) intervalMiutes);
    }

    // duration in miutes
    private static int _getDuration(Plane plane, int offset, int distance) {
        if (plane.getRange() >= distance) {
            return offset + distance * 60 / plane.getSpeed();
        }
        offset += (30 + plane.getRange() * 60 / plane.getSpeed());
        return _getDuration(plane, offset, distance - plane.getRange());
    }
}
