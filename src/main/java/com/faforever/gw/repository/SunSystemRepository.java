package com.faforever.gw.repository;

import com.faforever.gw.model.Planet;
import com.faforever.gw.model.SunSystem;
import com.faforever.gw.tables.records.QuantumGateLinkRecord;
import com.google.common.collect.Sets;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindAll;
import io.katharsis.repository.annotations.JsonApiFindAllWithIds;
import io.katharsis.repository.annotations.JsonApiFindOne;
import io.katharsis.repository.annotations.JsonApiResourceRepository;
import io.katharsis.resource.exception.ResourceNotFoundException;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.faforever.gw.Tables.*;

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

        List<SunSystem> connections = dslContext
                .select(SUN_SYSTEM.fields())
                .from(SUN_SYSTEM)
                .join(QUANTUM_GATE_LINK)
                .on(SUN_SYSTEM.ID.eq(QUANTUM_GATE_LINK.FK_SUN_SYSTEM_TO))
                .where(QUANTUM_GATE_LINK.FK_SUN_SYSTEM_FROM.eq(Id))
                .fetchInto(SunSystem.class);

        List<Planet> indirect_planets = dslContext
                .select(PLANET.ID, PLANET.FK_SUN_SYSTEM)
                .from(PLANET)
                .join(QUANTUM_GATE_LINK)
                .on(PLANET.FK_SUN_SYSTEM.eq(QUANTUM_GATE_LINK.FK_SUN_SYSTEM_TO))
                .fetchInto(Planet.class);

        sunSystem.getConnections().addAll(connections);

        connections.stream()
                .forEach(c -> c.getPlanets().addAll(
                        indirect_planets.stream().filter(ip -> c.getId() == ip.getSunSystem().getId()).collect(Collectors.toList()))
                );

        return sunSystem;
    }

    @JsonApiFindAll
    public Iterable<SunSystem> findAll(QueryParams requestParams) {
        List<SunSystem> sunSystems = dslContext
                .selectFrom(SUN_SYSTEM)
                .fetchInto(SunSystem.class);

        List<Planet> planets = dslContext
                .select(PLANET.ID, PLANET.FK_SUN_SYSTEM)
                .from(PLANET)
                .fetchInto(Planet.class);

        Result<QuantumGateLinkRecord> quantumGateLinks = dslContext
                .selectFrom(QUANTUM_GATE_LINK)
                .fetch();

        sunSystems.stream()
                .forEach(s -> {
                    s.getPlanets().addAll(
                            planets.stream()
                                    .filter(p -> s.getId() == p.getSunSystem().getId())
                                    .collect(Collectors.toList()));
                    quantumGateLinks.stream()
                            .filter(q -> s.getId() == q.getFkSunSystemFrom())
                            .forEach(q -> s.getConnections().add(new SunSystem(q.getFkSunSystemTo())));
                });

        return sunSystems;
    }

    @JsonApiFindAllWithIds
    public Iterable<SunSystem> findAll(Iterable<Long> Ids, QueryParams requestParams) {
        Collection<Long> idCollection = Sets.newHashSet(Ids);

        List<SunSystem> sunSystems = dslContext
                .selectFrom(SUN_SYSTEM)
                .where(SUN_SYSTEM.ID.in(idCollection))
                .fetchInto(SunSystem.class);

        List<Planet> planets = dslContext
                .select(PLANET.ID)
                .from(PLANET)
                .where(PLANET.FK_SUN_SYSTEM.in(idCollection))
                .fetchInto(Planet.class);

        Result<QuantumGateLinkRecord> quantumGateLinks = dslContext
                .selectFrom(QUANTUM_GATE_LINK)
                .where(QUANTUM_GATE_LINK.FK_SUN_SYSTEM_FROM.in(idCollection))
                .fetch();


        sunSystems.stream()
                .forEach(s -> {
                    s.getPlanets().addAll(
                            planets.stream()
                                    .filter(p -> s.getId() == p.getSunSystem().getId())
                                    .collect(Collectors.toList()));
                    quantumGateLinks.stream()
                            .filter(q -> s.getId() == q.getFkSunSystemFrom())
                            .forEach(q -> s.getConnections().add(new SunSystem(q.getFkSunSystemTo())));
                });

        return sunSystems;
    }
}
