package com.faforever.gw.json;

import com.faforever.gw.exceptions.EntityNotFoundException;
import com.faforever.gw.mapping.PlanetMapper;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Optional;

import static com.faforever.gw.Tables.PLANETS;

public class Planet {
    private String currentOwner = null;
    private Integer fkMap = null;
    private Integer fkSunSystem = null;
    private String ground = null;
    private Boolean habitable = false;
    private Integer id = null;
    private Integer orbitLevel = null;
    private Integer size = null;

    public Planet(String currentOwner, Integer fkMap, Integer fkSunSystem, String ground, Boolean habitable, Integer id, Integer orbitLevel, Integer size) {
        this.currentOwner = currentOwner;
        this.fkMap = fkMap;
        this.fkSunSystem = fkSunSystem;
        this.ground = ground;
        this.habitable = habitable;
        this.id = id;
        this.orbitLevel = orbitLevel;
        this.size = size;
    }

    public static Optional<Planet> selectById(DSLContext create, Integer id) {
            return create.selectFrom(PLANETS).where(PLANETS.ID.equal(id)).fetchOptional(new PlanetMapper());
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public Integer getFkMap() {
        return fkMap;
    }

    public Integer getFkSunSystem() {
        return fkSunSystem;
    }

    public String getGround() {
        return ground;
    }

    public Boolean getHabitable() {
        return habitable;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOrbitLevel() {
        return orbitLevel;
    }

    public Integer getSize() {
        return size;
    }
}
