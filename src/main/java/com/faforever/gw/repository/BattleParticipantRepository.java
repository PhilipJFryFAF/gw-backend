package com.faforever.gw.repository;

import com.faforever.gw.model.BattleParticipant;
import com.google.common.collect.Sets;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindAll;
import io.katharsis.repository.annotations.JsonApiFindAllWithIds;
import io.katharsis.repository.annotations.JsonApiFindOne;
import io.katharsis.repository.annotations.JsonApiResourceRepository;
import io.katharsis.resource.exception.ResourceNotFoundException;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

import static com.faforever.gw.Tables.BATTLE_PARTICIPANT;

@JsonApiResourceRepository(BattleParticipant.class)
@Component
public class BattleParticipantRepository {
    @Resource
    DSLContext dslContext;

    @Resource
    BattleRepository battleRepository;

    @Resource
    CharacterRepository characterRepository;

    @JsonApiFindOne
    public BattleParticipant findOne(Long Id, QueryParams requestParams) {
        BattleParticipant battleParticipant = dslContext
                .selectFrom(BATTLE_PARTICIPANT)
                .where(BATTLE_PARTICIPANT.ID.eq(Id))
                .fetchOptionalInto(BattleParticipant.class)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("BattleParticipant (id=%s) not found.", Id)));

        battleParticipant.setBattle(battleRepository.findOne(battleParticipant.getBattle().getId(), null));
        battleParticipant.setCharacter(characterRepository.findOne(battleParticipant.getCharacter().getId(), null));

        return battleParticipant;
    }

    @JsonApiFindAll
    public Iterable<BattleParticipant> findAll(QueryParams requestParams) {
        return dslContext
                .selectFrom(BATTLE_PARTICIPANT)
                .fetchInto(BattleParticipant.class);
    }

    @JsonApiFindAllWithIds
    public Iterable<BattleParticipant> findAll(Iterable<Long> Ids, QueryParams requestParams) {
        Collection<Long> idCollection = Sets.newHashSet(Ids);

        return dslContext
                .selectFrom(BATTLE_PARTICIPANT)
                .where(BATTLE_PARTICIPANT.ID.in(idCollection))
                .fetchInto(BattleParticipant.class);
    }

    public Collection<BattleParticipant> findAllByBattleId(Long Id) {
        return dslContext
                .selectFrom(BATTLE_PARTICIPANT)
                .where(BATTLE_PARTICIPANT.FK_BATTLE.eq(Id))
                .fetchInto(BattleParticipant.class);
    }
}
