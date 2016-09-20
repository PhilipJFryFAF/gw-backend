package com.faforever.gw.repository;

import com.faforever.gw.model.Planet;
import com.faforever.gw.model.SunSystem;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindManyTargets;
import io.katharsis.repository.annotations.JsonApiFindOneTarget;
import io.katharsis.repository.annotations.JsonApiRelationshipRepository;
import io.katharsis.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@JsonApiRelationshipRepository(source = SunSystem.class, target = Planet.class)
@Component
public class SunSystemToPlanetRepository {

    private final SunSystemRepository sunSystemRepository;

    @Autowired
    public SunSystemToPlanetRepository(SunSystemRepository sunSystemRepository) {
        this.sunSystemRepository = sunSystemRepository;
    }

    @JsonApiFindOneTarget
    public Planet findOneTarget(Long id, String fieldName, QueryParams requestParams) {
        SunSystem sunSystem = sunSystemRepository.findOne(id, requestParams);
        try {
            return (Planet) PropertyUtils.getProperty(sunSystem, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindManyTargets
    public Iterable<Planet> findManyTargets(Long id, String fieldName, QueryParams requestParams) {
        SunSystem sunSystem = sunSystemRepository.findOne(id, requestParams);
        try {
            return (Iterable<Planet>) PropertyUtils.getProperty(sunSystem, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
