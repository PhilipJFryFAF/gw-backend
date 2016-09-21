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

@JsonApiRelationshipRepository(source = BattleParticipant.class, target = Battle.class)
@Component
public class BattleParticipantToBattleRepository {

    private final BattleParticipantRepository battleParticipantRepository;

    @Autowired
    public BattleParticipantToBattleRepository(BattleParticipantRepository battleParticipantRepository) {
        this.battleParticipantRepository = battleParticipantRepository;
    }

    @JsonApiFindOneTarget
    public Battle findOneTarget(Long id, String fieldName, QueryParams requestParams) {
        BattleParticipant battleParticipant = battleParticipantRepository.findOne(id, requestParams);
        try {
            return (Battle) PropertyUtils.getProperty(battleParticipant, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindManyTargets
    public Iterable<Battle> findManyTargets(Long id, String fieldName, QueryParams requestParams) {
        BattleParticipant battleParticipant = battleParticipantRepository.findOne(id, requestParams);
        try {
            return (Iterable<Battle>) PropertyUtils.getProperty(battleParticipant, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
