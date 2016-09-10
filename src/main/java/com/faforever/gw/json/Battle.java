package com.faforever.gw.json;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Battle {
    private String attackingFaction = null;
    private String defendingFaction = null;
    private Timestamp endedAt = null;
    private Integer id = null;
    private Timestamp initiatedAt = null;
    private List<BattleParticipant> participants = new ArrayList<BattleParticipant>();
    private Planet planet = null;
    private Timestamp startedAt = null;
    private String status = null;
    private String winningFaction = null;

    public Battle(String attackingFaction, String defendingFaction, Timestamp endedAt, Integer id, Timestamp initiatedAt, List<BattleParticipant> participants, Planet planet, Timestamp startedAt, String status, String winningFaction) {
        this.attackingFaction = attackingFaction;
        this.defendingFaction = defendingFaction;
        this.endedAt = endedAt;
        this.id = id;
        this.initiatedAt = initiatedAt;
        this.participants = participants;
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

    public Integer getId() {
        return id;
    }

    public Timestamp getInitiatedAt() {
        return initiatedAt;
    }

    public List<BattleParticipant> getParticipants() {
        return participants;
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
