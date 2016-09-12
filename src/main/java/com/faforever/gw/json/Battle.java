package com.faforever.gw.json;

import com.faforever.gw.exceptions.EntityNotFoundException;
import com.faforever.gw.mapping.BattleMapper;
import org.jooq.DSLContext;

import java.sql.Timestamp;
import java.util.Optional;

import static com.faforever.gw.Tables.BATTLES;

public class Battle {
    private String attackingFaction;
    private String defendingFaction;
    private Timestamp endedAt;
    private Integer id;
    private Timestamp initiatedAt;
    private Planet planet;
    private Timestamp startedAt;
    private String status;
    private String winningFaction;

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

    public static Optional<Battle> selectById(DSLContext create, Integer battleID) {
        return create.selectFrom(BATTLES).where(BATTLES.ID.equal(battleID)).fetchOptional(new BattleMapper());
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
