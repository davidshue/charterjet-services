package com.charter;

import com.charter.model.Plane;
import com.charter.model.PlaneRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

/**
 * Created by david on 3/21/17.
 */
@Service
public class CharterService {
    private PlaneRepository planeRepository;

    @Autowired
    public void setPlaneRepository(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public Plane findByName(String name) {
        return planeRepository.findByName(name);
    }

    public List<Plane> getAllPlanes() {
        return Lists.newArrayList(planeRepository.findAll());
    }

    public Plane save(Plane plane) {
        Plane p = findByName(plane.getName());
        if (p == null) {
            p = new Plane();
            p.setName(plane.getName());
        }
        p.setSpeed(plane.getSpeed());
        p.setRange(plane.getRange());
        return planeRepository.save(p);
    }

    public Duration getFlightTime(String name, Point from, Point to) {
        int distance = DistanceUtil.getDistance(from, to);
        Plane plane = findByName(name);
        return DistanceUtil.getDuration(plane, distance);
    }
}
