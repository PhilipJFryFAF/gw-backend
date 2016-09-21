package com.faforever.gw.repository;

import com.faforever.gw.model.Battle;
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

import static com.faforever.gw.Tables.BATTLE;

@JsonApiResourceRepository(Battle.class)
@Component
public class BattleRepository {
    @Resource
    DSLContext dslContext;

    @Resource
    PlanetRepository planetRepository;

    @JsonApiFindOne
    public Battle findOne(Long Id, QueryParams requestParams) {
        Battle battle = dslContext
                .selectFrom(BATTLE)
                .where(BATTLE.ID.eq(Id))
                .fetchOptionalInto(Battle.class)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Battle (id=%s) not found.", Id)));

        battle.setPlanet(planetRepository.findOne(battle.getPlanet().getId(), null));

        return battle;
    }

    @JsonApiFindAll
    public Iterable<Battle> findAll(QueryParams requestParams) {
        return dslContext
                .selectFrom(BATTLE)
                .fetchInto(Battle.class);
    }

    @JsonApiFindAllWithIds
    public Iterable<Battle> findAll(Iterable<Long> Ids, QueryParams requestParams) {
        Collection<Long> idCollection = Sets.newHashSet(Ids);

        return dslContext
                .selectFrom(BATTLE)
                .where(BATTLE.ID.in(idCollection))
                .fetchInto(Battle.class);
    }
}
