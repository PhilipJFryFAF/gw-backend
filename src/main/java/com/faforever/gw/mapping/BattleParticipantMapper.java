package com.faforever.gw.mapping;

import com.faforever.gw.exceptions.EntityNotFoundException;
import com.faforever.gw.json.Battle;
import com.faforever.gw.json.BattleParticipant;
import com.faforever.gw.json.Character;
import com.faforever.gw.tables.records.BattleParticipantsRecord;
import com.faforever.gw.tables.records.BattlesRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.jooq.exception.DataTypeException;

import javax.annotation.Resource;

public class BattleParticipantMapper implements RecordMapper<BattleParticipantsRecord, BattleParticipant> {
    DSLContext dslContext;

    private BattleParticipantMapper(){}

    public BattleParticipantMapper(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    @Override
    public BattleParticipant map(BattleParticipantsRecord r) {
        try {
            return new BattleParticipant(getCharacter(r.getFkCharacter()), r.getResult(), r.getRole());
        }
        catch(EntityNotFoundException e)
        {
            throw new DataTypeException("mapping failed", e);
        }
    }

    private Character getCharacter(int characterID) throws EntityNotFoundException{
        return Character.selectById(dslContext, characterID);
    }
}
