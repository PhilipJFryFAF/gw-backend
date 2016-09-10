package com.faforever.gw.json;

import java.time.LocalDateTime;

public class Character {
    private Long currentCredits = null;
    private Long currentXp = null;
    private String faction = null;
    private Integer id = null;
    private LocalDateTime killedAt = null;
    private Character killedBy = null;
    private String name = null;
    private Integer rankId = null;
    private String rankTitle = null;

    public Character(Long currentCredits, Long currentXp, String faction, Integer id, LocalDateTime killedAt, Character killedBy, String name, Integer rankId, String rankTitle) {
        this.currentCredits = currentCredits;
        this.currentXp = currentXp;
        this.faction = faction;
        this.id = id;
        this.killedAt = killedAt;
        this.killedBy = killedBy;
        this.name = name;
        this.rankId = rankId;
        this.rankTitle = rankTitle;
    }

    public Long getCurrentCredits() {
        return currentCredits;
    }

    public Long getCurrentXp() {
        return currentXp;
    }

    public String getFaction() {
        return faction;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getKilledAt() {
        return killedAt;
    }

    public Character getKilledBy() {
        return killedBy;
    }

    public String getName() {
        return name;
    }

    public Integer getRankId() {
        return rankId;
    }

    public String getRankTitle() {
        return rankTitle;
    }
}
