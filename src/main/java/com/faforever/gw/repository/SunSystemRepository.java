package com.faforever.gw.repository;

import com.faforever.gw.model.SunSystem;
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
import java.util.List;

import static com.faforever.gw.Tables.SUN_SYSTEM;

@JsonApiResourceRepository(SunSystem.class)
@Component
public class SunSystemRepository {
    @Resource
    DSLContext dslContext;

    @Resource
    PlanetRepository planetRepository;

    @JsonApiFindOne
    public SunSystem findOne(Long Id, QueryParams requestParams) {
        SunSystem sunSystem = dslContext
                .selectFrom(SUN_SYSTEM)
                .where(SUN_SYSTEM.ID.eq(Id))
                .fetchOptionalInto(SunSystem.class)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("SunSystem (id=%s) not found.", Id)));

        sunSystem.getPlanets().addAll(planetRepository.findAllBySunSystemId(Id));

        return sunSystem;
    }

    @JsonApiFindAll
    public Iterable<SunSystem> findAll(QueryParams requestParams) {
        List<SunSystem> sunSystems = dslContext
                .selectFrom(SUN_SYSTEM)
                .fetchInto(SunSystem.class);

        return sunSystems;
    }

    @JsonApiFindAllWithIds
    public Iterable<SunSystem> findAll(Iterable<Long> Ids, QueryParams requestParams) {
        Collection<Long> idCollection = Sets.newHashSet(Ids);

        List<SunSystem> sunSystems = dslContext
                .selectFrom(SUN_SYSTEM)
                .where(SUN_SYSTEM.ID.in(idCollection))
                .fetchInto(SunSystem.class);

        return sunSystems;
    }
}
