package com.faforever.gw.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToMany;
import io.katharsis.resource.annotations.JsonApiToOne;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private List<BattleParticipant> battleParticipants;

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
        this.battleParticipants = new ArrayList<>();
    }

    /**
     * creates shallow instance (JSON reference only)
     *
     * @param id
     * @param fkPlanet
     */
    public Battle(Long id, @Nullable Long fkPlanet) {
        this.id = id;
        this.planet = new Planet(fkPlanet, null);
    }

    @JsonApiToMany
    public List<BattleParticipant> getBattleParticipants() {
        return battleParticipants;
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
