package com.faforever.gw.json;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Battle {
    private String attackingFaction = null;
    private String defendingFaction = null;
    private String endedAt = null;
    private Long id = null;
    private LocalDateTime initiatedAt = null;
    private List<BattleParticipant> participants = new ArrayList<BattleParticipant>();
    private Planet planet = null;
    private LocalDateTime startedAt = null;
    private String status = null;
    private String winningFaction = null;

    public Battle(String attackingFaction, String defendingFaction, String endedAt, Long id, LocalDateTime initiatedAt, List<BattleParticipant> participants, Planet planet, LocalDateTime startedAt, String status, String winningFaction) {
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

    public String getEndedAt() {
        return endedAt;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInitiatedAt() {
        return initiatedAt;
    }

    public List<BattleParticipant> getParticipants() {
        return participants;
    }

    public Planet getPlanet() {
        return planet;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public String getStatus() {
        return status;
    }

    public String getWinningFaction() {
        return winningFaction;
    }
}
