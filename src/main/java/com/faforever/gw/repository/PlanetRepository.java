package com.faforever.gw.repository;

import com.faforever.gw.model.Battle;
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

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;

import static com.faforever.gw.Tables.BATTLE;
import static com.faforever.gw.Tables.PLANET;

@JsonApiResourceRepository(Planet.class)
@Component
public class PlanetRepository {
    @Resource
    private DSLContext dslContext;
    @Resource
    private SunSystemRepository sunSystemRepository;

    private Field<String> currentOwnerField;

    @PostConstruct
    public void postConstruct() {
        currentOwnerField = dslContext
                .select(BATTLE.WINNING_FACTION)
                .from(BATTLE)
                .where(BATTLE.FK_PLANET.eq(PLANET.ID))
                .orderBy(BATTLE.ENDED_AT.desc())
                .limit(1)
                .asField("currentOwner");
    }

    @JsonApiFindOne
    public Planet findOne(Long Id, QueryParams requestParams) {
        Planet planet = dslContext
                .select(PLANET.fields())
                .select(currentOwnerField)
                .from(PLANET)
                .where(PLANET.ID.eq(Id))
                .fetchOptionalInto(Planet.class)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Planet (id=%s) not found.", Id)));

        planet.setSunSystem(sunSystemRepository.findOne(planet.getSunSystem().getId(), null));

        planet.getBattles().addAll(dslContext
                .selectFrom(BATTLE)
                .where(BATTLE.FK_PLANET.eq(Id))
                .fetchInto(Battle.class));

        return planet;
    }

    @JsonApiFindAll
    public Iterable<Planet> findAll(QueryParams requestParams) {
        Iterable<Planet> planets = dslContext
                .select(PLANET.fields())
                .select(currentOwnerField)
                .from(PLANET)
                .fetchInto(Planet.class);

        return planets;
    }

    @JsonApiFindAllWithIds
    public Iterable<Planet> findAll(Iterable<Long> Ids, QueryParams requestParams) {
        Collection<Long> idCollection = Sets.newHashSet(Ids);

        Iterable<Planet> planets = dslContext
                .select(PLANET.fields())
                .select(currentOwnerField)
                .from(PLANET)
                .where(PLANET.ID.in(idCollection))
                .fetchInto(Planet.class);

        return planets;
    }

    public Collection<Planet> findAllBySunSystemId(Long Id) {
        Collection<Planet> planets = dslContext
                .select(PLANET.fields())
                .select(currentOwnerField)
                .from(PLANET)
                .where(PLANET.FK_SUN_SYSTEM.eq(Id))
                .fetchInto(Planet.class);

        return planets;
    }
}
