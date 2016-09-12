package com.faforever.gw.model;

import com.faforever.gw.exceptions.SemanticsException;
import com.faforever.gw.mapping.CharacterMapper;
import com.faforever.gw.tables.records.RanksRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;
import org.jooq.DSLContext;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Optional;

import static com.faforever.gw.Tables.*;
import static org.jooq.impl.DSL.sum;

@JsonApiResource(type = "characters")
public class Character {
    @Resource CharacterMapper characterMapper;

    @JsonProperty
    private Long currentCredits;
    @JsonProperty
    private Long currentXp;
    @JsonProperty
    private String faction;
    @JsonApiId
    private Long id = null;
    @JsonProperty
    private Timestamp killedAt;
    @JsonApiToOne
    private Character killedBy;
    @JsonProperty
    private String name;
    @JsonProperty
    private Long rankId;
    @JsonProperty
    private String rankTitle;

    public Long getCurrentCredits() {
        return (currentCredits == null) ? 0 : currentCredits;
    }

    public void setCurrentCredits(Long currentCredits) {
        this.currentCredits = currentCredits;
    }

    public Long getCurrentXp() {
        return (currentXp == null) ? 0 : currentXp;
    }

    public void setCurrentXp(Long currentXp) {
        this.currentXp = currentXp;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getKilledAt() {
        return killedAt;
    }

    public void setKilledAt(Timestamp killedAt) {
        this.killedAt = killedAt;
    }

    public Character getKilledBy() {
        return killedBy;
    }

    public void setKilledBy(Character killedBy) {
        this.killedBy = killedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRankId() {
        return rankId;
    }


    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    public String getRankTitle() {
        return rankTitle;
    }


    public void setRankTitle(String rankTitle) {
        this.rankTitle = rankTitle;
    }

    public Character() {
    }

    public Character(Long currentCredits, Long currentXp, String faction, Long id, Timestamp killedAt, Character killedBy, String name, Long rankId, String rankTitle) {
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

}
