package com.faforever.gw.repository;

import com.faforever.gw.model.Battle;
import com.faforever.gw.model.Planet;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindManyTargets;
import io.katharsis.repository.annotations.JsonApiFindOneTarget;
import io.katharsis.repository.annotations.JsonApiRelationshipRepository;
import io.katharsis.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@JsonApiRelationshipRepository(source = Planet.class, target = Battle.class)
@Component
public class PlanetToBattleRepository {

    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetToBattleRepository(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @JsonApiFindOneTarget
    public Battle findOneTarget(Long id, String fieldName, QueryParams requestParams) {
        Planet planet = planetRepository.findOne(id, requestParams);
        try {
            return (Battle) PropertyUtils.getProperty(planet, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindManyTargets
    public Iterable<Battle> findManyTargets(Long id, String fieldName, QueryParams requestParams) {
        Planet planet = planetRepository.findOne(id, requestParams);
        try {
            return (Iterable<Battle>) PropertyUtils.getProperty(planet, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
