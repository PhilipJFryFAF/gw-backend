package com.faforever.gw.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;

@JsonApiResource(type = "battle_participants")
public class BattleParticipant {
    private Long id;
    private Battle battle;
    private Character character;
    private String result;
    private String role;

    public BattleParticipant(Long id, Long fkBattle, Long fkCharacter, String result, String role) {
        this.id = id;
        this.battle = new Battle(fkBattle, null);
        this.character = new Character(fkCharacter);
        this.result = result;
        this.role = role;
    }

    @JsonApiToOne
    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    @JsonApiId
    public Long getId() {
        return id;
    }

    @JsonApiToOne
    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public String getResult() {
        return result;
    }

    public String getRole() {
        return role;
    }
}
