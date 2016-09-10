package com.faforever.gw.mapping;

import com.faforever.gw.json.BattleParticipant;
import com.faforever.gw.json.Character;
import com.faforever.gw.tables.records.BattleParticipantsRecord;
import com.faforever.gw.tables.records.CharactersRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;

public class CharacterMapper implements RecordMapper<CharactersRecord, Character> {
    DSLContext dslContext;

    private CharacterMapper(){}

    public CharacterMapper(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    @Override
    public Character map(CharactersRecord r) {
        return new Character(null, null, r.getFaction(), r.getId(), null, getCharacter(r.getKilledBy()), r.getName(), null, null);
    }

    private Character getCharacter(int characterID){
        return new Character(null,null, null, null, null, null, null, null, null);
    }
}
