package com.faforever.gw.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;

import java.sql.Timestamp;

@JsonApiResource(type = "battles")
public class Battle {
    private String attackingFaction;
    private String defendingFaction;
    private Timestamp endedAt;
    private Long id;
    private Timestamp initiatedAt;
    private Planet planet;
    private Timestamp startedAt;
    private String status;
    private String winningFaction;

    public Battle(Long id, Long fkPlanet, String status, Timestamp initiatedAt, Timestamp startedAt, Timestamp endedAt, String attackingFaction, String defendingFaction, String winningFaction) {
        this.attackingFaction = attackingFaction;
        this.defendingFaction = defendingFaction;
        this.endedAt = endedAt;
        this.id = id;
        this.initiatedAt = initiatedAt;
        this.planet = new Planet(fkPlanet, null);
        this.startedAt = startedAt;
        this.status = status;
        this.winningFaction = winningFaction;
    }

    public String getAttackingFaction() {
        return attackingFaction;
    }

    public String getDefendingFaction() {
        return defendingFaction;
    }

    public Timestamp getEndedAt() {
        return endedAt;
    }

    @JsonApiId
    public Long getId() {
        return id;
    }

    public Timestamp getInitiatedAt() {
        return initiatedAt;
    }

    @JsonApiToOne
    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public String getStatus() {
        return status;
    }

    public String getWinningFaction() {
        return winningFaction;
    }
}
