package com.faforever.gw.model;

import java.sql.Timestamp;

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

    public Battle(String attackingFaction, String defendingFaction, Timestamp endedAt, Long id, Timestamp initiatedAt, Planet planet, Timestamp startedAt, String status, String winningFaction) {
        this.attackingFaction = attackingFaction;
        this.defendingFaction = defendingFaction;
        this.endedAt = endedAt;
        this.id = id;
        this.initiatedAt = initiatedAt;
        this.planet = planet;
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

    public Long getId() {
        return id;
    }

    public Timestamp getInitiatedAt() {
        return initiatedAt;
    }

    public Planet getPlanet() {
        return planet;
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
