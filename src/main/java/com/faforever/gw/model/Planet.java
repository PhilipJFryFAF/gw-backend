package com.faforever.gw.model;

import com.faforever.gw.mapping.PlanetMapper;
import org.jooq.DSLContext;

import java.util.Optional;

import static com.faforever.gw.Tables.PLANET;

public class Planet {
    private String currentOwner = null;
    private Long fkMap = null;
    private Long fkSunSystem = null;
    private String ground = null;
    private Boolean habitable = false;
    private Long id = null;
    private Long orbitLevel = null;
    private Long size = null;

    public Planet(String currentOwner, Long fkMap, Long fkSunSystem, String ground, Boolean habitable, Long id, Long orbitLevel, Long size) {
        this.currentOwner = currentOwner;
        this.fkMap = fkMap;
        this.fkSunSystem = fkSunSystem;
        this.ground = ground;
        this.habitable = habitable;
        this.id = id;
        this.orbitLevel = orbitLevel;
        this.size = size;
    }

    // creates shallow instance (JSON reference only)
    public Planet(Long id, Long fkSunSystem) {
        this.id = id;
        this.fkSunSystem = fkSunSystem;
    }

    public static Optional<Planet> selectById(DSLContext create, Long id) {
        return create.selectFrom(PLANET).where(PLANET.ID.equal(id)).fetchOptional(new PlanetMapper());
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public Long getFkMap() {
        return fkMap;
    }

    public Long getFkSunSystem() {
        return fkSunSystem;
    }

    public String getGround() {
        return ground;
    }

    public Boolean getHabitable() {
        return habitable;
    }

    public Long getId() {
        return id;
    }

    public Long getOrbitLevel() {
        return orbitLevel;
    }

    public Long getSize() {
        return size;
    }
}
