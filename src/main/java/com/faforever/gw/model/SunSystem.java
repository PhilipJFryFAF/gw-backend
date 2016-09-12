package com.faforever.gw.model;

import java.util.ArrayList;
import java.util.List;

public class SunSystem {
    private List<Long> connections = new ArrayList<Long>();
    private Long id = null;
    private String name = null;
    private List<Planet> planets = new ArrayList<Planet>();
    private Long x = null;
    private Long y = null;
    private Long z = null;

    public SunSystem(List<Long> connections, Long id, String name, List<Planet> planets, Long x, Long y, Long z) {
        this.connections = connections;
        this.id = id;
        this.name = name;
        this.planets = planets;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public List<Long> getConnections() {
        return connections;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

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
