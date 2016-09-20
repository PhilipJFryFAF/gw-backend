package com.faforever.gw.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;

@JsonApiResource(type = "characters")
public class Character {
    @JsonProperty
    private String faction;
    @JsonApiId
    private Long id = null;
    @JsonProperty
    private Long killedAt;
    @JsonApiToOne
    private Character killedBy;
    @JsonProperty
    private String name;
    @JsonProperty
    private Long rankId;
    @JsonProperty
    private String rankTitle;

    /**
     * creates shallow instance (JSON reference only)
     *
     * @param id
     */
    public Character(Long id) {
        this.id = id;
    }

    public Character(Long id, String name, String faction, Long killedAt, Long killedBy, Long rankId, String rankTitle) {
        this.faction = faction;
        this.id = id;
        this.killedAt = killedAt;
        if (killedBy != null)
            this.killedBy = new Character(killedBy);
        this.name = name;
        this.rankId = rankId;
        this.rankTitle = rankTitle;
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

    public Long getKilledAt() {
        return killedAt;
    }

    public void setKilledAt(Long killedAt) {
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

}
