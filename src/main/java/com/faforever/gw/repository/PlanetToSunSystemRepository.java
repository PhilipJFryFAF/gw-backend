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

@JsonApiRelationshipRepository(source = Planet.class, target = SunSystem.class)
@Component
public class PlanetToSunSystemRepository {

    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetToSunSystemRepository(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @JsonApiFindOneTarget
    public SunSystem findOneTarget(Long id, String fieldName, QueryParams requestParams) {
        Planet planet = planetRepository.findOne(id, requestParams);
        try {
            return (SunSystem) PropertyUtils.getProperty(planet, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindManyTargets
    public Iterable<SunSystem> findManyTargets(Long id, String fieldName, QueryParams requestParams) {
        Planet planet = planetRepository.findOne(id, requestParams);
        try {
            return (Iterable<SunSystem>) PropertyUtils.getProperty(planet, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
