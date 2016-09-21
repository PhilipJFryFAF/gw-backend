package com.faforever.gw.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToMany;
import io.katharsis.resource.annotations.JsonApiToOne;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

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
    private List<Battle> battles;

    public Planet(Long id, Long fkSunSystem, Long fkMap, Long orbitLevel, Long size, Boolean habitable, String ground, String currentOwner) {
        this.currentOwner = currentOwner;
        this.fkMap = fkMap;
        this.sunSystem = new SunSystem(fkSunSystem);
        this.ground = ground;
        this.habitable = habitable;
        this.id = id;
        this.orbitLevel = orbitLevel;
        this.size = size;
        this.battles = new ArrayList<Battle>();
    }

    /**
     * creates shallow instance (JSON reference only)
     *
     * @param id          Planet.id
     * @param fkSunSystem Planet.fk_sun_system
     */
    public Planet(Long id, @Nullable Long fkSunSystem) {
        this.id = id;
        this.sunSystem = new SunSystem(fkSunSystem);
    }

    @JsonApiToMany
    public List<Battle> getBattles() {
        return battles;
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

    @JsonProperty("fk_map")
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
