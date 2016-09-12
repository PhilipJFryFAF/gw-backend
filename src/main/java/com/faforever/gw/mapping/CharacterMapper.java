package com.faforever.gw.mapping;

import com.faforever.gw.json.BattleParticipant;
import com.faforever.gw.json.Character;
import com.faforever.gw.tables.records.BattleParticipantsRecord;
import com.faforever.gw.tables.records.CharactersRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


public class CharacterMapper implements RecordMapper<CharactersRecord, Character> {

    public static final CharacterMapper INSTANCE = new CharacterMapper();

    private CharacterMapper(){}

    @Override
    public Character map(CharactersRecord r) {
        return new Character(null, null, r.getFaction(), r.getId(), null, getCharacter(r.getKilledBy()), r.getName(), null, null);
    }

    private Character getCharacter(Integer characterID){
        return new Character(null,null, null, null, null, null, null, null, null);
    }
}
