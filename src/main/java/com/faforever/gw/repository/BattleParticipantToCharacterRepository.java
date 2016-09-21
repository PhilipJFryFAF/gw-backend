package com.faforever.gw.repository;

import com.faforever.gw.model.BattleParticipant;
import com.faforever.gw.model.Character;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindManyTargets;
import io.katharsis.repository.annotations.JsonApiFindOneTarget;
import io.katharsis.repository.annotations.JsonApiRelationshipRepository;
import io.katharsis.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@JsonApiRelationshipRepository(source = BattleParticipant.class, target = Character.class)
@Component
public class BattleParticipantToCharacterRepository {

    private final BattleParticipantRepository battleParticipantRepository;

    @Autowired
    public BattleParticipantToCharacterRepository(BattleParticipantRepository battleParticipantRepository) {
        this.battleParticipantRepository = battleParticipantRepository;
    }

    @JsonApiFindOneTarget
    public Character findOneTarget(Long id, String fieldName, QueryParams requestParams) {
        BattleParticipant battleParticipant = battleParticipantRepository.findOne(id, requestParams);
        try {
            return (Character) PropertyUtils.getProperty(battleParticipant, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindManyTargets
    public Iterable<Character> findManyTargets(Long id, String fieldName, QueryParams requestParams) {
        BattleParticipant battleParticipant = battleParticipantRepository.findOne(id, requestParams);
        try {
            return (Iterable<Character>) PropertyUtils.getProperty(battleParticipant, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
