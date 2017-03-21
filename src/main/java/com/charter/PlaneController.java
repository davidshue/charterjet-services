package com.charter;

import com.charter.model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

/**
 * Created by david on 3/21/17.
 */
@RequestMapping("/ws/v1/")
@RestController
public class PlaneController {
    private CharterService charterService;


    // bootstrap 2 planes (ice and maverick)
    @PostConstruct
    public void bootstrap() {
        Plane ice = new Plane();
        ice.setName("ice");
        ice.setSpeed(600);
        ice.setRange(1000);

        charterService.save(ice);

        Plane maverick = new Plane();
        maverick.setName("maverick");
        maverick.setSpeed(700);
        maverick.setRange(1500);

        charterService.save(maverick);
    }

    @Autowired
    public void setCharterService(CharterService charterService) {
        this.charterService = charterService;
    }

    @RequestMapping(value="/")
    public String home() {
        return "Welcome to XYZ Charter Jet Services";
    }

    @RequestMapping(value="/planes", method = RequestMethod.GET)
    public Object planes() {
        return charterService.getAllPlanes();
    }

    @RequestMapping(value="/planes/{name}", method = RequestMethod.GET)
    public Object plane(@PathVariable(name = "name") String name) {
        Plane plane = charterService.findByName(name);
        if (plane == null) {
            throw new EntityNotFoundException("Plane with name " + name + " is not in the system");
        }
        return plane;
    }

    @RequestMapping(value="/planes/{name}", method = RequestMethod.PUT)
    public Object addPlane(@PathVariable(name="name") String name, @RequestBody Plane plane) {
        plane.setName(name);
        return charterService.save(plane);
    }

    @RequestMapping(value="/planes/{name}", method=RequestMethod.POST)
    public String duration(@PathVariable(name="name") String name, @RequestBody Path path) {
        Plane plane = charterService.findByName(name);
        if (plane == null) {
            throw new EntityNotFoundException("Plane with name " + name + " is not in the system");
        }
        Duration duration = DistanceUtil.getDuration(plane,
                DistanceUtil.getDistance(path.getFrom(), path.getTo()));

        String s = String.format("%d hour(s) %02d minute(s)", duration.toHours(), duration.minusHours(duration.toHours()).toMinutes());

        return s;
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public String nfeHandler(EntityNotFoundException e, HttpServletResponse response){
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return e.getMessage();
    }
}
