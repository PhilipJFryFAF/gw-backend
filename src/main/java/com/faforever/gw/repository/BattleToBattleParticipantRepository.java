package com.faforever.gw.repository;

import com.faforever.gw.model.Battle;
import com.faforever.gw.model.BattleParticipant;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindManyTargets;
import io.katharsis.repository.annotations.JsonApiFindOneTarget;
import io.katharsis.repository.annotations.JsonApiRelationshipRepository;
import io.katharsis.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@JsonApiRelationshipRepository(source = Battle.class, target = BattleParticipant.class)
@Component
public class BattleToBattleParticipantRepository {

    private final BattleRepository battleRepository;

    @Autowired
    public BattleToBattleParticipantRepository(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    @JsonApiFindOneTarget
    public BattleParticipant findOneTarget(Long id, String fieldName, QueryParams requestParams) {
        Battle battle = battleRepository.findOne(id, requestParams);
        try {
            return (BattleParticipant) PropertyUtils.getProperty(battle, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindManyTargets
    public Iterable<BattleParticipant> findManyTargets(Long id, String fieldName, QueryParams requestParams) {
        Battle battle = battleRepository.findOne(id, requestParams);
        try {
            return (Iterable<BattleParticipant>) PropertyUtils.getProperty(battle, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
