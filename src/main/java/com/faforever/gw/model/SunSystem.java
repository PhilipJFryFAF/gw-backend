package com.faforever.gw.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiIncludeByDefault;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToMany;

import java.util.ArrayList;
import java.util.List;

@JsonApiResource(type = "sun_systems")
public class SunSystem {
    private List<SunSystem> connections = new ArrayList<>();
    @JsonApiId
    private Long id;
    private String name;
    private List<Planet> planets = new ArrayList<>();
    private Long x;
    private Long y;
    private Long z;

    /**
     * creates shallow instance (JSON reference only)
     *
     * @param id SunSystem.id
     */
    public SunSystem(Long id) {
        this.id = id;
    }

    public SunSystem(Long id, String name, Long x, Long y, Long z) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @JsonApiToMany
    public List<SunSystem> getConnections() {
        return connections;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonApiToMany
    @JsonApiIncludeByDefault
    public List<Planet> getPlanets() {
        return planets;
    }

    public Long getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }
}
