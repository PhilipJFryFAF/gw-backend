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

@JsonApiRelationshipRepository(source = Battle.class, target = Planet.class)
@Component
public class BattleToPlanetRepository {

    private final BattleRepository battleRepository;

    @Autowired
    public BattleToPlanetRepository(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    @JsonApiFindOneTarget
    public Planet findOneTarget(Long id, String fieldName, QueryParams requestParams) {
        Battle planet = battleRepository.findOne(id, requestParams);
        try {
            return (Planet) PropertyUtils.getProperty(planet, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindManyTargets
    public Iterable<Planet> findManyTargets(Long id, String fieldName, QueryParams requestParams) {
        Battle battle = battleRepository.findOne(id, requestParams);
        try {
            return (Iterable<Planet>) PropertyUtils.getProperty(battle, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
