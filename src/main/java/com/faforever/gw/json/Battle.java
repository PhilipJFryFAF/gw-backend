package com.faforever.gw.json;

import com.faforever.gw.exceptions.EntityNotFoundException;
import com.faforever.gw.mapping.BattleMapper;
import org.jooq.DSLContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.faforever.gw.Tables.BATTLES;

public class Battle {
    private String attackingFaction = null;
    private String defendingFaction = null;
    private Timestamp endedAt = null;
    private Integer id = null;
    private Timestamp initiatedAt = null;
    private Planet planet = null;
    private Timestamp startedAt = null;
    private String status = null;
    private String winningFaction = null;

    public Battle(String attackingFaction, String defendingFaction, Timestamp endedAt, Integer id, Timestamp initiatedAt, Planet planet, Timestamp startedAt, String status, String winningFaction) {
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

    public static Battle selectById(DSLContext create, Integer battleID)throws EntityNotFoundException {
        return create.selectFrom(BATTLES).where(BATTLES.ID.equal(battleID)).fetchOne(new BattleMapper(create));
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
