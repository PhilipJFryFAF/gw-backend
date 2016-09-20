package com.faforever.gw.repository;

import com.faforever.gw.model.Planet;
import com.google.common.collect.Sets;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindAll;
import io.katharsis.repository.annotations.JsonApiFindAllWithIds;
import io.katharsis.repository.annotations.JsonApiFindOne;
import io.katharsis.repository.annotations.JsonApiResourceRepository;
import io.katharsis.resource.exception.ResourceNotFoundException;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

import static com.faforever.gw.Tables.BATTLE;
import static com.faforever.gw.Tables.PLANET;

@JsonApiResourceRepository(Planet.class)
@Component
public class PlanetRepository {
    @Resource
    DSLContext dslContext;

    @Resource
    SunSystemRepository sunSystemRepository;

    @JsonApiFindOne
    public Planet findOne(Long Id, QueryParams requestParams) {
        Field<String> currentOwnerField = dslContext
                .select(BATTLE.WINNING_FACTION)
                .from(BATTLE)
                .where(BATTLE.FK_PLANET.eq(Id))
                .orderBy(BATTLE.ENDED_AT.desc())
                .limit(1)
                .asField("currentOwner");

        Planet planet = dslContext
                .select(PLANET.fields())
                .select(currentOwnerField)
                .from(PLANET)
                .where(PLANET.ID.eq(Id))
                .fetchOptionalInto(Planet.class)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Planet (id=%s) not found.", Id)));

//        SunSystem sunSystem = sunSystemRepository.findOne(planet.getFkSunSystem(), null);

        return planet;
    }

    @JsonApiFindAll
    public Iterable<Planet> findAll(QueryParams requestParams) {
        Iterable<Planet> planets = dslContext
                .selectFrom(PLANET)
                .fetchInto(Planet.class);

        return planets;
    }

    @JsonApiFindAllWithIds
    public Iterable<Planet> findAll(Iterable<Long> Ids, QueryParams requestParams) {
        Collection<Long> idCollection = Sets.newHashSet(Ids);

        Iterable<Planet> planets = dslContext
                .selectFrom(PLANET)
                .where(PLANET.ID.in(idCollection))
                .fetchInto(Planet.class);

        return planets;
    }
}
