package com.faforever.gw.model;

public class BattleParticipant {
    private Character character = null;
    private String result = null;
    private String role = null;

    public BattleParticipant(Character character, String result, String role) {
        this.character = character;
        this.result = result;
        this.role = role;
    }

    public Character getCharacter() {
        return character;
    }

    public String getResult() {
        return result;
    }

    public String getRole() {
        return role;
    }
}
