package com.faforever.gw.json;

import com.faforever.gw.exceptions.EntityNotFoundException;
import com.faforever.gw.tables.records.RanksRecord;
import org.jooq.DSLContext;
import org.jooq.Field;

import javax.persistence.Column;
import java.sql.Timestamp;

import static com.faforever.gw.Tables.*;
import static org.jooq.impl.DSL.sum;

public class Character {
    private Long currentCredits = null;
    private Long currentXp = null;
    private String faction = null;
    private Integer id = null;
    private Timestamp killedAt = null;
    private Character killedBy = null;
    private String name = null;
    private Integer rankId = null;
    private String rankTitle = null;

    public Long getCurrentCredits() {
        return (currentCredits == null) ? 0 : currentCredits;
    }

    @Column(name = "CREDITS")
    public void setCurrentCredits(Long currentCredits) {
        this.currentCredits = currentCredits;
    }

    public Long getCurrentXp() {
        return (currentXp == null) ? 0 : currentXp;
    }

    @Column(name = "XP")
    public void setCurrentXp(Long currentXp) {
        this.currentXp = currentXp;
    }

    public String getFaction() {
        return faction;
    }

    @Column(name = "FACTION")
    public void setFaction(String faction) {
        this.faction = faction;
    }

    public Integer getId() {
        return id;
    }

    @Column(name = "ID")
    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getKilledAt() {
        return killedAt;
    }

    @Column(name = "KILLED_AT")
    public void setKilledAt(Timestamp killedAt) {
        this.killedAt = killedAt;
    }

    public Character getKilledBy() {
        return killedBy;
    }

    @Column(name = "KILLED_BY")
    public void setKilledBy(Character killedBy) {
        this.killedBy = killedBy;
    }

    public String getName() {
        return name;
    }

    @Column(name = "NAME")
    public void setName(String name) {
        this.name = name;
    }

    public Integer getRankId() {
        return rankId;
    }


    @Column(name = "RANK_ID")
    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public String getRankTitle() {
        return rankTitle;
    }

    @Column(name = "RANK_TITLE")
    public void setRankTitle(String rankTitle) {
        this.rankTitle = rankTitle;
    }

    public Character() {
    }

    public Character(Long currentCredits, Long currentXp, String faction, Integer id, Timestamp killedAt, Character killedBy, String name, Integer rankId, String rankTitle) {
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

    public static Character selectById(DSLContext create, Integer id) throws EntityNotFoundException {
        try {
            Field<Integer> credits = create.select(sum(CREDIT_JOURNAL.AMOUNT)).from(CREDIT_JOURNAL).where(CREDIT_JOURNAL.FK_CHARACTER.equal(id)).asField("CREDITS");
            Field<Integer> xp = create.select(sum(XP_JOURNAL.AMOUNT)).from(XP_JOURNAL).where(XP_JOURNAL.FK_CHARACTER.equal(id)).asField("XP");
            Field<Integer> rankId = create.select(PROMOTIONS.NEW_RANK).from(PROMOTIONS).where(PROMOTIONS.FK_CHARACTER.equal(id)).orderBy(PROMOTIONS.CREATED_AT.desc()).limit(1).asField("RANK_ID");

            Character c = create.select(CHARACTERS.ID, CHARACTERS.NAME, CHARACTERS.FACTION, CHARACTERS.KILLED_BY, credits, xp, rankId)
                    .from(CHARACTERS)
                    .where(CHARACTERS.ID.equal(id))
                    .fetchOne()
                    .into(Character.class);


            RanksRecord ranksRecord = create.selectFrom(RANKS).where(RANKS.LEVEL.eq(c.rankId)).fetchOneInto(RanksRecord.class);

            switch (c.faction) {

                case "A":
                    c.setRankTitle(ranksRecord.getAeonTitle());
                    break;
                case "C":
                    c.setRankTitle(ranksRecord.getCybranTitle());
                    break;
                case "S":
                    c.setRankTitle(ranksRecord.getSeraphTitle());
                    break;
                case "U":
                    c.setRankTitle(ranksRecord.getUefTitle());
                    break;
            }

            return c;
        } catch (NullPointerException e) {
            throw new EntityNotFoundException(CHARACTERS.getClass(), String.format("id = %s", id));
        }
    }

}
