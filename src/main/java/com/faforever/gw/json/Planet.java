package com.faforever.gw.json;

public class Planet {
    private String currentOwner = null;
    private String fkMap = null;
    private Long fkSunSystem = null;
    private String ground = null;
    private Boolean habitable = false;
    private Long id = null;
    private Long orbitLevel = null;
    private Integer size = null;

    public Planet(String currentOwner, String fkMap, Long fkSunSystem, String ground, Boolean habitable, Long id, Long orbitLevel, Integer size) {
        this.currentOwner = currentOwner;
        this.fkMap = fkMap;
        this.fkSunSystem = fkSunSystem;
        this.ground = ground;
        this.habitable = habitable;
        this.id = id;
        this.orbitLevel = orbitLevel;
        this.size = size;
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public String getFkMap() {
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

    public Integer getSize() {
        return size;
    }
}
