package com.charter.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by david on 3/21/17.
 */
public interface PlaneRepository extends CrudRepository<Plane, Integer> {
    Plane findByName(String name);
}
