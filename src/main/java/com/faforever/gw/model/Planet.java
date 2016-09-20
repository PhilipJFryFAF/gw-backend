package com.faforever.gw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;

@JsonApiResource(type = "planets")
public class Planet {
    private String currentOwner;
    private Long fkMap;
    private String ground;
    private Boolean habitable = false;
    private Long id;
    private Long orbitLevel;
    private Long size;
    private SunSystem sunSystem;

    public Planet(Long id, Long fkSunSystem, Long fkMap, Long orbitLevel, Long size, Boolean habitable, String ground, String currentOwner) {
        this.currentOwner = currentOwner;
        this.fkMap = fkMap;
        this.sunSystem = new SunSystem(fkSunSystem);
        this.ground = ground;
        this.habitable = habitable;
        this.id = id;
        this.orbitLevel = orbitLevel;
        this.size = size;
    }

    /**
     * creates shallow instance (JSON reference only)
     *
     * @param id          Planet.id
     * @param fkSunSystem Planet.fk_sun_system
     */
    public Planet(Long id, Long fkSunSystem) {
        this.id = id;
        this.sunSystem = new SunSystem(fkSunSystem); //TODO: Is this required?
    }

    @JsonApiToOne
    public SunSystem getSunSystem() {
        return sunSystem;
    }

    public void setSunSystem(SunSystem sunSystem) {
        this.sunSystem = sunSystem;
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    @JsonIgnore
    public Long getFkMap() {
        return fkMap;
    }


    public String getGround() {
        return ground;
    }

    public Boolean getHabitable() {
        return habitable;
    }

    @JsonApiId
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
